package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.DeleteLectureEventHandler;
import com.google.lecture_manager.shared.model.Lecture;

import java.util.List;

public class DeleteLectureEvent extends GwtEvent<DeleteLectureEventHandler> {

  public static Type<DeleteLectureEventHandler> TYPE = new Type<>();
  private final List<Lecture> selectedItems;

  public DeleteLectureEvent(List<Lecture> selectedItems) {
    this.selectedItems = selectedItems;
  }

  @Override
  public Type<DeleteLectureEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DeleteLectureEventHandler handler) {
    handler.onDeleteLectureEvent(this);
  }

  public List<Lecture> getSelectedItems() {
    return selectedItems;
  }
}
