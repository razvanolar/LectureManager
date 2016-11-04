package com.google.lecture_manager.shared;

/**
 * Created by razvanolar on 31.10.2016
 */
public enum UserTypes {

  ADMIN(1), TEACHER(2), STUDENT(3);

  int id;

  UserTypes(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public static UserTypes fromId(int id) {
    for (UserTypes type : values()) {
      if (type.getId() == id)
        return type;
    }
    return null;
  }
}
