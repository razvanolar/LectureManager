package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.LoadLecturesEventHandler;

public class LoadLecturesEvent extends GwtEvent<LoadLecturesEventHandler> {

  public static Type<LoadLecturesEventHandler> TYPE = new Type<>();

  @Override
  public Type<LoadLecturesEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(LoadLecturesEventHandler handler) {
    handler.reloadLectures(this);
  }
} 
