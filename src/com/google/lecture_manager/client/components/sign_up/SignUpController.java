package com.google.lecture_manager.client.components.sign_up;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.lecture_manager.client.events.BackToLoginEvent;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.MaskableView;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.shared.model.User;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Created by razvanolar on 28.10.2016
 */
public class SignUpController extends Controller<SignUpController.ISignUpView> {

  public interface ISignUpView extends View, MaskableView {
    Label getBackLabel();
    TextButton getSignUpButton();
    TextField getFirstNameTextField();
    TextField getLastNameTextField();
    TextField getUsernameTextField();
    TextField getEmailTextField();
    PasswordField getPwdField();
    PasswordField getRepwdField();
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
    view.getPwdField().addKeyUpHandler(handler);
    view.getRepwdField().addKeyUpHandler(handler);

    view.getSignUpButton().addSelectHandler(new SelectEvent.SelectHandler() {
      public void onSelect(SelectEvent event) {
        onSignUpPressed();
      }
    });

    this.view = view;
    setIsBound(true);
  }

  private boolean validFields() {
    String firstName = view.getFirstNameTextField().getText();
    String lastName = view.getLastNameTextField().getText();
    String userName = view.getUsernameTextField().getText();
    String email = view.getEmailTextField().getText();
    String password = view.getPwdField().getText();
    String repassword = view.getRepwdField().getText();
    return !(AppUtils.isNullOrEmpty(firstName) || AppUtils.isNullOrEmpty(lastName) || AppUtils.isNullOrEmpty(userName) ||
            AppUtils.isNullOrEmpty(email) || AppUtils.isNullOrEmpty(password) || AppUtils.isNullOrEmpty(repassword)) &&
            firstName.length() > 2 && lastName.length() > 2 && userName.length() > 2 && email.matches(".+@.+\\.[a-z]+") &&
            password.equals(repassword);
  }

  private void onSignUpPressed() {
    if (!validFields()) {
      return;
    }
    view.mask("Sign Up...");
    AppUtils.SERVICE_FACTORY.getUserService().addNewUser(new User(), new AsyncCallback<Void>() {
      public void onFailure(Throwable throwable) {
        view.unmask();
      }

      public void onSuccess(Void aVoid) {
        view.unmask();
      }
    });
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
