package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.DeleteLectureEvent;

public interface DeleteLectureEventHandler extends EventHandler {
  void onDeleteLectureEvent(DeleteLectureEvent event);
} 
