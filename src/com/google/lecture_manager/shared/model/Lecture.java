package com.google.lecture_manager.shared.model;

/**
 * Created by razvanolar on 21.01.2017
 */
public class Lecture {

  public Lecture() {}

  private int id;
  private Teacher teacher;
  private String lectureName;
  private String enrolmentKey;

  public int getId() {
    return id;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public String getLectureName() {
    return lectureName;
  }

  public String getEnrolmentKey() {
    return enrolmentKey;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public void setLectureName(String lectureName) {
    this.lectureName = lectureName;
  }

  public void setEnrolmentKey(String enrolmentKey) {
    this.enrolmentKey = enrolmentKey;
  }
}
