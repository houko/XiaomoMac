package com.xiaomo.TimeMachine.handler;

import com.jfinal.handler.Handler;
import com.jfinal.render.RenderFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对sitemap，rss等xml的处理
 *
 * @author l.cm
 * @site:www.dreamlu.net 2014年4月1日 下午9:36:53
 */
public class XmlHandler extends Handler {

    @Override
    public void handle(String target, HttpServletRequest request,
                       HttpServletResponse response, boolean[] isHandled) {
        if (target.endsWith(".xml")) {
            String view = target.replace(".xml", ".vm");
            RenderFactory.me().getRender("/xml".concat(view)).setContext(request, response).render();
            // 跳出
            isHandled[0] = true;
            return;
        }
        nextHandler.handle(target, request, response, isHandled);
    }

}
