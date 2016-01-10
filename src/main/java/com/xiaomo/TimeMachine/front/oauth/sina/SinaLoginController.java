package com.xiaomo.TimeMachine.front.oauth.sina;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.xiaomo.TimeMachine.model.User;
import com.xiaomo.TimeMachine.model.WbLogin;
import com.xiaomo.TimeMachine.constant.Consts;
import com.xiaomo.TimeMachine.constant.LoginField;
import com.xiaomo.TimeMachine.constant.LoginTypeConst;
import net.dreamlu.api.oauth.OauthSina;
import net.dreamlu.api.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * sina登录api
 *
 * @author L.cm
 * @date 2013-5-14 下午5:08:12
 */
public class SinaLoginController extends Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(SinaLoginController.class);

    private static final String SESSION_STATE = "_SESSION_STATE_SINA_";

    @ActionKey("api/sina")
    public void index() {
        try {
            String state = TokenUtil.randomState();
            setSessionAttr(SESSION_STATE, state);
            redirect(OauthSina.me().getAuthorizeUrl(state));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            redirect("/");
        }
    }

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
            JSONObject userInfo = OauthSina.me().getUserInfoByCode(code);
            LOGGER.error(userInfo.toJSONString());
            String openid = userInfo.getString("id");
            String nickname = userInfo.getString("screen_name");
            String photoUrl = userInfo.getString("profile_image_url");
            WbLogin login = WbLogin.dao.findByOpenID(openid, LoginTypeConst.sina);
            if (null == login) {
                login = new WbLogin();
                login.set(WbLogin.OPENID, openid);
                login.set(WbLogin.TYPE, LoginTypeConst.sina);
                login.set(WbLogin.HEAD_PHOTO, photoUrl);
                login.set(WbLogin.CREATETIME, new Date());
                login.set(WbLogin.STATUS, WbLogin.STATUS_N);
                login.set(WbLogin.NICKNAME, nickname).save();
            }
            // 是否邮件校验通过
            int status = login.getInt(WbLogin.STATUS);
            if (null != login.getInt(WbLogin.ID) && WbLogin.STATUS_N == status) {
                if (null == login.getInt(WbLogin.USERID)) {
                    setAttr("nouser", true);
                }
                // 跳转到绑定页
                setAttr(LoginField.id, login.getInt(WbLogin.ID));
                setAttr(LoginField.type, LoginTypeConst.sina);
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
