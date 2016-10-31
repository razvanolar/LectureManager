package com.google.lecture_manager.client.utils;

/**
 * Created by razvanolar on 27.10.2016
 */
public abstract class Controller<T extends View> {

  public abstract void bind(T view);

  public abstract View getView();

  public void setDefaults() {}

  protected boolean isBound;

  public boolean isBound() {
    return isBound;
  }

  protected void setIsBound(boolean value) {
    this.isBound = value;
  }
}
