package com.xiaomo.TimeMachine.handler;

import com.google.zxing.WriterException;
import com.jfinal.handler.Handler;
import com.xiaomo.TimeMachine.utils.QRCodeUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QRcodeHandler extends Handler {

    protected final Logger logger = LoggerFactory.getLogger(QRcodeHandler.class);

    @Override
    public void handle(String target, HttpServletRequest request,
                       HttpServletResponse response, boolean[] isHandled) {
        // 二维码拦截，对二维码修改
        if (target.startsWith("/api/qrcode-")) {
            isHandled[0] = true;
            String codeInfo = target.substring(12, target.lastIndexOf('.'));
            byte[] bytes = Base64.decodeBase64(codeInfo);
            try {
                QRCodeUtil.encode(response.getOutputStream(), new String(bytes, "UTF-8"), 100);
            } catch (IOException | WriterException e) {
                logger.error(e.getMessage());
            }
            return;
        }
        nextHandler.handle(target, request, response, isHandled);
    }

}
