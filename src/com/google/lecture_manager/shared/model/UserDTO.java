package com.google.lecture_manager.shared.model;

import com.google.lecture_manager.shared.UserTypes;

import java.io.Serializable;

/**
 * Created by razvanolar on 30.10.2016
 */
public class UserDTO implements Serializable {

  public UserDTO() {}

  private long id;
  private String firstName;
  private String lastName;
  private String userName;
  private String email;
  private String password;
  private int typeId;

  public UserDTO(long id, String firstName, String lastName, String userName, String email, int typeId) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.typeId = typeId;
  }

  public UserDTO(String firstName, String lastName, String userName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.email = email;
    this.password = password;
  }

  public UserDTO(User user) {
    this.id = user.getId();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.userName = user.getUserName();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.typeId = user.getTypeId();
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

  public int getTypeId() {
    return typeId;
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

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  public UserTypes getType() {
    return UserTypes.fromId(typeId);
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setId(long id) {
    this.id = id;
  }
}
