package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.PopulateUserFields;

public interface PopulateUserFieldsHandler extends EventHandler {
  void initEditMode(PopulateUserFields event);
} 
