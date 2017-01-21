package com.google.lecture_manager.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lecture_manager.client.utils.services.UserService;
import com.google.lecture_manager.server.jdbc.JDBCUtil;
import com.google.lecture_manager.server.jdbc.dao.UserDAO;
import com.google.lecture_manager.server.utils.ServerUtil;
import com.google.lecture_manager.shared.InputValidator;
import com.google.lecture_manager.shared.model.Teacher;
import com.google.lecture_manager.shared.model.User;
import com.google.lecture_manager.shared.model.UserDTO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.util.List;

/**
 * Created by razvanolar on 30.10.2016
 */
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

  @Override
  public void addNewUser(UserDTO user) throws Exception {
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
  public UserDTO authenticate(String username, String password) throws Exception {
    if (InputValidator.isNullOrEmpty(username) || InputValidator.isNullOrEmpty(password))
      throw new Exception("Authentication failed due to incomplete data.");
    password = ServerUtil.getMD5().crypt(password);

    Session session = ServerUtil.SESSION_FACTORY.openSession();
    List userList = null;
    try {
      Criteria criteria = session.createCriteria(User.class);
      criteria.add(Restrictions.eq("userName", username));
      criteria.add(Restrictions.eq("password", password));
      userList = criteria.list();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      session.close();
    }

    if (userList == null || userList.isEmpty() || userList.size() > 1)
      return null;
    return new UserDTO((User) userList.get(0));
  }

  @Override
  public List<UserDTO> getAllUsers() throws Exception {
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
  public List<Teacher> getAllTeachers() throws Exception {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      UserDAO dao = new UserDAO(connection);
      return dao.getAllTeachers();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  @Override
  public void editUser(UserDTO user) throws Exception {
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
  public void deleteUsers(List<UserDTO> users) throws Exception {
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
