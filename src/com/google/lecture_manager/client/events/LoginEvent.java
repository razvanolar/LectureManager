package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.LoginEventHandler;
import com.google.lecture_manager.shared.model.UserDTO;

/**
 * Created by razvanolar on 28.10.2016
 */
public class LoginEvent extends GwtEvent<LoginEventHandler> {

  private UserDTO user;

  public static Type<LoginEventHandler> TYPE = new Type<>();

  public LoginEvent(UserDTO user) {
    this.user = user;
  }

  public UserDTO getUser() {
    return user;
  }

  @Override
  public Type<LoginEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(LoginEventHandler loginEventHandler) {
    loginEventHandler.onLoginEvent(this);
  }
}
