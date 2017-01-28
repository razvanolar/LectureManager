package com.google.lecture_manager.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lecture_manager.client.utils.services.LectureService;
import com.google.lecture_manager.server.jdbc.JDBCUtil;
import com.google.lecture_manager.server.jdbc.dao.LectureDAO;
import com.google.lecture_manager.server.utils.FileUtil;
import com.google.lecture_manager.server.utils.ServerUtil;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.Lecture;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.google.lecture_manager.shared.model.tree.Node;
import com.google.lecture_manager.shared.model.tree.Tree;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by razvanolar on 04.01.2017
 */
public class LectureServiceImpl extends RemoteServiceServlet implements LectureService {

  private static final String attendedLecturesQuery = "SELECT lc.* FROM lectures lc INNER JOIN user_lecture_maps ulm ON lc.id = ulm.lecture_id WHERE ulm.user_id = :id_user";
  private static final String unatendedLecturesQuery = "SELECT lc.* FROM lectures lc LEFT JOIN user_lecture_maps ulm ON lc.id = ulm.lecture_id LEFT JOIN users us ON ulm.user_id = us.id WHERE us.id <> :id_user OR us.id IS NULL";

  @Override
  public Tree<FileData> getLecturesFilesForUser(int userId) throws Exception {
    List<LectureDTO> lectures = getAttendedLectures(userId);
    if (lectures == null)
      return null;

    Tree<FileData> tree = new Tree<>();
    for (LectureDTO lecture : lectures) {
      Node<FileData> node = FileUtil.getHierarchyForLecture(lecture);
      if (node != null)
        tree.addRoot(node);
    }
    return tree;
  }

  @Override
  public Node<FileData> getLectureFilesForUser(int lectureId, int userId) throws Exception {
    if (!checkUserAttendance(lectureId, userId))
      return null;
    return FileUtil.getHierarchyForLecture(getLectureById(lectureId));
  }

  @Override
  public Tree<FileData> getLectureFiles(LectureDTO lecture) throws Exception {
    Tree<FileData> tree = new Tree<>();

    Node<FileData> node = FileUtil.getHierarchyForLecture(lecture);
    if (node != null) {
      tree.addRoot(node);
    }

    return tree;
  }

  @Override
  public List<LectureDTO> getAllLectures() throws Exception {
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      List userList = session.createCriteria(Lecture.class).list();
      transaction.commit();
      if (userList == null || userList.isEmpty()) {
        return new ArrayList<>();
      }
      List<LectureDTO> result = new ArrayList<>();
      for (Object o : userList) {
        result.add(new LectureDTO((Lecture) o));
      }
      return result;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      session.clear();
      session.close();
    }
  }

  @Override
  public List<LectureDTO> getAttendedLectures(int userId) throws Exception {
    return getLecturesFromQuery(attendedLecturesQuery, userId);
  }

  @Override
  public List<LectureDTO> getUnattendedLectures(int userId) throws Exception {
    return getLecturesFromQuery(unatendedLecturesQuery, userId);
  }

  @Override
  public LectureDTO getLectureById(int lectureId) throws Exception {
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      Criteria criteria = session.createCriteria(Lecture.class);
      criteria.add(Restrictions.eq("id", lectureId));
      List list = criteria.list();
      transaction.commit();
      if (list != null && list.size() == 1)
        return new LectureDTO((Lecture) list.get(0));
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage());
    } finally {
      session.close();
    }
    return null;
  }

  @Override
  public void deleteLecture(List<LectureDTO> lectures) throws Exception {
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      for (LectureDTO temp : lectures) {
        Lecture lecture = new Lecture(temp);
        session.delete(lecture);
      }
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      session.close();
    }
  }

  @Override
  public void editLecture(LectureDTO temp) throws Exception {
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      Lecture lecture = new Lecture(temp);
      session.update(lecture);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      session.close();
    }
  }

  @Override
  public void addLecture(LectureDTO temp) throws Exception {
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    try {
      Transaction transaction = session.beginTransaction();
      Lecture lecture = new Lecture(temp);
      session.save(lecture);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      session.close();
    }
  }

  @Override
  public void addUserForLecture(int userId, int lectureId) throws Exception {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      LectureDAO dao = new LectureDAO(connection);
      dao.addUserForLecture(userId, lectureId);
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage());
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  private boolean checkUserAttendance(int lectureId, int userId) {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      LectureDAO dao = new LectureDAO(connection);
      return dao.checkUserAttendance(lectureId, userId);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
    return false;
  }

  private List<LectureDTO> getLecturesFromQuery(String queryString, int userId) throws Exception {
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    List<LectureDTO> lectures = null;
    try {
      Transaction transaction = session.beginTransaction();
      SQLQuery query = session.createSQLQuery(queryString);
      query.setParameter("id_user", userId);
      query.setCacheable(false);
      query.addEntity(Lecture.class);
      List list = query.list();
      transaction.commit();
      if (list != null) {
        lectures = new ArrayList<>(list.size());
        for (Object obj : list) {
          lectures.add(new LectureDTO((Lecture) obj));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      session.close();
    }

    if (lectures == null || lectures.isEmpty())
      return null;
    return lectures;
  }
}
