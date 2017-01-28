package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.PopulateLectureFileEventHandler;
import com.google.lecture_manager.shared.model.LectureDTO;

public class PopulateLectureFileEvent extends GwtEvent<PopulateLectureFileEventHandler> {

  public static Type<PopulateLectureFileEventHandler> TYPE = new Type<>();
  private final LectureDTO selectedItem;

  public PopulateLectureFileEvent(LectureDTO selectedItem) {
    this.selectedItem = selectedItem;
  }

  @Override
  public Type<PopulateLectureFileEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(PopulateLectureFileEventHandler handler) {
    handler.loadFiles(this);
  }

  public LectureDTO getSelectedItem() {
    return selectedItem;
  }
}
