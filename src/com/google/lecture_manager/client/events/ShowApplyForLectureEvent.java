package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.ShowApplyForLectureEventHandler;

public class ShowApplyForLectureEvent extends GwtEvent<ShowApplyForLectureEventHandler> {

  public static Type<ShowApplyForLectureEventHandler> TYPE = new Type<>();

  @Override
  public Type<ShowApplyForLectureEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ShowApplyForLectureEventHandler handler) {
    handler.onApplyForLectureEvent(this);
  }
} 
