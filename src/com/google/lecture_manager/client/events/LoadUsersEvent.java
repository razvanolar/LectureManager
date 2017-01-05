package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.LoadUsersEventHandler;

public class LoadUsersEvent extends GwtEvent<LoadUsersEventHandler> {

  public static Type<LoadUsersEventHandler> TYPE = new Type<>();

  @Override
  public Type<LoadUsersEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(LoadUsersEventHandler handler) {
    handler.reloadUsers(this);
  }
} 
