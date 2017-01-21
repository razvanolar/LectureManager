package com.google.lecture_manager.client.utils.properties;

import com.google.gwt.editor.client.Editor;
import com.google.lecture_manager.shared.model.UserDTO;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created by Cristi on 1/4/2017.
 */
public interface UsersProperties extends PropertyAccess<UserDTO> {
  @Editor.Path("id")
  ModelKeyProvider<UserDTO> key();

  ValueProvider<UserDTO, Long> id();
  ValueProvider<UserDTO, String> firstName();
  ValueProvider<UserDTO, String> lastName();
  ValueProvider<UserDTO, String> userName();
  ValueProvider<UserDTO, String> email();
}
