package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.ApplyForLectureEventHandler;

public class ApplyForLectureEvent extends GwtEvent<ApplyForLectureEventHandler> {

  public static Type<ApplyForLectureEventHandler> TYPE = new Type<>();

  @Override
  public Type<ApplyForLectureEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ApplyForLectureEventHandler handler) {
    handler.onApplyForLectureEvent(this);
  }
} 
