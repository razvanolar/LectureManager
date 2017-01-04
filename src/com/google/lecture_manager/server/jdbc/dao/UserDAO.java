package com.google.lecture_manager.server.jdbc.dao;

import com.google.lecture_manager.server.jdbc.JDBCUtil;
import com.google.lecture_manager.shared.UserTypes;
import com.google.lecture_manager.shared.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

  public User getUser(String username, String password) throws Exception {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      String query = "SELECT * FROM users WHERE user_name = ? AND password = ?";
      statement = connection.prepareStatement(query);
      statement.setString(1, username);
      statement.setString(2, password);
      result = statement.executeQuery();
      if (result.next())
        return computeResultSet(result);
      return null;
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
      if (result != null)
        JDBCUtil.getInstance().closeResultSet(result);
    }
  }

  private User computeResultSet(ResultSet result) throws Exception {
    int id = result.getInt(7);
    UserTypes type = UserTypes.fromId(id);
    if (type == null) {
      System.out.println("Unable to determine user type for id: " + id);
      throw new Exception("Unable to determine user type");
    }
    return new User(result.getInt(1),
            result.getString(2),
            result.getString(3),
            result.getString(5),
            result.getString(4),
            type);
  }

  public List<User> getAllUsers() throws Exception {
    List<User> result = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = "SELECT * FROM users";
      statement = connection.prepareStatement(query);
      rs = statement.executeQuery();
      while (rs.next()){
        result.add(computeResultSet(rs));
      }
      return result;
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
      if (rs != null)
        JDBCUtil.getInstance().closeResultSet(rs);
    }
  }
}
