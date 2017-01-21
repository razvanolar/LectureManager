package com.google.lecture_manager.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.lecture_manager.client.handlers.PopulateUserFieldsHandler;
import com.google.lecture_manager.shared.model.UserDTO;

public class PopulateUserFields extends GwtEvent<PopulateUserFieldsHandler> {

  public static Type<PopulateUserFieldsHandler> TYPE = new Type<>();
  private UserDTO user;

  public PopulateUserFields(UserDTO user) {
    this.user = user;
  }

  @Override
  public Type<PopulateUserFieldsHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(PopulateUserFieldsHandler handler) {
    handler.initEditMode(this);
  }

  public UserDTO getUser() {
    return user;
  }
}
