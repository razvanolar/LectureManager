package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.AttendLectureEvent;

public interface AttendLectureEventHandler extends EventHandler {
  void onAttendLectureEvent(AttendLectureEvent event);
} 
