package com.google.lecture_manager.client.utils.factories;

import com.google.lecture_manager.client.components.login.LoginView;
import com.google.lecture_manager.client.components.sign_up.SignUpView;
import com.google.lecture_manager.client.components.app.AppView;
//{view}
import com.google.lecture_manager.client.utils.ElementTypes;
import com.google.lecture_manager.client.utils.View;

/**
 * Created by razvanolar on 27.10.2016
 */
public class ViewFactory {

  public View getView(ElementTypes type) {
    switch (type) {
      case LOGIN_FORM:
        return new LoginView();
      case SIGN_UP_FORM:
        return new SignUpView();
      case APP:
        return new AppView();
      //{new_case}
    }
    return null;
  }
}