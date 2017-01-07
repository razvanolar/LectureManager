package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.PopulateLectureFields;

public interface PopulateLectureFieldsHandler extends EventHandler {
  void initEditMode(PopulateLectureFields event);
} 
