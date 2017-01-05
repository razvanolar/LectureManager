package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.LoadUsersEvent;

public interface LoadUsersEventHandler extends EventHandler {
  void reloadUsers(LoadUsersEvent event);
} 
