package com.google.lecture_manager.client.components.app.apply_for_lecture;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.components.app.lectures_grid.LecturesGrid;
import com.google.lecture_manager.client.utils.AppConstants;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class ApplyForLectureView implements ApplyForLectureController.IApplyForLectureView {

  private Window window;
  private BorderLayoutContainer mainContainer;
  private Grid<LectureDTO> lecturesGrid;
  private TextButton applyButton;
  private TextField enrolmentKeyTextField;

  public ApplyForLectureView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    HBoxLayoutContainer bottomContainer = new HBoxLayoutContainer(HBoxLayoutContainer.HBoxLayoutAlign.MIDDLE);
    window = new Window();
    enrolmentKeyTextField = new TextField();
    applyButton = new TextButton("Apply For");
    mainContainer = new BorderLayoutContainer();
    lecturesGrid = new LecturesGrid(Style.SelectionMode.SINGLE, false).getLecturesGrid();

    mainContainer.setCenterWidget(lecturesGrid);
    mainContainer.setSouthWidget(bottomContainer, new BorderLayoutContainer.BorderLayoutData(30));
    mainContainer.setStyleName(AppConstants.WHITE_BG_STYLE);

    BoxLayoutContainer.BoxLayoutData flex = new BoxLayoutContainer.BoxLayoutData();
    flex.setFlex(1);
    bottomContainer.setStyleName(AppConstants.WHITE_BG_STYLE);
    bottomContainer.add(new FieldLabel(enrolmentKeyTextField, "Enrolment Key"), new BoxLayoutContainer.BoxLayoutData(new Margins(0, 0, 0, 5)));
    bottomContainer.add(new Label(), flex);
    bottomContainer.add(applyButton, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 5, 0, 0)));

    applyButton.getElement().getStyle().setPaddingBottom(3, com.google.gwt.dom.client.Style.Unit.PX);
    enrolmentKeyTextField.setWidth(200);

    window.add(mainContainer);
    window.setModal(true);
    window.setPixelSize(650, 350);
  }

  public Grid<LectureDTO> getLecturesGrid() {
    return lecturesGrid;
  }

  public TextButton getApplyButton() {
    return applyButton;
  }

  public TextField getEnrolmentKeyTextField() {
    return enrolmentKeyTextField;
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