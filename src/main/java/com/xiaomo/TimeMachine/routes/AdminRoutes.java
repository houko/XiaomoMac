package com.xiaomo.TimeMachine.routes;

import com.jfinal.config.Routes;
import com.xiaomo.TimeMachine.admin.blog.BlogController;
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
 * @Date: 2016/1/8 20:25
 * @Description: 后台
 * @Copyright(©) 2015 by xiaomo.
 */
public class AdminRoutes extends Routes {

    @Override
    public void config() {
        add("/admin", IndexController.class);    // 第三个参数为该Controller的视图存放路径
        add("/admin/blog", BlogController.class);            // 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
    }
}
