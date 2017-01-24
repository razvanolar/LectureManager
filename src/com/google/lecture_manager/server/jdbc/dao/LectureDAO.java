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

  public void addUserForLecture(int userId, int lectureId) throws SQLException {
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement("INSERT INTO user_lecture_maps (user_id, lecture_id) VALUES (?, ?)");
      statement.setInt(1, userId);
      statement.setInt(2, lectureId);
      statement.executeUpdate();
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
    }
  }

  public boolean checkUserAttendance(int lectureId, int userId) throws SQLException {
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
      statement = connection.prepareStatement("SELECT * FROM lectures lc INNER JOIN user_lecture_maps ulm ON lc.id = ulm.lecture_id WHERE ulm.lecture_id = ? AND ulm.user_id = ?");
      statement.setInt(1, lectureId);
      statement.setInt(2, userId);
      resultSet = statement.executeQuery();
      return resultSet.next();
    } finally {
      if (statement != null)
        JDBCUtil.getInstance().closeStatement(statement);
      if (resultSet != null)
        JDBCUtil.getInstance().closeResultSet(resultSet);
    }
  }
}
