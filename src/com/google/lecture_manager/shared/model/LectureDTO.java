package com.google.lecture_manager.shared.model;

import java.io.Serializable;

/**
 * Created by Cristi on 1/4/2017
 */
public class LectureDTO implements Serializable {
  private long id;
  private TeacherDTO teacher;
  private String lectureName;
  private String enrolmentKey;

  public LectureDTO() {}

  public LectureDTO(TeacherDTO teacher, String lectureName, String enrolmentKey) {
    this.teacher = teacher;
    this.lectureName = lectureName;
    this.enrolmentKey = enrolmentKey;
  }

  public LectureDTO(long id, TeacherDTO teacher, String lectureName, String enrolmentKey) {
    this(teacher, lectureName, enrolmentKey);
    this.id = id;
  }

  public LectureDTO(String lectureName, TeacherDTO teacher) {
    this.teacher = teacher;
    this.lectureName = lectureName;
  }

  public LectureDTO(Lecture lecture) {
    this(lecture.getId(), new TeacherDTO(lecture.getTeacher()), lecture.getLectureName(), lecture.getEnrolmentKey());
  }

  public void setTeacher(TeacherDTO teacher) {
    this.teacher = teacher;
  }

  public void setLectureName(String lectureName) {
    this.lectureName = lectureName;
  }

  public TeacherDTO getTeacher() {
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
