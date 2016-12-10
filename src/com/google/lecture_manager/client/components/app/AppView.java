package com.google.lecture_manager.client.components.app;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

public class AppView implements AppController.IAppView {

  private BorderLayoutContainer borderLayoutContainer;
  private Widget northWidget;
  private Widget centerWidget;

  public AppView(Widget northWidget, Widget centerWidget) {
    this.northWidget = northWidget;
    this.centerWidget = centerWidget;
    initGUI();
  }

  @Override
  public void initGUI() {
    borderLayoutContainer = new BorderLayoutContainer();
    borderLayoutContainer.getElement().getStyle().setBackgroundColor("#ffffff");

    if (northWidget != null)
      borderLayoutContainer.setNorthWidget(northWidget, new BorderLayoutContainer.BorderLayoutData(40));
    if (centerWidget != null)
      borderLayoutContainer.setCenterWidget(centerWidget);
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
