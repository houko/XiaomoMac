package com.xiaomo.TimeMachine.admin.blog;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * UserInterceptor
 * 拦截器
 */
public class BlogInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        System.out.println("Before invoking " + inv.getActionKey());
        inv.invoke();
        System.out.println("After invoking " + inv.getActionKey());
    }
}
