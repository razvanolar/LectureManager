package com.google.lecture_manager.server.jdbc.dao;

import com.google.lecture_manager.shared.model.User;

import java.sql.Connection;

/**
 * Created by razvanolar on 30.10.2016
 */
public class UserDAO {

  private Connection connection;

  public UserDAO(Connection connection) {
    this.connection = connection;
  }

  public void addUser(User user) throws Exception {
    if (user == null)
      throw new Exception("Can not add user NULL instance into db.");
    System.out.println("-- add user here --");
  }
}
