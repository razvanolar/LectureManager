package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.Lecture;
import com.google.lecture_manager.shared.model.tree.Tree;

import java.util.List;

/**
 * Created by razvanolar on 04.01.2017
 */
@RemoteServiceRelativePath("lectureService")
public interface LectureService extends RemoteService {
  Tree<FileData> getLecturesFilesForUser(long userId) throws Exception;
  List<Lecture> getAllLectures() throws Exception;
  void deleteLecture(List<Lecture> lectures) throws Exception;
  void editLecture(Lecture lecture) throws Exception;
  void addLecture(Lecture temp) throws Exception;
}
