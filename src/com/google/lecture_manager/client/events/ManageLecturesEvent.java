package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.ManageLecturesHandler;

public class ManageLecturesEvent extends GwtEvent<ManageLecturesHandler> {

  public static Type<ManageLecturesHandler> TYPE = new Type<>();

  @Override
  public Type<ManageLecturesHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ManageLecturesHandler handler) {
    handler.onManageLecturesEvent(this);
  }
} 
