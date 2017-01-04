package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.ManageUsersHandler;

public class ManageUsers extends GwtEvent<ManageUsersHandler> {

  public static Type<ManageUsersHandler> TYPE = new Type<>();

  @Override
  public Type<ManageUsersHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ManageUsersHandler handler) {
    handler.onManageUsersEvent(this);
  }
} 
