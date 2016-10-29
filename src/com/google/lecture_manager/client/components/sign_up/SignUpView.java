package com.google.lecture_manager.client.components.sign_up;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * Created by razvanolar on 28.10.2016
 */
public class SignUpView implements SignUpController.ISignUpView {

  private CenterLayoutContainer centerLayoutContainer;
  private Label backLabel;

  public SignUpView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    TextField usernameTextField = new TextField();
    TextField emailTextField = new TextField();
    TextField pwdTextField = new TextField();
    TextField repwdTextField = new TextField();
    FieldLabel usernameFieldLabel = new FieldLabel(usernameTextField, "Username");
    FieldLabel emailFieldLabel = new FieldLabel(emailTextField, "Email");
    FieldLabel pwdFieldLabel = new FieldLabel(pwdTextField, "Password");
    FieldLabel repFieldLabel = new FieldLabel(repwdTextField, "Retype Password");
    backLabel = new Label("Back");

    int labelWidth = 120;
    int textFieldWidth = 300;
    usernameFieldLabel.setLabelWidth(labelWidth);
    usernameFieldLabel.setWidth(textFieldWidth);
    emailFieldLabel.setLabelWidth(labelWidth);
    emailFieldLabel.setWidth(textFieldWidth);
    pwdFieldLabel.setLabelWidth(labelWidth);
    pwdFieldLabel.setWidth(textFieldWidth);
    repFieldLabel.setLabelWidth(labelWidth);
    repFieldLabel.setWidth(textFieldWidth);

    VBoxLayoutContainer vBoxLayoutContainer = new VBoxLayoutContainer(VBoxLayoutContainer.VBoxLayoutAlign.STRETCHMAX);
    vBoxLayoutContainer.add(usernameFieldLabel);
    vBoxLayoutContainer.add(emailFieldLabel);
    vBoxLayoutContainer.add(pwdFieldLabel);
    vBoxLayoutContainer.add(repFieldLabel);
    vBoxLayoutContainer.add(backLabel);

    centerLayoutContainer = new CenterLayoutContainer();
    centerLayoutContainer.add(vBoxLayoutContainer);
  }

  public Label getBackLabel() {
    return backLabel;
  }

  @Override
  public Widget asWidget() {
    return centerLayoutContainer;
  }
}
