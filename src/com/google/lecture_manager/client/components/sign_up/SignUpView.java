package com.google.lecture_manager.client.components.sign_up;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.utils.AppConstants;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Created by razvanolar on 28.10.2016
 */
public class SignUpView implements SignUpController.ISignUpView {

  private CenterLayoutContainer centerLayoutContainer;
  private Label backLabel;
  private TextButton signUpButton;
  private TextField firstNameTextField;
  private TextField lastNameTextField;
  private TextField usernameTextField;
  private TextField emailTextField;
  private TextField pwdTextField;
  private TextField repwdTextField;

  public SignUpView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    firstNameTextField = new TextField();
    lastNameTextField = new TextField();
    usernameTextField = new TextField();
    emailTextField = new TextField();
    pwdTextField = new TextField();
    repwdTextField = new TextField();
    signUpButton = new TextButton("Sign Up");
    FieldLabel firstNameFieldLabel = new FieldLabel(firstNameTextField, "First Name");
    FieldLabel lastNameFieldLabel = new FieldLabel(lastNameTextField, "Last Name");
    FieldLabel usernameFieldLabel = new FieldLabel(usernameTextField, "Username");
    FieldLabel emailFieldLabel = new FieldLabel(emailTextField, "Email");
    FieldLabel pwdFieldLabel = new FieldLabel(pwdTextField, "Password");
    FieldLabel repFieldLabel = new FieldLabel(repwdTextField, "Retype Password");
    backLabel = new Label("Back");
    backLabel.setStyleName(AppConstants.LINK_LABEL_STYLE);

    HBoxLayoutContainer hBoxLayoutContainer = new HBoxLayoutContainer(HBoxLayoutContainer.HBoxLayoutAlign.MIDDLE);
    BoxLayoutContainer.BoxLayoutData flex = new BoxLayoutContainer.BoxLayoutData();
    flex.setFlex(1);
    hBoxLayoutContainer.add(backLabel);
    hBoxLayoutContainer.add(new Label(), flex);
    hBoxLayoutContainer.add(signUpButton);

    int labelWidth = 120;
    int textFieldWidth = 300;
    firstNameFieldLabel.setLabelWidth(labelWidth);
    lastNameFieldLabel.setLabelWidth(labelWidth);
    usernameFieldLabel.setLabelWidth(labelWidth);
    usernameFieldLabel.setWidth(textFieldWidth);
    emailFieldLabel.setLabelWidth(labelWidth);
    emailFieldLabel.setWidth(textFieldWidth);
    pwdFieldLabel.setLabelWidth(labelWidth);
    pwdFieldLabel.setWidth(textFieldWidth);
    repFieldLabel.setLabelWidth(labelWidth);
    repFieldLabel.setWidth(textFieldWidth);
    signUpButton.setWidth(70);

    VBoxLayoutContainer vBoxLayoutContainer = new VBoxLayoutContainer(VBoxLayoutContainer.VBoxLayoutAlign.STRETCHMAX);
    vBoxLayoutContainer.add(firstNameFieldLabel);
    vBoxLayoutContainer.add(lastNameFieldLabel);
    vBoxLayoutContainer.add(usernameFieldLabel);
    vBoxLayoutContainer.add(emailFieldLabel);
    vBoxLayoutContainer.add(pwdFieldLabel);
    vBoxLayoutContainer.add(repFieldLabel);
    vBoxLayoutContainer.add(hBoxLayoutContainer);

    centerLayoutContainer = new CenterLayoutContainer();
    centerLayoutContainer.add(vBoxLayoutContainer);
  }

  public Label getBackLabel() {
    return backLabel;
  }

  public TextButton getSignUpButton() {
    return signUpButton;
  }

  public TextField getFirstNameTextField() {
    return firstNameTextField;
  }

  public TextField getLastNameTextField() {
    return lastNameTextField;
  }

  public TextField getUsernameTextField() {
    return usernameTextField;
  }

  public TextField getEmailTextField() {
    return emailTextField;
  }

  public TextField getPwdTextField() {
    return pwdTextField;
  }

  public TextField getRepwdTextField() {
    return repwdTextField;
  }

  @Override
  public Widget asWidget() {
    return centerLayoutContainer;
  }
}
