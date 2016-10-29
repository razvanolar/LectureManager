package com.google.lecture_manager.client.handlers;

import com.google.gwt.event.shared.EventHandler;
import com.google.lecture_manager.client.events.SignUpEvent;

/**
 * Created by razvanolar on 28.10.2016
 */
public interface SignUpEventHandler extends EventHandler {
  void onSignUpEvent(SignUpEvent signUpEvent);
}
