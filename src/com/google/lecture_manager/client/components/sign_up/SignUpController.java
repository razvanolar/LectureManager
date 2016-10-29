package com.google.lecture_manager.client.components.sign_up;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.lecture_manager.client.events.BackToLoginEvent;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;

/**
 * Created by razvanolar on 28.10.2016
 */
public class SignUpController extends Controller<SignUpController.ISignUpView> {

  public interface ISignUpView extends View {
    Label getBackLabel();
  }

  private static SignUpController INSTANCE = null;

  private ISignUpView view;

  @Override
  public void bind(ISignUpView view) {
    view.getBackLabel().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent clickEvent) {
        AppUtils.EVENT_BUS.fireEvent(new BackToLoginEvent());
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
      INSTANCE = new SignUpController();
    return INSTANCE;
  }
}
