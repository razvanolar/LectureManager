package com.google.lecture_manager.shared.model;

/**
 * Created by razvanolar on 21.01.2017
 */
public class Teacher {

  private int id;
  private String firstName;
  private String lastName;
  private String userName;
  private String email;
  private String password;
  private int typeId;

  public Teacher() {}

  public Teacher(TeacherDTO teacher) {
    this.id = teacher.getId();
    this.firstName = teacher.getFirstName();
    this.lastName = teacher.getLastName();
    this.userName = teacher.getUserName();
    this.email = teacher.getUserName();
    this.password = teacher.getPassword();
    this.typeId = teacher.getTypeId();
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

  public void setPassword(String password) {
    this.password = password;
  }

  public void setId(int id) {
    this.id = id;
  }

}
