package com.google.lecture_manager.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
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
    viewport.add(mainContainer);
    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get().add(viewport);
    callInitService();
  }

  private void callInitService() {
    mainContainer.setCenterWidget(AbstractFactory.getWidget(ElementTypes.LOGIN_FORM));
    initHandlers();

    mainContainer.mask("Init Server Data...");
    AppUtils.SERVICE_FACTORY.getInitService().initServerData(new AsyncCallback<Void>() {
      public void onFailure(Throwable throwable) {
        mainContainer.unmask();
        mainContainer.setCenterWidget(new Label(throwable.getMessage()));
      }

      public void onSuccess(Void aVoid) {
        mainContainer.setCenterWidget(AbstractFactory.getWidget(ElementTypes.LOGIN_FORM));
        initHandlers();
        mainContainer.unmask();
      }
    });
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
  }
}
