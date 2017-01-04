package com.google.lecture_manager.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.lecture_manager.shared.FileTypes;

import java.util.Date;

/**
 * Created by razvanolar on 04.01.2017
 */
public class FileData implements IsSerializable {

  private String name;
  private String path;
  private FileTypes type;
  private long length;
  private String lastModified;

  public FileData() {}

  public FileData(String name, String path, FileTypes type, long length, Date lastModified) {
    this.name = name;
    this.type = type;
    this.path = path;
    this.length = length;
    this.lastModified = lastModified != null ? lastModified.toString() : "";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FileTypes getType() {
    return type;
  }

  public void setType(FileTypes type) {
    this.type = type;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public long getLength() {
    return length;
  }

  public void setLength(long length) {
    this.length = length;
  }

  public String getLastModified() {
    return lastModified;
  }

  public void setLastModified(String lastModified) {
    this.lastModified = lastModified;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof FileData))
      return false;
    return this.path.equals(((FileData) object).getPath());
  }
}
