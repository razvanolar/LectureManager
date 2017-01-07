package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.AddLectureEvent;

public interface AddLectureEventHandler extends EventHandler {
  void onAddLectureEvent(AddLectureEvent event);
} 
