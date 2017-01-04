package com.google.lecture_manager.client.components.app.center.lectures_tree;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;

public class LecturesTreeView implements LecturesTreeController.ILecturesTreeView {

  private ContentPanel mainContainer;

  public LecturesTreeView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    mainContainer = new ContentPanel();
    mainContainer.setHeaderVisible(false);
//    mainContainer.getElement().getStyle().setBackgroundColor("#ffffff");
  }

  @Override
  public Widget asWidget() {
    return mainContainer;
  }
}