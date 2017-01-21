package com.google.lecture_manager.client.components.app.manage_users;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.events.LoadUsersEvent;
import com.google.lecture_manager.client.events.PopulateUserFields;
import com.google.lecture_manager.client.handlers.PopulateUserFieldsHandler;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.shared.InputValidator;
import com.google.lecture_manager.shared.UserTypes;
import com.google.lecture_manager.shared.model.User;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class AddEditUsersController extends Controller<AddEditUsersController.IAddEditUsersView> {

  public interface IAddEditUsersView extends View{

    TextField getFirstNameTextField();
    TextField getLastNameTextField();
    TextField getUsernameTextField();
    TextField getEmailTextField();
    PasswordField getPwdField();
    PasswordField getRepwdField();
    TextButton getApplyButton();
    ComboBox<UserTypes> getUserTypesComboBox();
    FieldLabel getPwdFieldLabel();
    FieldLabel getRepFieldLabel();
    Widget asWidget();
    void mask(String message);
    void unmask();
    void close();
  }
  private static AddEditUsersController INSTANCE;

  private IAddEditUsersView view;
  private User selectedUser;

  public void bind(final IAddEditUsersView view) {
    this.view = view;
    setIsBound(true);
    AppUtils.EVENT_BUS.addHandler(PopulateUserFields.TYPE, new PopulateUserFieldsHandler() {
      @Override
      public void initEditMode(PopulateUserFields event) {
        selectedUser = event.getUser();
        view.getPwdField().setVisible(false);
        view.getPwdFieldLabel().setVisible(false);
        view.getRepwdField().setVisible(false);
        view.getRepFieldLabel().setVisible(false);
        view.getFirstNameTextField().setValue(selectedUser.getFirstName());
        view.getLastNameTextField().setValue(selectedUser.getLastName());
        view.getUsernameTextField().setValue(selectedUser.getUserName());
        view.getEmailTextField().setValue(selectedUser.getEmail());
        view.getPwdField().setValue("");
        view.getRepwdField().setValue("");
        view.getUserTypesComboBox().setValue(selectedUser.getType());
      }
    });
    addListeners();
  }

  public IAddEditUsersView getView() {
    return view;
  }

  private void addListeners() {
    view.getApplyButton().addSelectHandler(new SelectEvent.SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        if (!validFields()) {
          Info.display("Info", "Invalid input");
          return;
        }
        User temp = collectData();
        view.mask("Applying...");
        if (selectedUser == null) {
          AppUtils.SERVICE_FACTORY.getUserService().addNewUser(temp, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
              view.unmask();
              new AlertMessageBox("Info", "Error while adding new user: " + caught.getMessage()).show();
            }

            @Override
            public void onSuccess(Void result) {
              view.unmask();
              Info.display("Info", "User added successfully.");
              view.close();
              AppUtils.EVENT_BUS.fireEvent(new LoadUsersEvent());
            }
          });
        } else {
          temp.setId(selectedUser.getId());
          AppUtils.SERVICE_FACTORY.getUserService().editUser(temp, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
              view.unmask();
              new AlertMessageBox("Info", "Error while editing user: " + caught.getMessage()).show();
            }

            @Override
            public void onSuccess(Void result) {
              view.unmask();
              Info.display("Info", "User edited successfully");
              view.close();
              AppUtils.EVENT_BUS.fireEvent(new LoadUsersEvent());
            }
          });
        }
      }
    });
  }

  private boolean validFields() {
    String firstName = view.getFirstNameTextField().getText();
    String lastName = view.getLastNameTextField().getText();
    String userName = view.getUsernameTextField().getText();
    String email = view.getEmailTextField().getText();
    String password = view.getPwdField().getText();
    String repassword = view.getRepwdField().getText();
    boolean result = !(InputValidator.isNullOrEmpty(firstName) || InputValidator.isNullOrEmpty(lastName) || InputValidator.isNullOrEmpty(userName) ||
            InputValidator.isNullOrEmpty(email)) &&
            firstName.length() > 2 && lastName.length() > 2 && userName.length() > 2 && email.matches(".+@.+\\.[a-z]+");
    result = result && (selectedUser != null || (!(InputValidator.isNullOrEmpty(password) || InputValidator.isNullOrEmpty(repassword)) && password.equals(repassword)));

    return result;
  }

  private User collectData() {
    User temp = new User(view.getFirstNameTextField().getText(),
            view.getLastNameTextField().getText(),
            view.getUsernameTextField().getText(),
            view.getEmailTextField().getText(),
            view.getPwdField().getText());
    temp.setTypeId(view.getUserTypesComboBox().getValue().getId());
    return temp;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new AddEditUsersController();
    return INSTANCE;
  }

  @Override
  public void setDefaults() {
    view.getPwdField().setVisible(true);
    view.getPwdFieldLabel().setVisible(true);
    view.getRepwdField().setVisible(true);
    view.getRepFieldLabel().setVisible(true);
    view.getFirstNameTextField().setValue("");
    view.getLastNameTextField().setValue("");
    view.getUsernameTextField().setValue("");
    view.getEmailTextField().setValue("");
    view.getPwdField().setValue("");
    view.getRepwdField().setValue("");
    view.getUserTypesComboBox().setValue(UserTypes.STUDENT);
    selectedUser = null;
  }
}