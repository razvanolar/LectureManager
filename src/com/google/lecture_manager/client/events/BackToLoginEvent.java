package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.BackToLoginEventHandler;

/**
 * Created by razvanolar on 28.10.2016
 */
public class BackToLoginEvent extends GwtEvent<BackToLoginEventHandler> {

  public static Type<BackToLoginEventHandler> TYPE = new Type<>();
  
  @Override
  public Type<BackToLoginEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(BackToLoginEventHandler backToLoginEventHandler) {
    backToLoginEventHandler.onBackToLoginEvent(this);
  }
}
