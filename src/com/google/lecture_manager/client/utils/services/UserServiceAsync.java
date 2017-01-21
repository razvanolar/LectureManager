package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.shared.model.TeacherDTO;
import com.google.lecture_manager.shared.model.UserDTO;

import java.util.List;

public interface UserServiceAsync {
  void addNewUser(UserDTO user, AsyncCallback<Void> async);
  void authenticate(String user, String password, AsyncCallback<UserDTO> async);
  void getAllUsers(AsyncCallback<List<UserDTO>> async);
  void editUser(UserDTO user, AsyncCallback<Void> async);
  void deleteUsers(List<UserDTO> users, AsyncCallback<Void> async);
  void getAllTeachers(AsyncCallback<List<TeacherDTO>> teachers);
}
