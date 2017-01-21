package com.google.lecture_manager.client.utils.properties;

import com.google.gwt.editor.client.Editor;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created by Cristi on 1/6/2017.
 */
public interface LectureProperties extends PropertyAccess<LectureDTO> {
  @Editor.Path("id")
  ModelKeyProvider<LectureDTO> key();

  ValueProvider<LectureDTO, Long> id();
  ValueProvider<LectureDTO, String> lectureName();
  ValueProvider<LectureDTO, String> enrolmentKey();
}
