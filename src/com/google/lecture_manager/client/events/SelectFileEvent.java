package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.SelectFileEventHandler;
import com.google.lecture_manager.shared.model.FileData;

public class SelectFileEvent extends GwtEvent<SelectFileEventHandler> {

  private FileData fileData;

  public SelectFileEvent(FileData fileData) {
    this.fileData = fileData;
  }

  public static Type<SelectFileEventHandler> TYPE = new Type<>();

  public FileData getFileData() {
    return fileData;
  }

  @Override
  public Type<SelectFileEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SelectFileEventHandler handler) {
    handler.onSelectFileEvent(this);
  }
} 
