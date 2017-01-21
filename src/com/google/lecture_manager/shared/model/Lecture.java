package com.google.lecture_manager.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Created by Cristi on 1/4/2017
 */
public class Lecture implements Serializable {
  private long id;
  private Teacher teacher;
  private String lectureName;
  private String enrolmentKey;

  public Lecture() {}

  public Lecture(Teacher teacher, String lectureName, String enrolmentKey) {
    this.teacher = teacher;
    this.lectureName = lectureName;
    this.enrolmentKey = enrolmentKey;
  }

  public Lecture(long id, Teacher teacher, String lectureName, String enrolmentKey) {
    this(teacher, lectureName, enrolmentKey);
    this.id = id;
  }

  public Lecture(String lectureName, Teacher teacher) {
    this.teacher = teacher;
    this.lectureName = lectureName;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public void setLectureName(String lectureName) {
    this.lectureName = lectureName;
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

  public void setEnrolmentKey(String enrolmentKey) {
    this.enrolmentKey = enrolmentKey;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String toString() {
    return id + " " + lectureName + " " + enrolmentKey;
  }
}
