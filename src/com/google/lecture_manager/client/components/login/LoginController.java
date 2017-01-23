package com.google.lecture_manager.client.components.login;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.client.events.SignUpEvent;
import com.google.lecture_manager.client.events.LoginEvent;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.MaskableView;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.shared.model.UserDTO;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Created by razvanolar on 27.10.2016
 */
public class LoginController extends Controller<LoginController.ILoginView> {

  public interface ILoginView extends View, MaskableView {
    TextButton getLoginButton();
    TextButton getSignUpButton();
    TextField getUserTextField();
    PasswordField getPasswordField();
  }

  private static LoginController INSTANCE = null;

  private ILoginView view;

  @Override
  public void bind(final ILoginView view) {
    view.getLoginButton().addSelectHandler(new SelectEvent.SelectHandler() {
      public void onSelect(SelectEvent event) {
        view.mask("Authenticating");
        AppUtils.SERVICE_FACTORY.getUserService().authenticate(view.getUserTextField().getText(),
                view.getPasswordField().getText(), new AsyncCallback<UserDTO>() {
                  public void onFailure(Throwable throwable) {
                    view.unmask();
                    Info.display("Error", throwable.getMessage());
                  }

                  public void onSuccess(UserDTO user) {
                    view.unmask();
                    if (user == null) {
                      Info.display("Error", "Unable to authenticate.");
                      return;
                    }
//                    Info.display("Info", "Successfully authenticated.");
                    AppUtils.EVENT_BUS.fireEvent(new LoginEvent(user));
                  }
                });
      }
    });

    view.getSignUpButton().addSelectHandler(new SelectEvent.SelectHandler() {
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new SignUpEvent());
      }
    });

    view.getUserTextField().setText("admin");
    view.getPasswordField().setText("admin");
    this.view = view;
    setIsBound(true);
  }

  @Override
  public void setDefaults() {
    if (view != null) {
      view.getUserTextField().setText("admin");
      view.getPasswordField().setText("admin");
    }
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
