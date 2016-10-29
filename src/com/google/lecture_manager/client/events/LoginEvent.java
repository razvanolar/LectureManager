package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.LoginEventHandler;

/**
 * Created by razvanolar on 28.10.2016
 */
public class LoginEvent extends GwtEvent<LoginEventHandler> {

  public static Type<LoginEventHandler> TYPE = new Type<>();

  @Override
  public Type<LoginEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(LoginEventHandler loginEventHandler) {
    loginEventHandler.onLoginEvent(this);
  }
}
