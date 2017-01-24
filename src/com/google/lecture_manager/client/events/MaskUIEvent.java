package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.MaskUIEventHandler;

public class MaskUIEvent extends GwtEvent<MaskUIEventHandler> {

  public static Type<MaskUIEventHandler> TYPE = new Type<>();

  private String message;

  public MaskUIEvent(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public Type<MaskUIEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(MaskUIEventHandler handler) {
    handler.onMaskUIEvent(this);
  }
} 
