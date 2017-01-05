package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.lecture_manager.shared.model.User;

import java.util.List;

/**
 * Created by razvanolar on 30.10.2016
 */
@RemoteServiceRelativePath("userService")
public interface UserService extends RemoteService {
  void addNewUser(User user) throws Exception;
  User authenticate(String user, String password) throws Exception;
  List<User> getAllUsers() throws Exception;
  void editUser(User user) throws Exception;
}
