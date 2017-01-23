package com.google.lecture_manager.server.jdbc.dao;

import com.google.lecture_manager.server.jdbc.JDBCUtil;
import com.google.lecture_manager.shared.UserTypes;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.google.lecture_manager.shared.model.TeacherDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristi on 1/6/2017
 */
public class LectureDAO {
  private Connection connection;

  public LectureDAO(Connection connection) {
    this.connection = connection;
  }

  public List<LectureDTO> getAllLectures() throws SQLException {
    List<LectureDTO> result = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      String query = "SELECT l.id, l.teacher_id, l.name, l.enrolment_key," +
              "u.id, u.first_name, u.last_name, u.email, u.user_name " +
              "FROM lectures l inner join users u on l.teacher_id = u.id where u.user_type = ?";
      statement = connection.prepareStatement(query);
      statement.setLong(1, UserTypes.TEACHER.getId());
      rs = statement.executeQuery();
      while (rs.next()){
        result.add(computeResultSet(rs));
      }
      return result;
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
      if (rs != null)
        JDBCUtil.getInstance().closeResultSet(rs);
    }
  }

  private LectureDTO computeResultSet(ResultSet rs) throws SQLException {
    return new LectureDTO(rs.getLong(1),
            new TeacherDTO(rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(9),
                    rs.getString(8)),
            rs.getString(3),
            rs.getString(4));
  }

  public void deleteLectures(List<LectureDTO> lectures) throws SQLException {
    PreparedStatement statement = null;
    try {
      String sql = "delete from lectures where id in (";
      for (int i=0; i<lectures.size(); i++) {
        if (i<lectures.size() - 1) {
          sql += lectures.get(i).getId() + ", ";
        } else {
          sql += lectures.get(i).getId() + ")";
        }
      }
      statement = connection.prepareStatement(sql);
      statement.executeUpdate();
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
    }
  }

  public void editLecture(LectureDTO lecture) throws SQLException {
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement("update lectures set teacher_id = ?, enrolment_key = ?, name = ? where id = ?");
      statement.setLong(1, lecture.getTeacher().getId());
      statement.setString(2, lecture.getEnrolmentKey());
      statement.setString(3, lecture.getLectureName());
      statement.setLong(4, lecture.getId());
      statement.executeUpdate();
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
    }
  }

  public void addLecture(LectureDTO lecture) throws SQLException {
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement("insert into lectures (teacher_id, name, enrolment_key) VALUES (?, ?, ?)");
      statement.setLong(1, lecture.getTeacher().getId());
      statement.setString(2, lecture.getLectureName());
      statement.setString(3, lecture.getEnrolmentKey());
      statement.executeUpdate();
    } finally {
      if (statement != null) {
        JDBCUtil.getInstance().closeStatement(statement);
      }
    }
  }
}
