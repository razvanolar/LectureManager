package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.FilesEventHandler;
import com.google.lecture_manager.shared.model.LectureDTO;

public class FilesEvent extends GwtEvent<FilesEventHandler> {

  public static Type<FilesEventHandler> TYPE = new Type<>();
  private final LectureDTO selectedItem;

  public FilesEvent(LectureDTO selectedItem) {
    this.selectedItem = selectedItem;
  }

  @Override
  public Type<FilesEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(FilesEventHandler handler) {
    handler.onOpenFileManagementEvent(this);
  }

  public LectureDTO getSelectedItem() {
    return selectedItem;
  }
}
