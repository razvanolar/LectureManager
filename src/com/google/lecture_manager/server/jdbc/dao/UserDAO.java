package com.google.lecture_manager.server.jdbc.dao;

import com.google.lecture_manager.server.jdbc.JDBCUtil;
import com.google.lecture_manager.shared.UserTypes;
import com.google.lecture_manager.shared.model.TeacherDTO;
import com.google.lecture_manager.shared.model.UserDTO;

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

  public void addUser(UserDTO user) throws Exception {
    PreparedStatement statement = null;
    try {
      String query = "INSERT INTO users (first_name, last_name, email, user_name, password, user_type) VALUES (?, ?, ?, ?, ?, ?)";
      statement = connection.prepareStatement(query);
      statement.setString(1, user.getFirstName());
      statement.setString(2, user.getLastName());
      statement.setString(3, user.getEmail());
      statement.setString(4, user.getUserName());
      statement.setString(5, user.getPassword());
      if (user.getType() == null)
        statement.setInt(6, UserTypes.STUDENT.getId());
      else {
        statement.setInt(6, user.getType().getId());
      }
      statement.execute();
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
    }
  }

  public UserDTO getUser(String username, String password) throws Exception {
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

  private UserDTO computeResultSet(ResultSet result) throws Exception {
    int typeId = result.getInt(7);
    return new UserDTO(result.getInt(1),
            result.getString(2),
            result.getString(3),
            result.getString(5),
            result.getString(4),
            typeId);
  }

  public List<UserDTO> getAllUsers() throws Exception {
    List<UserDTO> result = new ArrayList<>();
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

  public List<TeacherDTO> getAllTeachers() throws SQLException {
    List<TeacherDTO> result = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = "SELECT * FROM users where user_type = ?";
      statement = connection.prepareStatement(query);
      statement.setLong(1, UserTypes.TEACHER.getId());
      rs = statement.executeQuery();
      while (rs.next()){
        result.add(new TeacherDTO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4)));
      }
      return result;
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
      if (rs != null)
        JDBCUtil.getInstance().closeResultSet(rs);
    }
  }

  public void editUser(UserDTO user) throws Exception {
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement("UPDATE users set first_name = ?, last_name = ? , email = ?, user_name = ?, user_type = ? where id = ?");
      statement.setString(1, user.getFirstName());
      statement.setString(2, user.getLastName());
      statement.setString(3, user.getEmail());
      statement.setString(4, user.getUserName());
      statement.setLong(5, user.getType().getId());
      statement.setLong(6, user.getId());
      statement.executeUpdate();
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
    }
  }

  public void deleteUsers(List<UserDTO> users) throws SQLException {
    PreparedStatement statement = null;
    try {
      String sql = "delete from users where id in (";
      for (int i=0; i<users.size(); i++) {
        if (i<users.size() - 1) {
          sql += users.get(i).getId() + ", ";
        } else {
          sql += users.get(i).getId() + ")";
        }
      }
      statement = connection.prepareStatement(sql);
      statement.executeUpdate();
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
    }
  }
}
