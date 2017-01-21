package com.google.lecture_manager.client.utils.factories;

import com.google.gwt.core.client.GWT;
import com.google.lecture_manager.client.utils.properties.LectureProperties;

/**
 * Created by razvanolar on 21.01.2017
 */
public class PropertiesFactory {

  private LectureProperties lectureProperties;

  public LectureProperties getLecturesProperties() {
    if (lectureProperties == null)
      lectureProperties = GWT.create(LectureProperties.class);
    return lectureProperties;
  }
}
