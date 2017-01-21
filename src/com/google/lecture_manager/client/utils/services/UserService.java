package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.lecture_manager.shared.model.TeacherDTO;
import com.google.lecture_manager.shared.model.UserDTO;

import java.util.List;

/**
 * Created by razvanolar on 30.10.2016
 */
@RemoteServiceRelativePath("userService")
public interface UserService extends RemoteService {
  void addNewUser(UserDTO user) throws Exception;
  UserDTO authenticate(String user, String password) throws Exception;
  List<UserDTO> getAllUsers() throws Exception;
  void editUser(UserDTO user) throws Exception;
  void deleteUsers(List<UserDTO> users) throws Exception;
  List<TeacherDTO> getAllTeachers() throws Exception;
}
