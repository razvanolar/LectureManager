package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.DeleteLectureEventHandler;
import com.google.lecture_manager.shared.model.LectureDTO;

import java.util.List;

public class DeleteLectureEvent extends GwtEvent<DeleteLectureEventHandler> {

  public static Type<DeleteLectureEventHandler> TYPE = new Type<>();
  private final List<LectureDTO> selectedItems;

  public DeleteLectureEvent(List<LectureDTO> selectedItems) {
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

  public List<LectureDTO> getSelectedItems() {
    return selectedItems;
  }
}
