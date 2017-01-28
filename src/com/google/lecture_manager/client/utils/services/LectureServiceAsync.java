package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.google.lecture_manager.shared.model.tree.Node;
import com.google.lecture_manager.shared.model.tree.Tree;

import java.util.List;

public interface LectureServiceAsync {
  void getLecturesFilesForUser(int userId, AsyncCallback<Tree<FileData>> async);
  void getAllLectures(AsyncCallback<List<LectureDTO>> async);
  void getUnattendedLectures(int userId, AsyncCallback<List<LectureDTO>> async);
  void deleteLecture(List<LectureDTO> lectures, AsyncCallback<Void> asyncCallback);
  void editLecture(LectureDTO lecture, AsyncCallback<Void> async);
  void addLecture(LectureDTO temp, AsyncCallback<Void> asyncCallback);

  void addUserForLecture(int userId, int lectureId, AsyncCallback<Void> async);

  void getAttendedLectures(int userId, AsyncCallback<List<LectureDTO>> async);

  void getLectureFilesForUser(int lectureId, int userId, AsyncCallback<Node<FileData>> async);

  void getLectureById(int lectureId, AsyncCallback<LectureDTO> async);

  void getLectureFiles(LectureDTO lecture, AsyncCallback<Tree<FileData>> async);
}
