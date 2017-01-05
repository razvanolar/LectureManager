package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.DeleteUserEventHandler;
import com.google.lecture_manager.shared.model.User;

import java.util.List;

public class DeleteUserEvent extends GwtEvent<DeleteUserEventHandler> {

  public static Type<DeleteUserEventHandler> TYPE = new Type<>();
  private final List<User> selectedItems;

  public DeleteUserEvent(List<User> selectedItems) {
    this.selectedItems = selectedItems;
  }

  @Override
  public Type<DeleteUserEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DeleteUserEventHandler handler) {
    handler.onDeleteUserEvent(this);
  }
} 
