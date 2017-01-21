package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by razvanolar on 21.01.2017
 */
@RemoteServiceRelativePath("initService")
public interface InitService extends RemoteService {
  void initServerData() throws Exception;
}
