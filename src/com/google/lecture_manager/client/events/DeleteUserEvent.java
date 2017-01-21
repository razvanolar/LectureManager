package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.DeleteUserEventHandler;
import com.google.lecture_manager.shared.model.UserDTO;

import java.util.List;

public class DeleteUserEvent extends GwtEvent<DeleteUserEventHandler> {

  public static Type<DeleteUserEventHandler> TYPE = new Type<>();
  private final List<UserDTO> selectedItems;

  public DeleteUserEvent(List<UserDTO> selectedItems) {
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

  public List<UserDTO> getSelectedItems() {
    return selectedItems;
  }
}
