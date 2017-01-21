package com.google.lecture_manager.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.lecture_manager.shared.UserTypes;

/**
 * Created by razvanolar on 30.10.2016
 */
public class User implements IsSerializable {

  public User() {}

  private long id;
  private String firstName;
  private String lastName;
  private String userName;
  private String email;
  private String password;
  private UserTypes type;

  public User(long id, String firstName, String lastName, String userName, String email, int typeId) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.type = UserTypes.fromId(typeId);
  }

  public User(String firstName, String lastName, String userName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.password = password;
  }

  public long getId() {
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

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserTypes getType() {
    return type;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setType(UserTypes type) {
    this.type = type;
  }
}
