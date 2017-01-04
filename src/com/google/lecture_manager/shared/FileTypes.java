package com.google.lecture_manager.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by razvanolar on 04.01.2017
 */
public enum FileTypes implements IsSerializable {
  FOLDER(1), REGULAR(1);

  private long id;

  private FileTypes(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public FileTypes getFileTypeById(long id) {
    for (FileTypes type : values())
      if (type.getId() == id)
        return type;
    return null;
  }
}
