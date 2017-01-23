package com.google.lecture_manager.shared.model;

import com.google.lecture_manager.shared.UserTypes;

/**
 * Created by Cristi on 1/4/2017
 */
public class TeacherDTO extends UserDTO {

  public TeacherDTO() {
  }

  public TeacherDTO(int id, String firstName, String lastName, String username, String email) {
    super(id, firstName, lastName, username, email, UserTypes.TEACHER.getId());
  }

  public TeacherDTO(Teacher teacher) {
    super(teacher.getId(), teacher.getFirstName(), teacher.getLastName(), teacher.getUserName(), teacher.getEmail(),
            UserTypes.TEACHER.getId());
  }

  @Override
  public String toString() {
    return super.getFirstName() + " " + super.getLastName();
  }
}
