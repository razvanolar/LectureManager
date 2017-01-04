package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.tree.Tree;

public interface LectureServiceAsync {
  void getLecturesFilesForUser(long userId, AsyncCallback<Tree<FileData>> async);
}
