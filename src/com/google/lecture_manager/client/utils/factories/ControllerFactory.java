package com.google.lecture_manager.client.utils.factories;

import com.google.lecture_manager.client.components.app.center.CenterController;
import com.google.lecture_manager.client.components.app.header.HeaderController;
import com.google.lecture_manager.client.components.app.manage_lectures.ManageLecturesController;
import com.google.lecture_manager.client.components.login.LoginController;
import com.google.lecture_manager.client.components.sign_up.SignUpController;
import com.google.lecture_manager.client.components.app.AppController;
import com.google.lecture_manager.client.components.app.manage_users.ManageUsersController;
import com.google.lecture_manager.client.components.app.center.lectures_tree.LecturesTreeController;
import com.google.lecture_manager.client.components.app.center.lecture_file_content.LectureFileContentController;
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
      case APP:
        return AppController.getInstance();
      case HEADER:
        return HeaderController.getInstance();
      case CENTER:
        return CenterController.getInstance();
      case MANAGE_USERS:
        return ManageUsersController.getInstance();
      case MANAGE_LECTURES:
        return ManageLecturesController.getInstance();
      case LECTURES_TREE:
        return LecturesTreeController.getInstance();
      case LECTURE_FILE_CONTENT:
        return LectureFileContentController.getInstance();
      //{new_case}
    }
    return null;
  }
}