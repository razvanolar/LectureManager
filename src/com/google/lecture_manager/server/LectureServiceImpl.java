package com.google.lecture_manager.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lecture_manager.client.utils.services.LectureService;
import com.google.lecture_manager.server.jdbc.JDBCUtil;
import com.google.lecture_manager.server.jdbc.dao.LectureDAO;
import com.google.lecture_manager.server.utils.ServerUtil;
import com.google.lecture_manager.shared.FileTypes;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.Lecture;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.google.lecture_manager.shared.model.tree.Node;
import com.google.lecture_manager.shared.model.tree.Tree;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by razvanolar on 04.01.2017
 */
public class LectureServiceImpl extends RemoteServiceServlet implements LectureService {

  @Override
  public Tree<FileData> getLecturesFilesForUser(long userId) throws Exception {
    Tree<FileData> tree = new Tree<>();
    Node<FileData> node1 = new Node<>(new FileData("Lecture_1", "1", FileTypes.FOLDER, -1, null));
    Node<FileData> node2 = new Node<>(new FileData("Lecture_2", "2", FileTypes.FOLDER, -1, null));
    Node<FileData> node3 = new Node<>(new FileData("Lecture_3", "3", FileTypes.FOLDER, -1, null));
    Node<FileData> node4 = new Node<>(new FileData("Lecture_4", "4", FileTypes.FOLDER, -1, null));

    Node<FileData> node11 = new Node<>(new FileData("lecture1_file1", "lecturemanager\\app_files\\lectures\\1\\generateComponent.bat", FileTypes.REGULAR, 125, null));
    Node<FileData> node12 = new Node<>(new FileData("lecture1_file2", "lecturemanager\\app_files\\lectures\\1\\generateComponent.py", FileTypes.REGULAR, 225, null));
    Node<FileData> node13 = new Node<>(new FileData("lecture1_file3", "lecturemanager\\app_files\\lectures\\1\\generateEvent.bat", FileTypes.REGULAR, 325, null));

    Node<FileData> node21 = new Node<>(new FileData("lecture2_file1", "lecturemanager\\app_files\\lectures\\1\\mysqlDumpScript.bat", FileTypes.REGULAR, 125, null));
    Node<FileData> node22 = new Node<>(new FileData("lecture2_file2", "22", FileTypes.REGULAR, 225, null));
    Node<FileData> node23 = new Node<>(new FileData("lecture2_file3", "23", FileTypes.REGULAR, 325, null));

    node1.addChild(node11);
    node1.addChild(node12);
    node1.addChild(node13);

    node2.addChild(node21);
    node2.addChild(node22);
    node2.addChild(node23);

    tree.addRoot(node1);
    tree.addRoot(node2);
    tree.addRoot(node3);
    tree.addRoot(node4);
    return tree;
  }

  @Override
  public List<LectureDTO> getAllLectures() throws Exception {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      LectureDAO dao = new LectureDAO(connection);
      return dao.getAllLectures();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  @Override
  public List<LectureDTO> getUnattendedLectures(int userId) throws Exception {
    Session session = ServerUtil.SESSION_FACTORY.openSession();
    List<LectureDTO> lectures = null;
    try {
      SQLQuery query = session.createSQLQuery("SELECT lc.* FROM lectures lc LEFT JOIN user_lecture_maps ulm ON lc.id = ulm.lecture_id LEFT JOIN users us ON ulm.user_id = us.id WHERE us.id <> :id_user OR us.id IS NULL");
      query.setParameter("id_user", userId);
      query.addEntity(Lecture.class);
      List list = query.list();
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

  @Override
  public void deleteLecture(List<LectureDTO> lectures) throws Exception {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      LectureDAO dao = new LectureDAO(connection);
      dao.deleteLectures(lectures);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  @Override
  public void editLecture(LectureDTO lecture) throws Exception {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      LectureDAO dao = new LectureDAO(connection);
      dao.editLecture(lecture);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }

  @Override
  public void addLecture(LectureDTO temp) throws Exception {
    Connection connection = null;
    try {
      connection = JDBCUtil.getInstance().getConnection();
      LectureDAO dao = new LectureDAO(connection);
      dao.addLecture(temp);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new Exception(e.getMessage());
    } finally {
      if (connection != null)
        JDBCUtil.getInstance().closeConnection(connection);
    }
  }
}
