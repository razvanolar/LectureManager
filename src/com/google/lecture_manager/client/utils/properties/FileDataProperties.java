package com.google.lecture_manager.client.utils.properties;

import com.google.gwt.editor.client.Editor;
import com.google.lecture_manager.shared.model.FileData;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created by razvanolar on 04.01.2017.
 */
public interface FileDataProperties extends PropertyAccess<FileData> {
  @Editor.Path("path")
  ModelKeyProvider<FileData> path();

  ValueProvider<FileData, String> name();
  ValueProvider<FileData, Long> length();
  ValueProvider<FileData, String> lastModified();
}
