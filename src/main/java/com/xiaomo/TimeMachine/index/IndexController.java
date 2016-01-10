package com.xiaomo.TimeMachine.index;

import com.jfinal.core.Controller;

/**
 * IndexController
 */
public class IndexController extends Controller {

    public void index() {
        render("index/index.html");
    }

    public void frontIndex() {
        render("/front/index.html");
    }
}





