package com.google.lecture_manager.client.components.app.center;

import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.utils.View;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;

public class CenterView implements CenterController.ICenterView {

  private static CenterView INSTANCE;

  private BorderLayoutContainer borderLayoutContainer;

  private CenterView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    borderLayoutContainer = new BorderLayoutContainer();
    borderLayoutContainer.getElement().getStyle().setBackgroundColor("#ffffff");

    HBoxLayoutContainer topMargin = new HBoxLayoutContainer();
    topMargin.getElement().getStyle().setBackgroundColor("#888888");
    borderLayoutContainer.setNorthWidget(topMargin, new BorderLayoutContainer.BorderLayoutData(4));
  }

  public void setContent(Widget lecturesTreeView, Widget lectureFileContentView) {
    BorderLayoutContainer.BorderLayoutData leftLayoutData = new BorderLayoutContainer.BorderLayoutData(350);
    leftLayoutData.setSplit(true);
    leftLayoutData.setCollapsible(true);
    leftLayoutData.setFloatable(true);
    borderLayoutContainer.setWestWidget(lecturesTreeView, leftLayoutData);
    borderLayoutContainer.setCenterWidget(lectureFileContentView);
    borderLayoutContainer.forceLayout();
  }

  @Override
  public Widget asWidget() {
    return borderLayoutContainer;
  }

  public static CenterView getInstance() {
    if (INSTANCE == null)
      INSTANCE = new CenterView();
    return INSTANCE;
  }
}