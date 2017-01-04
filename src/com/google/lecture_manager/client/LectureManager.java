package com.google.lecture_manager.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.lecture_manager.client.components.app.AppController;
import com.google.lecture_manager.client.events.*;
import com.google.lecture_manager.client.handlers.*;
import com.google.lecture_manager.client.utils.AppConstants;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.ElementTypes;
import com.google.lecture_manager.client.utils.factories.AbstractFactory;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LectureManager implements EntryPoint {

  private BorderLayoutContainer mainContainer;
  private AppUtils appUtils;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    appUtils = AppUtils.getInstance();
    Viewport viewport = new Viewport();
    mainContainer = new BorderLayoutContainer();
    mainContainer.setStyleName(AppConstants.WHITE_BG_STYLE);
    mainContainer.setCenterWidget(AbstractFactory.getWidget(ElementTypes.LOGIN_FORM));
    viewport.add(mainContainer);
    initHandlers();
    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get().add(viewport);
  }

  private void initHandlers() {
    AppUtils.EVENT_BUS.addHandler(SignUpEvent.TYPE, new SignUpEventHandler() {
      public void onSignUpEvent(SignUpEvent signUpEvent) {
        mainContainer.setCenterWidget(AbstractFactory.getWidget(ElementTypes.SIGN_UP_FORM));
        mainContainer.forceLayout();
      }
    });

    AppUtils.EVENT_BUS.addHandler(BackToLoginEvent.TYPE, new BackToLoginEventHandler() {
      public void onBackToLoginEvent(BackToLoginEvent backToLoginEvent) {
        mainContainer.setCenterWidget(AbstractFactory.getWidget(ElementTypes.LOGIN_FORM));
        mainContainer.forceLayout();
      }
    });

    AppUtils.EVENT_BUS.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
      public void onLoginEvent(LoginEvent loginEvent) {
        appUtils.setAuthenticatedUser(loginEvent.getUser());
        AppController appController = (AppController) AbstractFactory.getController(ElementTypes.APP);
        mainContainer.setCenterWidget(appController.getView().asWidget());
        mainContainer.forceLayout();
      }
    });

    AppUtils.EVENT_BUS.addHandler(ManageUsers.TYPE, new ManageUsersHandler() {
      @Override
      public void onManageUsersEvent(ManageUsers event) {
        mainContainer.setCenterWidget(AbstractFactory.getWidget(ElementTypes.MANAGE_USERS));
        mainContainer.forceLayout();
      }
    });

    AppUtils.EVENT_BUS.addHandler(ManageLectures.TYPE, new ManageLecturesHandler() {
      @Override
      public void onManageLecturesEvent(ManageLectures event) {
        mainContainer.setCenterWidget(AbstractFactory.getWidget(ElementTypes.MANAGE_LECTURES));
        mainContainer.forceLayout();
      }
    });
  }
}
