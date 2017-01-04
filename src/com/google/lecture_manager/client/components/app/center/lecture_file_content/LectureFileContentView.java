package com.google.lecture_manager.client.components.app.center.lecture_file_content;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

public class LectureFileContentView implements LectureFileContentController.ILectureFileContentView {

  private BorderLayoutContainer mainContainer;

  public LectureFileContentView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    mainContainer = new BorderLayoutContainer();
  }

  @Override
  public Widget asWidget() {
    return mainContainer;
  }
}