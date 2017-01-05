package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.AddUserEventHandler;

public class AddUserEvent extends GwtEvent<AddUserEventHandler> {

  public static Type<AddUserEventHandler> TYPE = new Type<>();

  @Override
  public Type<AddUserEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddUserEventHandler handler) {
    handler.onAddUserEvent(this);
  }
} 
