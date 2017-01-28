package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.FilesEvent;

public interface FilesEventHandler extends EventHandler {
  void onOpenFileManagementEvent(FilesEvent event);
} 
