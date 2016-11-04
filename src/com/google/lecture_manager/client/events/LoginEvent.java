package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.LoginEventHandler;
import com.google.lecture_manager.shared.model.User;

/**
 * Created by razvanolar on 28.10.2016
 */
public class LoginEvent extends GwtEvent<LoginEventHandler> {

  private User user;

  public static Type<LoginEventHandler> TYPE = new Type<>();

  public LoginEvent(User user) {
    this.user = user;
  }

  public User getUser() {
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
