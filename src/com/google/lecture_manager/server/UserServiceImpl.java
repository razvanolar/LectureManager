package com.google.lecture_manager.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lecture_manager.client.utils.services.UserService;
import com.google.lecture_manager.server.utils.ServerUtil;
import com.google.lecture_manager.shared.InputValidator;
import com.google.lecture_manager.shared.UserTypes;
import com.google.lecture_manager.shared.model.TeacherDTO;
import com.google.lecture_manager.shared.model.User;
import com.google.lecture_manager.shared.model.UserDTO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by razvanolar on 30.10.2016
 */
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

  @Override
  public void addNewUser(UserDTO userDTO) throws Exception {
    if (userDTO == null || userDTO.getPassword() == null || userDTO.getPassword().isEmpty())
      throw new Exception("Can not add user NULL instance into db.");
    userDTO.setPassword(ServerUtil.getMD5().crypt(userDTO.getPassword()));
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      User user = new User(userDTO);
      session.save(user);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      session.close();
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
      Transaction transaction = session.beginTransaction();
      Criteria criteria = session.createCriteria(User.class);
      criteria.add(Restrictions.eq("userName", username));
      criteria.add(Restrictions.eq("password", password));
      userList = criteria.list();
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      session.clear();
      session.close();
    }

    if (userList == null || userList.isEmpty() || userList.size() > 1)
      return null;
    return new UserDTO((User) userList.get(0));
  }

  @Override
  public List<UserDTO> getAllUsers() throws Exception {
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      List userList = session.createCriteria(User.class).list();
      transaction.commit();
      if (userList == null || userList.isEmpty()) {
        return new ArrayList<>();
      }
      List<UserDTO> result = new ArrayList<>();
      for (Object o : userList) {
        result.add(new UserDTO((User) o));
      }
      return result;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      session.clear();
      session.close();
    }
  }

  @Override
  public List<TeacherDTO> getAllTeachers() throws Exception {
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      Criteria criteria = session.createCriteria(User.class);
      criteria.add(Restrictions.eq("typeId", UserTypes.TEACHER.getId()));
      List userList = criteria.list();
      transaction.commit();
      if (userList == null || userList.isEmpty()) {
        return new ArrayList<>();
      }
      List<TeacherDTO> result = new ArrayList<>();
      for (Object o : userList) {
        result.add(new TeacherDTO((User) o));
      }
      return result;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      session.clear();
      session.close();
    }
  }

  @Override
  public void editUser(UserDTO userDTO) throws Exception {
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      User user = new User(userDTO);
      session.update(user);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      session.close();
    }
  }

  @Override
  public void deleteUsers(List<UserDTO> users) throws Exception {
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      for (UserDTO userDTO : users) {
        User user = new User(userDTO);
        session.delete(user);
      }
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      session.close();
    }
  }
}
