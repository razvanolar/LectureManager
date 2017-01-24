package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.UnmaskUIEventHandler;

public class UnmaskUIEvent extends GwtEvent<UnmaskUIEventHandler> {

  public static Type<UnmaskUIEventHandler> TYPE = new Type<>();

  @Override
  public Type<UnmaskUIEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(UnmaskUIEventHandler handler) {
    handler.onUnmaskUIEvent(this);
  }
} 
