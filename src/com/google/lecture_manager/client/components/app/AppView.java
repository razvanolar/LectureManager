package com.google.lecture_manager.client.components.app;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

public class AppView implements AppController.IAppView {

  private BorderLayoutContainer borderLayoutContainer;

  public AppView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    borderLayoutContainer = new BorderLayoutContainer();
  }

  @Override
  public void mask(String value) {
    borderLayoutContainer.mask(value);
  }

  @Override
  public void unmask() {
    borderLayoutContainer.unmask();
  }

  @Override
  public Widget asWidget() {
    return borderLayoutContainer;
  }
} 
