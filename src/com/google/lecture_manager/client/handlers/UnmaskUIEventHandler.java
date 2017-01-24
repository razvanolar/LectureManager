package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.UnmaskUIEvent;

public interface UnmaskUIEventHandler extends EventHandler {
  void onUnmaskUIEvent(UnmaskUIEvent event);
} 
