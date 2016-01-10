package com.xiaomo.TimeMachine;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.xiaomo.TimeMachine.model._MappingKit;
import com.xiaomo.TimeMachine.routes.AdminRoutes;
import com.xiaomo.TimeMachine.routes.FrontRoutes;

/**
 * API引导式配置
 */
public class TimeMachineConfig  extends JFinalConfig {

    public static C3p0Plugin createC3p0Plugin() {
        return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
    }



    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        // 加载少量必要配置，随后可用PropKit.get(...)获取值
        PropKit.use("db_config.properties");
        me.setDevMode(PropKit.getBoolean("devMode", false));
        // Template end
        me.setError404View("/error/404.vm");
        me.setError500View("/error/500.vm");
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add(new AdminRoutes()); //后台路由
        me.add(new FrontRoutes()); //前台路由
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        // 配置C3p0数据库连接池插件
        C3p0Plugin C3p0Plugin = createC3p0Plugin();
        me.add(C3p0Plugin);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
        me.add(arp);

        // 所有配置在 MappingKit 中搞定
        _MappingKit.mapping(arp);
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {


    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {
        me.add(new ContextPathHandler("CONTEXT_PATH"));
    }


    /**
     * 建议使用 JFinal 手册推荐的方式启动项目
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     */
    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 80, "/", 5);
    }
}
