package com.google.lecture_manager.shared.model;

import com.google.lecture_manager.shared.UserTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristi on 1/4/2017.
 */
public class Teacher extends User {

  List<Lecture> teachedLectures = new ArrayList<>();

  public Teacher() {
  }

  public Teacher(long id, String username, String password, String firstName, String lastName, String email) {
    super(id, firstName, lastName, username, email, UserTypes.TEACHER);
    setPassword(password);
  }


  public List<Lecture> getTeachedLectures() {
    return teachedLectures;
  }

  public void addLecture(Lecture lecture) {
    if (!teachedLectures.contains(lecture)) {
      teachedLectures.add(lecture);
    } else {
      throw new RuntimeException("You are already enrolled to this lecture!");
    }
  }

  @Override
  public String toString() {
    return super.getFirstName() + " " + super.getLastName();
  }
}
