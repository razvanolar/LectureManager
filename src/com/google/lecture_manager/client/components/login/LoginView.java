package com.google.lecture_manager.client.components.login;

import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.utils.MaskableView;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.*;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Created by razvanolar on 27.10.2016
 */
public class LoginView implements LoginController.ILoginView {

  private CenterLayoutContainer centerLayoutContainer;
  private TextButton loginButton;
  private TextButton signUpButton;

  public LoginView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    loginButton = new TextButton("Login");
    signUpButton = new TextButton("Sign Up");
    TextField userTextField = new TextField();
    TextField passwordField = new TextField();
    FieldLabel userFieldLabel = new FieldLabel(userTextField, "Username");
    FieldLabel passwordFieldLabel = new FieldLabel(passwordField, "Password");

    loginButton.setWidth(70);
    signUpButton.setWidth(70);
    userFieldLabel.setWidth(300);
    passwordFieldLabel.setWidth(300);

    HBoxLayoutContainer hBoxLayoutContainer = new HBoxLayoutContainer(HBoxLayoutContainer.HBoxLayoutAlign.MIDDLE);
    hBoxLayoutContainer.setPack(BoxLayoutContainer.BoxLayoutPack.END);
    hBoxLayoutContainer.add(loginButton, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 5, 0, 0)));
    hBoxLayoutContainer.add(signUpButton);

    VBoxLayoutContainer vBoxLayoutContainer = new VBoxLayoutContainer(VBoxLayoutContainer.VBoxLayoutAlign.STRETCHMAX);
    vBoxLayoutContainer.add(userFieldLabel);
    vBoxLayoutContainer.add(passwordFieldLabel);
    vBoxLayoutContainer.add(hBoxLayoutContainer);

    centerLayoutContainer = new CenterLayoutContainer();
    centerLayoutContainer.add(vBoxLayoutContainer);
  }

  @Override
  public void mask(String message) {
    centerLayoutContainer.mask(message);
  }

  @Override
  public void unmask() {
    centerLayoutContainer.unmask();
  }

  public TextButton getLoginButton() {
    return loginButton;
  }

  public TextButton getSignUpButton() {
    return signUpButton;
  }

  @Override
  public Widget asWidget() {
    return centerLayoutContainer;
  }
}
