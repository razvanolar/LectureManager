package com.google.lecture_manager.client.utils.factories;

import com.google.lecture_manager.client.components.app.center.CenterView;
import com.google.lecture_manager.client.components.app.header.HeaderView;
import com.google.lecture_manager.client.components.app.manage_lectures.ManageLecturesView;
import com.google.lecture_manager.client.components.login.LoginView;
import com.google.lecture_manager.client.components.sign_up.SignUpView;
import com.google.lecture_manager.client.components.app.AppView;
import com.google.lecture_manager.client.components.app.manage_users.ManageUsersView;
import com.google.lecture_manager.client.components.app.center.lectures_tree.LecturesTreeView;
import com.google.lecture_manager.client.components.app.center.lecture_file_content.LectureFileContentView;
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
        return new AppView(HeaderView.getInstance().asWidget(), CenterView.getInstance().asWidget());
      case HEADER:
        return HeaderView.getInstance();
      case CENTER:
        return CenterView.getInstance();
      case MANAGE_USERS:
        return new ManageUsersView();
      case MANAGE_LECTURES:
        return new ManageLecturesView();
      case LECTURES_TREE:
        return new LecturesTreeView();
      case LECTURE_FILE_CONTENT:
        return new LectureFileContentView();
      //{new_case}
    }
    return null;
  }
}