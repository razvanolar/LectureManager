package com.google.lecture_manager.client.components.app.apply_for_lecture;

import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.components.app.lectures_grid.LecturesGrid;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class ApplyForLectureView implements ApplyForLectureController.IApplyForLectureView {

  private Window window;
  private BorderLayoutContainer mainContainer;
  private Grid<LectureDTO> lecturesGrid;

  public ApplyForLectureView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    window = new Window();
    mainContainer = new BorderLayoutContainer();
    lecturesGrid = new LecturesGrid(Style.SelectionMode.SINGLE).getLecturesGrid();

    mainContainer.setCenterWidget(lecturesGrid);

    window.add(mainContainer);
    window.setModal(true);
    window.setPixelSize(650, 350);
  }

  public Grid<LectureDTO> getLecturesGrid() {
    return lecturesGrid;
  }

  @Override
  public void mask(String message) {
    mainContainer.mask(message);
  }

  @Override
  public void unmask() {
    mainContainer.unmask();
  }

  @Override
  public Widget asWidget() {
    return window;
  }
}