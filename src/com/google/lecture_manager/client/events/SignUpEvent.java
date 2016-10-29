package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.SignUpEventHandler;

/**
 * Created by razvanolar on 28.10.2016
 */
public class SignUpEvent extends GwtEvent<SignUpEventHandler> {

  public static Type<SignUpEventHandler> TYPE = new Type<>();

  @Override
  public Type<SignUpEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SignUpEventHandler signUpEventHandler) {
    signUpEventHandler.onSignUpEvent(this);
  }
}
