package com.google.lecture_manager.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lecture_manager.client.utils.services.LectureService;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.tree.Tree;

/**
 * Created by razvanolar on 04.01.2017
 */
public class LectureServiceImpl extends RemoteServiceServlet implements LectureService {

  @Override
  public Tree<FileData> getLecturesFilesForUser(long userId) throws Exception {
    return null;
  }
}
