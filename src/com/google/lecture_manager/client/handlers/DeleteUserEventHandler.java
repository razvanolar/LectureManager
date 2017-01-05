package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.DeleteUserEvent;

public interface DeleteUserEventHandler extends EventHandler {
  void onDeleteUserEvent(DeleteUserEvent event);
} 
