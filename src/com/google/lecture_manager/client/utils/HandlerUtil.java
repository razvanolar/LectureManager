package com.google.lecture_manager.client.utils;

import com.google.lecture_manager.client.events.ApplyForLectureEvent;
import com.google.lecture_manager.client.handlers.ApplyForLectureEventHandler;
import com.google.lecture_manager.client.utils.factories.AbstractFactory;
import com.sencha.gxt.widget.core.client.Window;

/**
 * Created by razvanolar on 21.01.2017
 */
public class HandlerUtil {

  public HandlerUtil() {
    initHandlers();
  }

  private void initHandlers() {
    AppUtils.EVENT_BUS.addHandler(ApplyForLectureEvent.TYPE, new ApplyForLectureEventHandler() {
      public void onApplyForLectureEvent(ApplyForLectureEvent event) {
        ((Window) AbstractFactory.getWidget(ElementTypes.APPLY_FOR_LECTURE)).show();
      }
    });
  }
}
