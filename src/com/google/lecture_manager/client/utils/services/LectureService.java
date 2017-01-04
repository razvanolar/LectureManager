package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.tree.Tree;

/**
 * Created by razvanolar on 04.01.2017
 */
@RemoteServiceRelativePath("lectureService")
public interface LectureService extends RemoteService {
  Tree<FileData> getLecturesFilesForUser(long userId) throws Exception;
}
