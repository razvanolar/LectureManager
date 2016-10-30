package com.google.lecture_manager.server.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by razvanolar on 30.10.2016
 */
public class JDBCUtil {

  private static final String HOST = "jdbc:mysql://localhost/lecturemanager";
  private static final String USER = "manager";
  private static final String PASSWORD = "manager";

  private static JDBCUtil INSTANCE;

  public JDBCUtil() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void closeStatement(Statement statement) {
    try {
      if (statement != null)
        statement.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void closeResultSet(ResultSet resultSet) {
    try {
      if (resultSet != null)
        resultSet.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void closeConnection(Connection connection) {
    try {
      if (connection != null)
        connection.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public Connection getConnection() throws Exception {
    try {
      return DriverManager.getConnection(HOST, USER, PASSWORD);
    } catch (Exception ex) {
      System.out.println("Repository - getConnection");
      ex.printStackTrace();
      throw ex;
    }
  }

  public static JDBCUtil getInstance() {
    if (INSTANCE == null)
      INSTANCE = new JDBCUtil();
    return INSTANCE;
  }
}
