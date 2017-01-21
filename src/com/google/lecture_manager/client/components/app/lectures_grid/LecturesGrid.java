package com.google.lecture_manager.client.components.app.lectures_grid;

import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.properties.LectureProperties;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by razvanolar on 21.01.2017
 */
public class LecturesGrid {

  private Grid<LectureDTO> lecturesGrid;
  private Style.SelectionMode selectionMode;
  private boolean showEnrolmentKey;

  public LecturesGrid(Style.SelectionMode selectionMode, boolean showEnrolmentKey) {
    this.selectionMode = selectionMode;
    this.showEnrolmentKey = showEnrolmentKey;
    init();
  }

  private void init() {
    LectureProperties lecturesProperties = AppUtils.PROPERTIES_FACTORY.getLecturesProperties();
    IdentityValueProvider<LectureDTO> identityValueProvider = new IdentityValueProvider<>("sm");
    CheckBoxSelectionModel<LectureDTO> selectionModel = new CheckBoxSelectionModel<>(identityValueProvider);
    selectionModel.setSelectionMode(selectionMode);

    List<ColumnConfig<LectureDTO, ?>> columnConfigs = new ArrayList<>();
    columnConfigs.add(selectionModel.getColumn());
    columnConfigs.add(new ColumnConfig<>(lecturesProperties.id(), 20, "ID"));
    columnConfigs.add(new ColumnConfig<>(lecturesProperties.lectureName(), 100, "Lecture Name"));
    columnConfigs.add(new ColumnConfig<>(new ValueProvider<LectureDTO, String>() {
      @Override public String getValue(LectureDTO object) {
        return object.getTeacher().getFirstName() + " " + object.getTeacher().getLastName();
      }
      @Override public void setValue(LectureDTO object, String value) {  }
      @Override public String getPath() {
        return "teacherName";
      }
    }, 100, "Teacher"));

    if (showEnrolmentKey) {
      columnConfigs.add(new ColumnConfig<>(lecturesProperties.enrolmentKey(), 100, "Enrolment Key"));
    }

    ColumnModel<LectureDTO> columnModel = new ColumnModel<>(columnConfigs);
    ListStore<LectureDTO> listStore = new ListStore<>(lecturesProperties.key());
    lecturesGrid = new Grid<>(listStore, columnModel);
    lecturesGrid.getView().setAutoFill(true);
    lecturesGrid.setSelectionModel(selectionModel);
  }

  public Grid<LectureDTO> getLecturesGrid() {
    return lecturesGrid;
  }
}
