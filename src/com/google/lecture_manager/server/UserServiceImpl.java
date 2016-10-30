package com.google.lecture_manager.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lecture_manager.client.utils.services.UserService;
import com.google.lecture_manager.server.jdbc.JDBCUtil;
import com.google.lecture_manager.server.jdbc.dao.UserDAO;
import com.google.lecture_manager.shared.model.User;

import java.sql.Connection;

/**
 * Created by razvanolar on 30.10.2016
 */
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

  @Override
  public void addNewUser(User user) throws Exception {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      UserDAO dao = new UserDAO(connection);
      dao.addUser(user);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw e;
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }
}
