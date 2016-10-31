package com.google.lecture_manager.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by razvanolar on 30.10.2016
 */
public class User implements IsSerializable {

  public User() {}

  private int id;
  private String firstName;
  private String lastName;
  private String userName;
  private String email;
  private String password;

  public User(int id, String firstName, String lastName, String userName, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
  }

  public User(String firstName, String lastName, String userName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUserName() {
    return userName;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
