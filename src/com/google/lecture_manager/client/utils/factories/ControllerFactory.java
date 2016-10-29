package com.google.lecture_manager.client.utils.factories;

import com.google.lecture_manager.client.components.login.LoginController;
import com.google.lecture_manager.client.components.sign_up.SignUpController;
//{controller}
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.ElementTypes;

/**
 * Created by razvanolar on 27.10.2016
 */
public class ControllerFactory {

  public Controller getController(ElementTypes type) {
    switch (type) {
      case LOGIN_FORM:
        return LoginController.getInstance();
      case SIGN_UP_FORM:
        return SignUpController.getInstance();
      //{new_case}
    }
    return null;
  }
}