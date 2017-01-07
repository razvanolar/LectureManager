package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.LoadLecturesEvent;

public interface LoadLecturesEventHandler extends EventHandler {
  void reloadLectures(LoadLecturesEvent event);
} 
