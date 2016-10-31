package com.google.lecture_manager.client.components.login;

import com.google.lecture_manager.client.events.SignUpEvent;
import com.google.lecture_manager.client.events.LoginEvent;
import com.google.lecture_manager.client.handlers.LoginEventHandler;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.MaskableView;
import com.google.lecture_manager.client.utils.View;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

/**
 * Created by razvanolar on 27.10.2016
 */
public class LoginController extends Controller<LoginController.ILoginView> {

  public interface ILoginView extends View, MaskableView {
    TextButton getLoginButton();
    TextButton getSignUpButton();
  }

  private static LoginController INSTANCE = null;

  private ILoginView view;

  @Override
  public void bind(ILoginView view) {
    view.getLoginButton().addSelectHandler(new SelectEvent.SelectHandler() {
      public void onSelect(SelectEvent event) {
        AppUtils.log("login selection");
        AppUtils.EVENT_BUS.fireEvent(new LoginEvent());
      }
    });

    view.getSignUpButton().addSelectHandler(new SelectEvent.SelectHandler() {
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new SignUpEvent());
      }
    });

    AppUtils.EVENT_BUS.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
      public void onLoginEvent(LoginEvent loginEvent) {
        AlertMessageBox alert = new AlertMessageBox("Title", "Login event detected");
        alert.show();
      }
    });

    this.view = view;
    setIsBound(true);
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new LoginController();
    return INSTANCE;
  }
}
