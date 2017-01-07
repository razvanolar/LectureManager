package com.google.lecture_manager.client.utils.properties;

import com.google.gwt.editor.client.Editor;
import com.google.lecture_manager.shared.model.Lecture;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created by Cristi on 1/6/2017.
 */
public interface LectureProperties extends PropertyAccess<Lecture> {
  @Editor.Path("id")
  ModelKeyProvider<Lecture> key();

  ValueProvider<Lecture, Long> id();
  ValueProvider<Lecture, String> lectureName();
  ValueProvider<Lecture, String> enrolmentKey();
}
