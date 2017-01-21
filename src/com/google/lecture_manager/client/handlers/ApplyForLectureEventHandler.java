package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.ApplyForLectureEvent;

public interface ApplyForLectureEventHandler extends EventHandler {
  void onApplyForLectureEvent(ApplyForLectureEvent event);
} 
