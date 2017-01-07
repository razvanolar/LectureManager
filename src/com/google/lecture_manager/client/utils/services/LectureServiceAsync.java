package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.Lecture;
import com.google.lecture_manager.shared.model.tree.Tree;

import java.util.List;

public interface LectureServiceAsync {
  void getLecturesFilesForUser(long userId, AsyncCallback<Tree<FileData>> async);
  void getAllLectures(AsyncCallback<List<Lecture>> async);
  void deleteLecture(List<Lecture> lectures, AsyncCallback<Void> asyncCallback);
  void editLecture(Lecture lecture, AsyncCallback<Void> async);
  void addLecture(Lecture temp, AsyncCallback<Void> asyncCallback);
}
