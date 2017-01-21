package com.google.lecture_manager.shared.model;

import com.google.lecture_manager.shared.UserTypes;

/**
 * Created by Cristi on 1/4/2017
 */
public class Teacher extends UserDTO {

  public Teacher() {
  }

  public Teacher(long id, String firstName, String lastName, String username, String email) {
    super(id, firstName, lastName, username, email, UserTypes.TEACHER.getId());
  }

  @Override
  public String toString() {
    return super.getFirstName() + " " + super.getLastName();
  }
}
