package com.xiaomo.TimeMachine.routes;

import com.jfinal.config.Routes;
import com.xiaomo.TimeMachine.front.oauth.qq.QQLoginController;
import com.xiaomo.TimeMachine.index.IndexController;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author: xiaomo
 * @github: https://github.com/qq83387856
 * @email: hupengbest@163.com(83387856)
 * @Date: 2016/1/8 20:38
 * @Description: todo
 * @Copyright(©) 2015 by xiaomo.
 */
public class FrontRoutes extends Routes {
    @Override
    public void config() {
        add("/", IndexController.class, "front");    // 第三个参数为该Controller的视图存放路径
        add("/api/qq", QQLoginController.class);
    }
}
