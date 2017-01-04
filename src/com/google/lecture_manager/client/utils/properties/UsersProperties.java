package com.google.lecture_manager.client.utils.properties;

import com.google.gwt.editor.client.Editor;
import com.google.lecture_manager.shared.model.User;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created by Cristi on 1/4/2017.
 */
public interface UsersProperties extends PropertyAccess<User> {
  @Editor.Path("id")
  ModelKeyProvider<User> key();

  ValueProvider<User, Long> id();
  ValueProvider<User, String> firstName();
  ValueProvider<User, String> lastName();
  ValueProvider<User, String> userName();
  ValueProvider<User, String> email();
}
