package com.xiaomo.TimeMachine.front.oauth.baidu;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.xiaomo.TimeMachine.model.User;
import com.xiaomo.TimeMachine.model.WbLogin;
import com.xiaomo.TimeMachine.constant.Consts;
import com.xiaomo.TimeMachine.constant.LoginField;
import com.xiaomo.TimeMachine.constant.LoginTypeConst;
import net.dreamlu.api.oauth.OauthBaidu;
import net.dreamlu.api.util.TokenUtil;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 百度登录
 *
 * @author L.cm
 *         email: 596392912@qq.com
 *         site:  http://www.dreamlu.net
 * @date Jun 27, 2013 12:40:11 AM
 */
public class BaiduLoginController extends Controller {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BaiduLoginController.class);
    private static final String PHOTO_URL = "http://tb.himg.baidu.com/sys/portrait/item/";

    private static final String SESSION_STATE = "_SESSION_STATE_BAIDU_";

    public void index() {
        try {
            String state = TokenUtil.randomState();
            setSessionAttr(SESSION_STATE, state);
            redirect(OauthBaidu.me().getAuthorizeUrl(state));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            redirect("/");
        }
    }

    /**
     * 百度授权回调
     * <p/>
     * 返回json：<url>http://developer.baidu.com/wiki/index.php?title=docs/oauth/rest/file_data_apis_list#.E8.8E.B7.E5.8F.96.E5.BD.93.E5.89.8D.E7.99.BB.E5.BD.95.E7.94.A8.E6.88.B7.E7.9A.84.E4.BF.A1.E6.81.AF</url>
     *
     * @return void    返回类型
     * @Title: callback
     */
    public void callback() {
        String code = getPara("code");
        String state = getPara("state");
        String session_state = getSessionAttr(SESSION_STATE);
        // 取消了授权
        if (StrKit.isBlank(state) || StrKit.isBlank(session_state) || !state.equals(session_state) || StrKit.isBlank(code)) {
            redirect("/admin");
            return;
        }
        removeSessionAttr(SESSION_STATE);
        try {
            // 获取用户登录信息
            JSONObject userInfo = OauthBaidu.me().getUserInfoByCode(code);
            LOGGER.info(userInfo.toJSONString());
            String openid = userInfo.getString("userid");
            String nickname = userInfo.getString("username");
            String photoUrl = PHOTO_URL + userInfo.getString("portrait");
            WbLogin login = WbLogin.dao.findByOpenID(openid, LoginTypeConst.baidu);
            LOGGER.error("login:\t" + login);
            if (null == login) {
                login = new WbLogin();
                login.set(WbLogin.OPENID, openid);
                login.set(WbLogin.TYPE, LoginTypeConst.baidu);
                login.set(WbLogin.HEAD_PHOTO, photoUrl);
                login.set(WbLogin.CREATETIME, new Date());
                login.set(WbLogin.STATUS, WbLogin.STATUS_N);
                login.set(WbLogin.NICKNAME, nickname).save();
            }
            LOGGER.error("login:\t" + login.toJson());
            // 是否邮件校验通过
            int status = login.getInt(WbLogin.STATUS);
            if (null != login.getInt(WbLogin.ID) && WbLogin.STATUS_N == status) {
                if (null == login.getInt(WbLogin.USERID)) {
                    setAttr("nouser", true);
                }
                // 跳转到绑定页
                setAttr(LoginField.id, login.getInt(WbLogin.ID));
                setAttr(LoginField.type, LoginTypeConst.baidu);
                setAttr(LoginField.nickname, nickname);
                setAttr(LoginField.photourl, photoUrl);
                render(LoginField.bindUrl);
                return;
            }
            if (WbLogin.STATUS_Y == status) {
                User user = User.dao.findById(login.getInt(WbLogin.USERID));
                setSessionAttr(Consts.USER_SESSION, user);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        redirect("/front");
    }
}
