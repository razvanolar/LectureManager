package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.PopulateLectureFieldsHandler;
import com.google.lecture_manager.shared.model.Lecture;

public class PopulateLectureFields extends GwtEvent<PopulateLectureFieldsHandler> {

  public static Type<PopulateLectureFieldsHandler> TYPE = new Type<>();
  private Lecture lecture;

  public PopulateLectureFields(Lecture lecture) {
    this.lecture = lecture;
  }

  @Override
  public Type<PopulateLectureFieldsHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(PopulateLectureFieldsHandler handler) {
    handler.initEditMode(this);
  }

  public Lecture getLecture() {
    return lecture;
  }
}
