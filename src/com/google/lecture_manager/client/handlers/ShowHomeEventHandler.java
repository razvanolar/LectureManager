package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.ShowHomeEvent;

public interface ShowHomeEventHandler extends EventHandler {
  void onShowHomeEvent(ShowHomeEvent event);
} 
