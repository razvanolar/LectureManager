package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.ManageUsers;

public interface ManageUsersHandler extends EventHandler {
  void onManageUsersEvent(ManageUsers event);
} 
