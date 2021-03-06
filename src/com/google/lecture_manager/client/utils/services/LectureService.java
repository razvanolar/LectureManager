package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.google.lecture_manager.shared.model.tree.Node;
import com.google.lecture_manager.shared.model.tree.Tree;

import java.util.List;

/**
 * Created by razvanolar on 04.01.2017
 */
@RemoteServiceRelativePath("lectureService")
public interface LectureService extends RemoteService {
  Tree<FileData> getLecturesFilesForUser(int userId) throws Exception;
  Node<FileData> getLectureFilesForUser(int lectureId, int userId) throws Exception;
  Tree<FileData> getLectureFiles(LectureDTO lecture) throws Exception;
  List<LectureDTO> getAllLectures(int teacherId) throws Exception;
  List<LectureDTO> getAttendedLectures(int userId) throws Exception;
  List<LectureDTO> getUnattendedLectures(int userId) throws Exception;
  LectureDTO getLectureById(int lectureId) throws Exception;
  void deleteLecture(List<LectureDTO> lectures) throws Exception;
  void editLecture(LectureDTO lecture) throws Exception;
  void addLecture(LectureDTO temp) throws Exception;
  void addUserForLecture(int userId, int lectureId) throws Exception;

  void deleteLectureFile(String path) throws Exception;
}
