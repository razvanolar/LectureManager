package com.google.lecture_manager.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lecture_manager.client.utils.services.UserService;
import com.google.lecture_manager.server.jdbc.JDBCUtil;
import com.google.lecture_manager.server.jdbc.dao.UserDAO;
import com.google.lecture_manager.server.utils.ServerUtil;
import com.google.lecture_manager.shared.InputValidator;
import com.google.lecture_manager.shared.model.User;

import java.sql.Connection;
import java.util.List;

/**
 * Created by razvanolar on 30.10.2016
 */
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

  @Override
  public void addNewUser(User user) throws Exception {
    if (user == null || user.getPassword() == null || user.getPassword().isEmpty())
      throw new Exception("Can not add user NULL instance into db.");
    user.setPassword(ServerUtil.getMD5().crypt(user.getPassword()));

    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      UserDAO dao = new UserDAO(connection);
      dao.addUser(user);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  @Override
  public User authenticate(String username, String password) throws Exception {
    if (InputValidator.isNullOrEmpty(username) || InputValidator.isNullOrEmpty(password))
      throw new Exception("Authentication failed due to incomplete data.");
    password = ServerUtil.getMD5().crypt(password);

    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      UserDAO dao = new UserDAO(connection);
      return dao.getUser(username, password);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  @Override
  public List<User> getAllUsers() throws Exception {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      UserDAO dao = new UserDAO(connection);
      return dao.getAllUsers();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  @Override
  public void editUser(User user) throws Exception {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      UserDAO dao = new UserDAO(connection);
      dao.editUser(user);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  @Override
  public void deleteUsers(List<User> users) throws Exception {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      UserDAO dao = new UserDAO(connection);
      dao.deleteUsers(users);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }
}
