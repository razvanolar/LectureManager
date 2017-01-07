package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.EditLectureEvent;

public interface EditLectureEventHandler extends EventHandler {
  void onEditLectureEvent(EditLectureEvent event);
} 
