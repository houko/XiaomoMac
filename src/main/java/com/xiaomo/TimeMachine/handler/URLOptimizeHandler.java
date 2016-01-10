package com.xiaomo.TimeMachine.handler;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对连接地址的优化，避免报错
 *
 * @author L.cm
 *         email: 596392912@qq.com
 *         site:http://www.dreamlu.net
 * @date 2015年1月30日下午11:52:21
 */
public class URLOptimizeHandler extends Handler {

    @Override
    public void handle(String target, HttpServletRequest request,
                       HttpServletResponse response, boolean[] isHandled) {

//		对于 ///x//x/x.html 路径的优化
        target = target.replaceAll("/{2,}", "/");

        nextHandler.handle(target, request, response, isHandled);
    }

}
