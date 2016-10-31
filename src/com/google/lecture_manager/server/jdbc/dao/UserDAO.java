package com.google.lecture_manager.server.jdbc.dao;

import com.google.lecture_manager.server.jdbc.JDBCUtil;
import com.google.lecture_manager.shared.UserTypes;
import com.google.lecture_manager.shared.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by razvanolar on 30.10.2016
 */
public class UserDAO {

  private Connection connection;

  public UserDAO(Connection connection) {
    this.connection = connection;
  }

  public void addUser(User user) throws Exception {
    PreparedStatement statement = null;
    try {
      String query = "INSERT INTO users (first_name, last_name, email, user_name, password, user_type) VALUES (?, ?, ?, ?, ?, ?)";
      statement = connection.prepareStatement(query);
      statement.setString(1, user.getFirstName());
      statement.setString(2, user.getLastName());
      statement.setString(3, user.getEmail());
      statement.setString(4, user.getUserName());
      statement.setString(5, user.getPassword());
      statement.setInt(6, UserTypes.STUDENT.getId());
      statement.execute();
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
    }
  }
}
