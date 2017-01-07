package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.EditLectureEventHandler;
import com.google.lecture_manager.shared.model.Lecture;

public class EditLectureEvent extends GwtEvent<EditLectureEventHandler> {

  public static Type<EditLectureEventHandler> TYPE = new Type<>();
  private final Lecture selectedItem;

  public EditLectureEvent(Lecture selectedItem) {
    this.selectedItem = selectedItem;
  }

  @Override
  public Type<EditLectureEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditLectureEventHandler handler) {
    handler.onEditLectureEvent(this);
  }

  public Lecture getSelectedItem() {
    return selectedItem;
  }
}
