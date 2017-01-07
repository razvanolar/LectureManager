package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.AddLectureEventHandler;

public class AddLectureEvent extends GwtEvent<AddLectureEventHandler> {

  public static Type<AddLectureEventHandler> TYPE = new Type<>();

  @Override
  public Type<AddLectureEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddLectureEventHandler handler) {
    handler.onAddLectureEvent(this);
  }
} 
