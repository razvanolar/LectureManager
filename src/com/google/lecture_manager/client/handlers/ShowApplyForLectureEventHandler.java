package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.ShowApplyForLectureEvent;

public interface ShowApplyForLectureEventHandler extends EventHandler {
  void onApplyForLectureEvent(ShowApplyForLectureEvent event);
} 
