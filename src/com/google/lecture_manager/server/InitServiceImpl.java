package com.google.lecture_manager.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lecture_manager.client.utils.services.InitService;
import com.google.lecture_manager.server.utils.FileUtil;
import com.google.lecture_manager.server.utils.ServerUtil;

/**
 * Created by razvanolar on 21.01.2017
 */
public class InitServiceImpl extends RemoteServiceServlet implements InitService {

  @Override
  public void initServerData() throws Exception {
    try {
      System.out.println("Start initializing session factory...");
      ServerUtil.initSessionFactory();
      System.out.println("Session factory initialized successfully");
      FileUtil.loadDiskPath();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
}
