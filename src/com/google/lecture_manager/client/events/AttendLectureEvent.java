package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.AttendLectureEventHandler;
import com.google.lecture_manager.shared.model.LectureDTO;

public class AttendLectureEvent extends GwtEvent<AttendLectureEventHandler> {

  public static Type<AttendLectureEventHandler> TYPE = new Type<>();

  private LectureDTO lecture;

  public AttendLectureEvent(LectureDTO lecture) {
    this.lecture = lecture;
  }

  public LectureDTO getLecture() {
    return lecture;
  }

  @Override
  public Type<AttendLectureEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AttendLectureEventHandler handler) {
    handler.onAttendLectureEvent(this);
  }
} 
