package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.ShowHomeEventHandler;

public class ShowHomeEvent extends GwtEvent<ShowHomeEventHandler> {

  public static Type<ShowHomeEventHandler> TYPE = new Type<>();

  @Override
  public Type<ShowHomeEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ShowHomeEventHandler handler) {
    handler.onShowHomeEvent(this);
  }
} 
