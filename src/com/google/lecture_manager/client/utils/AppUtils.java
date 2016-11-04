package com.google.lecture_manager.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.lecture_manager.client.utils.factories.ServiceFactory;

/**
 * Created by razvanolar on 28.10.2016
 */
public class AppUtils {

  public static EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);

  public static ServiceFactory SERVICE_FACTORY = new ServiceFactory();

  public static native void log(String message)/*-{
    console.info(message);
  }-*/;
}
