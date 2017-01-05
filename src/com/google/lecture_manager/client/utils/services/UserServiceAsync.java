package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.shared.model.User;

import java.util.List;

public interface UserServiceAsync {
  void addNewUser(User user, AsyncCallback<Void> async);
  void authenticate(String user, String password, AsyncCallback<User> async);
  void getAllUsers(AsyncCallback<List<User>> async);
  void editUser(User user, AsyncCallback<Void> async);
}
