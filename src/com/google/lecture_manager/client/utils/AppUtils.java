package com.google.lecture_manager.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.lecture_manager.client.utils.factories.ServiceFactory;
import com.google.lecture_manager.shared.UserTypes;
import com.google.lecture_manager.shared.model.User;

/**
 * Created by razvanolar on 28.10.2016
 */
public class AppUtils {

  private static AppUtils INSTANCE;

  public static EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);

  public static ServiceFactory SERVICE_FACTORY = new ServiceFactory();

  public static Icons ICONS = GWT.create(Icons.class);

  private User authenticatedUser;

  private AppUtils() {}

  public User getAuthenticatedUser() {
    return authenticatedUser;
  }

  public void setAuthenticatedUser(User authenticatedUser) {
    this.authenticatedUser = authenticatedUser;
  }

  public boolean isAdmin() {
    return authenticatedUser != null && authenticatedUser.getType() == UserTypes.ADMIN;
  }

  public static AppUtils getInstance() {
    if (INSTANCE == null)
      INSTANCE = new AppUtils();
    return INSTANCE;
  }

  public static native void log(String message)/*-{
    console.info(message);
  }-*/;
}
