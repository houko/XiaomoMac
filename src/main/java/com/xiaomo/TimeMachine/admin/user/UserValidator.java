package com.xiaomo.TimeMachine.admin.user;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.xiaomo.TimeMachine.model.User;

/**
 * UserValidator.
 */
public class UserValidator extends Validator {

    protected void validate(Controller controller) {
        validateRequiredString("user.title", "titleMsg", "请输入标题!");
        validateRequiredString("user.content", "contentMsg", "请输入内容!");
    }

    protected void handleError(Controller controller) {
        controller.keepModel(User.class);

        String actionKey = getActionKey();
        if (actionKey.equals("/user/save"))
            controller.render("add.html");
        else if (actionKey.equals("/user/update"))
            controller.render("edit.html");
    }
}
