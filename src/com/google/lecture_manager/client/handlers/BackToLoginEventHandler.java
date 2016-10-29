package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.BackToLoginEvent;

/**
 * Created by razvanolar on 28.10.2016
 */
public interface BackToLoginEventHandler extends EventHandler {
  void onBackToLoginEvent(BackToLoginEvent backToLoginEvent);
}
