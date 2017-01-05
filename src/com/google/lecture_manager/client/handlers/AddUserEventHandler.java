package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.AddUserEvent;

public interface AddUserEventHandler extends EventHandler {
  void onAddUserEvent(AddUserEvent event);
} 
