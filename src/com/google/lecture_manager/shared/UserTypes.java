package com.google.lecture_manager.shared;

/**
 * Created by razvanolar on 31.10.2016
 */
public enum UserTypes {

  ADMIN(1, false, true, true), TEACHER(2, false, false, true), STUDENT(3, true, false, false);

  int id;
  boolean applyForLecture;
  boolean manageUsers;
  boolean manageLectures;

  UserTypes(int id, boolean applyForLecture, boolean manageUsers, boolean manageLectures) {
    this.id = id;
    this.applyForLecture = applyForLecture;
    this.manageUsers = manageUsers;
    this.manageLectures = manageLectures;
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

  public boolean hasApplyForLectureRight() {
    return applyForLecture;
  }

  public boolean hasManageUsersRight() {
    return manageUsers;
  }

  public boolean hasManageLecturesRight() {
    return manageLectures;
  }
}
