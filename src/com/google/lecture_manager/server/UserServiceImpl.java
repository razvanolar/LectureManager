package com.google.lecture_manager.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lecture_manager.client.utils.services.UserService;
import com.google.lecture_manager.shared.model.User;

/**
 * Created by razvanolar on 30.10.2016
 */
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

  @Override
  public void addNewUser(User user) throws Exception {
    System.out.println("---- add new user ----");
  }
}
