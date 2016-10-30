package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.lecture_manager.shared.model.User;

/**
 * Created by razvanolar on 30.10.2016
 */
@RemoteServiceRelativePath("userService")
public interface UserService extends RemoteService {
  void addNewUser(User user) throws Exception;
}