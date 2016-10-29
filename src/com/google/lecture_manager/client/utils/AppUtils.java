package com.google.lecture_manager.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

/**
 * Created by razvanolar on 28.10.2016
 */
public class AppUtils {

  public static EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);

  public static native void log(String message)/*-{
    console.info(message);
  }-*/;
}
