package com.xiaomo.TimeMachine.front.oauth.qq;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.xiaomo.TimeMachine.model.User;
import com.xiaomo.TimeMachine.model.WbLogin;
import com.xiaomo.TimeMachine.constant.Consts;
import com.xiaomo.TimeMachine.constant.LoginField;
import com.xiaomo.TimeMachine.constant.LoginTypeConst;
import net.dreamlu.api.oauth.OauthQQ;
import net.dreamlu.api.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;

/**
 * qq登录api
 *
 * @author L.cm
 * @date 2013-5-14 下午5:08:12
 */
public class QQLoginController extends Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(QQLoginController.class);

    private static final String SESSION_STATE = "_SESSION_STATE_QQ_";

    @ActionKey("api/qq")
    public void index() {
        try {
            String state = TokenUtil.randomState();
            setSessionAttr(SESSION_STATE, state);
            redirect(OauthQQ.me().getAuthorizeUrl(state));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            redirect("/");
        }
    }

    /**
     * 返回json:<url>http://wiki.connect.qq.com/get_user_info</url>
     * 腾讯回调
     */
    @ActionKey("api/qq/callback")
    public void callback() throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String code = getPara("code");
        String state = getPara("state");
        String session_state = getSessionAttr(SESSION_STATE);
        // 取消了授权
        if (StrKit.isBlank(state) || StrKit.isBlank(session_state) || !state.equals(session_state) || StrKit.isBlank(code)) {
            redirect("/front");
            return;
        }
        removeSessionAttr(SESSION_STATE);
        JSONObject userInfo = OauthQQ.me().getUserInfoByCode(code);
        String openid = userInfo.getString("openid");
        String nickname = userInfo.getString("nickname");
        String photoUrl = userInfo.getString("figureurl_qq_2");
        WbLogin login = WbLogin.dao.findByOpenID(openid, LoginTypeConst.qq);
        if (null == login) {
            login = new WbLogin();
            login.set(WbLogin.OPENID, openid);
            login.set(WbLogin.TYPE, LoginTypeConst.qq);
            login.set(WbLogin.HEAD_PHOTO, photoUrl);
            login.set(WbLogin.CREATETIME, new Date());
            login.set(WbLogin.STATUS, WbLogin.STATUS_N);
            login.set(WbLogin.NICKNAME, nickname);
            login.save();
        }
        LOGGER.error("login2:\t" + login);
        // 是否邮件校验通过
        int status = login.getInt(WbLogin.STATUS);
        if (null != login.getInt(WbLogin.ID) && WbLogin.STATUS_N == status) {
            if (null == login.getInt(WbLogin.USERID)) {
                setAttr("nouser", true);
            }
            // 跳转到绑定页
            setAttr(LoginField.id, login.getInt(WbLogin.ID));
            setAttr(LoginField.type, LoginTypeConst.qq);
            setAttr(LoginField.nickname, nickname);
            setAttr(LoginField.photourl, photoUrl);
            render(LoginField.bindUrl);
            return;
        }
        if (WbLogin.STATUS_Y == status) {
            User user = User.dao.findById(login.getInt(WbLogin.USERID));
            setSessionAttr(Consts.USER_SESSION, user);
        }
        redirect("/front");
    }
}
