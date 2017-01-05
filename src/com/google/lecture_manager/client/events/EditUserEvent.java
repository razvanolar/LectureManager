package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.EditUserEventHandler;
import com.google.lecture_manager.shared.model.User;

public class EditUserEvent extends GwtEvent<EditUserEventHandler> {

  public static Type<EditUserEventHandler> TYPE = new Type<>();
  private final User selectedItem;

  public EditUserEvent(User selectedItem) {
    this.selectedItem = selectedItem;
  }

  @Override
  public Type<EditUserEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditUserEventHandler handler) {
    handler.onEditUserEvent(this);
  }

  public User getSelectedItem() {
    return selectedItem;
  }
}
