package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.PopulateLectureFileEvent;

public interface PopulateLectureFileEventHandler extends EventHandler {
  void loadFiles(PopulateLectureFileEvent event);
} 
