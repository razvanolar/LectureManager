package com.google.lecture_manager.client.components.app.manage_users;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.shared.UserTypes;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.*;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class AddEditUsersView implements AddEditUsersController.IAddEditUsersView {

  private TextField firstNameTextField;
  private TextField lastNameTextField;
  private TextField usernameTextField;
  private TextField emailTextField;
  private PasswordField pwdField;
  private PasswordField repwdField;
  private TextButton applyButton;
  private ComboBox<UserTypes> userTypesComboBox;
  private ListStore<UserTypes> listStore;
  private VerticalLayoutContainer mainContainer;
  private FieldLabel pwdFieldLabel, repFieldLabel;
  private Window window;

  public AddEditUsersView() {
    initGUI();
  }

  public void initGUI() {
    window = new Window();
    firstNameTextField = new TextField();
    lastNameTextField = new TextField();
    usernameTextField = new TextField();
    emailTextField = new TextField();
    pwdField = new PasswordField();
    repwdField = new PasswordField();
    listStore = new ListStore<>(new ModelKeyProvider<UserTypes>() {
      @Override
      public String getKey(UserTypes item) {
        return item.getId() + "";
      }
    });
    for (UserTypes userTypes : UserTypes.values()) {
      listStore.add(userTypes);
    }
    userTypesComboBox = new ComboBox<>(listStore, new LabelProvider<UserTypes>() {
      @Override
      public String getLabel(UserTypes item) {
        return item.name();
      }
    });
    userTypesComboBox.setForceSelection(true);
    userTypesComboBox.setTypeAhead(false);
    userTypesComboBox.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
    userTypesComboBox.setAllowBlank(false);
    userTypesComboBox.setAllowTextSelection(false);
    userTypesComboBox.setEditable(false);
    applyButton = new TextButton("Apply");

    FieldLabel firstNameFieldLabel = new FieldLabel(firstNameTextField, "First Name");
    FieldLabel lastNameFieldLabel = new FieldLabel(lastNameTextField, "Last Name");
    FieldLabel usernameFieldLabel = new FieldLabel(usernameTextField, "Username");
    FieldLabel emailFieldLabel = new FieldLabel(emailTextField, "Email");
    pwdFieldLabel = new FieldLabel(pwdField, "Password");
    repFieldLabel = new FieldLabel(repwdField, "Retype Password");
    FieldLabel userTypeLabel = new FieldLabel(userTypesComboBox, "Type");

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
    userTypeLabel.setLabelWidth(labelWidth);
    userTypeLabel.setWidth(textFieldWidth);
    applyButton.setWidth(70);

    VBoxLayoutContainer vBoxLayoutContainer = new VBoxLayoutContainer(VBoxLayoutContainer.VBoxLayoutAlign.STRETCHMAX);
    vBoxLayoutContainer.add(firstNameFieldLabel);
    vBoxLayoutContainer.add(lastNameFieldLabel);
    vBoxLayoutContainer.add(usernameFieldLabel);
    vBoxLayoutContainer.add(emailFieldLabel);
    vBoxLayoutContainer.add(pwdFieldLabel);
    vBoxLayoutContainer.add(repFieldLabel);
    vBoxLayoutContainer.add(userTypeLabel);
    HBoxLayoutContainer horizontalLayoutContainer = new HBoxLayoutContainer(HBoxLayoutContainer.HBoxLayoutAlign.MIDDLE);
    horizontalLayoutContainer.setPack(BoxLayoutContainer.BoxLayoutPack.END);
    BoxLayoutContainer.BoxLayoutData flex = new BoxLayoutContainer.BoxLayoutData();
    flex.setFlex(1);
    horizontalLayoutContainer.add(new Label(), flex);
    horizontalLayoutContainer.add(applyButton);
    vBoxLayoutContainer.add(horizontalLayoutContainer);

    mainContainer = new VerticalLayoutContainer();
    mainContainer.add(vBoxLayoutContainer, new VerticalLayoutContainer.VerticalLayoutData(1, 1, new Margins(5)));

    window.add(mainContainer);
    window.setSize("320", "300");
    window.setModal(true);
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

  public PasswordField getPwdField() {
    return pwdField;
  }

  public PasswordField getRepwdField() {
    return repwdField;
  }

  public TextButton getApplyButton() {
    return applyButton;
  }

  public ComboBox<UserTypes> getUserTypesComboBox() {
    return userTypesComboBox;
  }

  public FieldLabel getPwdFieldLabel() {
    return pwdFieldLabel;
  }

  public FieldLabel getRepFieldLabel() {
    return repFieldLabel;
  }

  @Override
  public Widget asWidget() {
    return window;
  }

  @Override
  public void mask(String message) {
    mainContainer.mask(message);
  }

  @Override
  public void unmask() {
    mainContainer.unmask();
  }

  public void close() {
    window.hide();
  }
}