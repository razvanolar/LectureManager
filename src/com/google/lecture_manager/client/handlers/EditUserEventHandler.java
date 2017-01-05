package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.EditUserEvent;

public interface EditUserEventHandler extends EventHandler {
  void onEditUserEvent(EditUserEvent event);
} 
