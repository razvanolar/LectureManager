package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.ManageLectures;

public interface ManageLecturesHandler extends EventHandler {
  void onManageLecturesEvent(ManageLectures event);
} 
