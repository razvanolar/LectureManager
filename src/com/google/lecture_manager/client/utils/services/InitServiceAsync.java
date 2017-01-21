package com.google.lecture_manager.client.utils.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public interface InitServiceAsync {
  void initServerData(AsyncCallback<Void> async);
}
