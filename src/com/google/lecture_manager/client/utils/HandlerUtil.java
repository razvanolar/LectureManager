package com.google.lecture_manager.client.utils;

import com.google.lecture_manager.client.events.ShowApplyForLectureEvent;
import com.google.lecture_manager.client.handlers.ShowApplyForLectureEventHandler;
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
    AppUtils.EVENT_BUS.addHandler(ShowApplyForLectureEvent.TYPE, new ShowApplyForLectureEventHandler() {
      public void onApplyForLectureEvent(ShowApplyForLectureEvent event) {
        ((Window) AbstractFactory.getWidget(ElementTypes.SHOW_APPLY_FOR_LECTURE)).show();
      }
    });
  }
}
