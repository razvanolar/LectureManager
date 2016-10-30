package com.google.lecture_manager.client.utils.factories;

import com.google.gwt.core.client.GWT;
import com.google.lecture_manager.client.utils.services.UserService;
import com.google.lecture_manager.client.utils.services.UserServiceAsync;

/**
 * Created by razvanolar on 30.10.2016
 */
public class ServiceFactory {

  private UserServiceAsync userService;

  public UserServiceAsync getUserService() {
    if (userService == null)
      userService = GWT.create(UserService.class);
    return userService;
  }
}
