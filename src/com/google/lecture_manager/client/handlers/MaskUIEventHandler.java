package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.MaskUIEvent;

public interface MaskUIEventHandler extends EventHandler {
  void onMaskUIEvent(MaskUIEvent event);
} 
