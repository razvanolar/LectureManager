package com.google.lecture_manager.client.utils.factories;

import com.google.gwt.core.client.GWT;
import com.google.lecture_manager.client.utils.services.*;

/**
 * Created by razvanolar on 30.10.2016
 */
public class ServiceFactory {

  private InitServiceAsync initService;
  private UserServiceAsync userService;
  private LectureServiceAsync lectureService;

  public InitServiceAsync getInitService() {
    if (initService == null)
      initService = GWT.create(InitService.class);
    return initService;
  }

  public UserServiceAsync getUserService() {
    if (userService == null)
      userService = GWT.create(UserService.class);
    return userService;
  }

  public LectureServiceAsync getLectureService() {
    if (lectureService == null)
      lectureService = GWT.create(LectureService.class);
    return lectureService;
  }
}
