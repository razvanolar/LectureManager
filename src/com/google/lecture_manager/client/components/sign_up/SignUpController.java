package com.google.lecture_manager.client.components.sign_up;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.lecture_manager.client.events.BackToLoginEvent;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Created by razvanolar on 28.10.2016
 */
public class SignUpController extends Controller<SignUpController.ISignUpView> {

  public interface ISignUpView extends View {
    Label getBackLabel();
    TextButton getSignUpButton();
    TextField getFirstNameTextField();
    TextField getLastNameTextField();
    TextField getUsernameTextField();
    TextField getEmailTextField();
    TextField getPwdTextField();
    TextField getRepwdTextField();
  }

  private static SignUpController INSTANCE = null;

  private ISignUpView view;

  @Override
  public void bind(final ISignUpView view) {
    view.getBackLabel().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent clickEvent) {
        AppUtils.EVENT_BUS.fireEvent(new BackToLoginEvent());
      }
    });

    KeyUpHandler handler = new KeyUpHandler() {
      public void onKeyUp(KeyUpEvent keyUpEvent) {
        view.getSignUpButton().setEnabled(validFields());
      }
    };

    view.getFirstNameTextField().addKeyUpHandler(handler);
    view.getLastNameTextField().addKeyUpHandler(handler);
    view.getUsernameTextField().addKeyUpHandler(handler);
    view.getEmailTextField().addKeyUpHandler(handler);
    view.getPwdTextField().addKeyUpHandler(handler);
    view.getRepwdTextField().addKeyUpHandler(handler);

    this.view = view;
    setIsBound(true);
  }

  private boolean validFields() {
    return !(AppUtils.isNullOrEmpty(view.getFirstNameTextField().getText()) ||
            AppUtils.isNullOrEmpty(view.getLastNameTextField().getText()) ||
            AppUtils.isNullOrEmpty(view.getUsernameTextField().getText()) ||
            AppUtils.isNullOrEmpty(view.getEmailTextField().getText()) ||
            AppUtils.isNullOrEmpty(view.getPwdTextField().getText()) ||
            AppUtils.isNullOrEmpty(view.getRepwdTextField().getText()));
  }

  @Override
  public void setDefaults() {
    if (view != null) {
      view.getSignUpButton().setEnabled(validFields());
    }
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new SignUpController();
    return INSTANCE;
  }
}
