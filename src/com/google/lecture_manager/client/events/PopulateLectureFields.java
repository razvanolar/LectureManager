package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.PopulateLectureFieldsHandler;
import com.google.lecture_manager.shared.model.LectureDTO;

public class PopulateLectureFields extends GwtEvent<PopulateLectureFieldsHandler> {

  public static Type<PopulateLectureFieldsHandler> TYPE = new Type<>();
  private LectureDTO lecture;

  public PopulateLectureFields(LectureDTO lecture) {
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

  public LectureDTO getLecture() {
    return lecture;
  }
}
