package com.google.lecture_manager.client.components.app.manage_lectures;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.events.AddLectureEvent;
import com.google.lecture_manager.client.events.DeleteLectureEvent;
import com.google.lecture_manager.client.events.EditLectureEvent;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.properties.LectureProperties;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class ManageLecturesView implements ManageLecturesController.IManageLecturesView {
  private VerticalLayoutContainer mainContainer;
  private ListStore<LectureDTO> listStore;
  private Grid<LectureDTO> grid;
  private LectureProperties lecturesProperties = GWT.create(LectureProperties.class);
  private HorizontalLayoutContainer horizontalLayoutContainer;
  private TextButton addButton, editButton, deleteButton;

  public ManageLecturesView() {
    initGUI();
    addHandlers();
  }

  private void addHandlers() {
    addButton.addSelectHandler(new SelectEvent.SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new AddLectureEvent());
      }
    });

    editButton.addSelectHandler(new SelectEvent.SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new EditLectureEvent(grid.getSelectionModel().getSelectedItem()));
      }
    });

    deleteButton.addSelectHandler(new SelectEvent.SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new DeleteLectureEvent(grid.getSelectionModel().getSelectedItems()));
      }
    });
  }

  @Override
  public void initGUI() {
    mainContainer = new VerticalLayoutContainer();
    grid = createGrid();
    addButton = new TextButton("Add");
    editButton = new TextButton("Edit");
    editButton.setEnabled(false);
    deleteButton = new TextButton("Delete");
    deleteButton.setEnabled(false);
    horizontalLayoutContainer = new HorizontalLayoutContainer();
    horizontalLayoutContainer.add(addButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 10)));
    horizontalLayoutContainer.add(editButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 0)));
    horizontalLayoutContainer.add(deleteButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 0)));

    mainContainer.add(horizontalLayoutContainer, new VerticalLayoutContainer.VerticalLayoutData(1, 30));
    mainContainer.add(grid, new VerticalLayoutContainer.VerticalLayoutData(1, 1));
  }

  private Grid<LectureDTO> createGrid() {
    IdentityValueProvider<LectureDTO> identityValueProvider = new IdentityValueProvider<>("sm");
    CheckBoxSelectionModel<LectureDTO> selectionModel = new CheckBoxSelectionModel<>(identityValueProvider);
    selectionModel.setSelectionMode(Style.SelectionMode.MULTI);

    List<ColumnConfig<LectureDTO, ?>> columnConfigs = new ArrayList<>();
    columnConfigs.add(selectionModel.getColumn());
    columnConfigs.add(new ColumnConfig<>(lecturesProperties.id(), 20, "ID"));
    columnConfigs.add(new ColumnConfig<>(lecturesProperties.lectureName(), 100, "Lecture Name"));
    columnConfigs.add(new ColumnConfig<>(new ValueProvider<LectureDTO, String>() {
      @Override
      public String getValue(LectureDTO object) {
        return object.getTeacher().getFirstName() + " " + object.getTeacher().getLastName();
      }
      @Override
      public void setValue(LectureDTO object, String value) {  }
      @Override
      public String getPath() {
        return "teacherName";
      }
    }, 100, "Teacher"));
    columnConfigs.add(new ColumnConfig<>(lecturesProperties.enrolmentKey(), 100, "Enrolment Key"));

    ColumnModel<LectureDTO> columnModel = new ColumnModel<>(columnConfigs);
    listStore = new ListStore<>(lecturesProperties.key());
    Grid<LectureDTO> LectureGrid = new Grid<>(listStore, columnModel);
    LectureGrid.getView().setAutoFill(true);
    LectureGrid.setSelectionModel(selectionModel);

    return LectureGrid;
  }

  public Grid<LectureDTO> getGrid() {
    return grid;
  }

  public TextButton getEditButton() {
    return editButton;
  }

  public TextButton getDeleteButton() {
    return deleteButton;
  }

  @Override
  public Widget asWidget() {
    return mainContainer;
  }

  @Override
  public void mask(String message) {
    mainContainer.mask(message);
  }

  @Override
  public void unmask() {
    mainContainer.unmask();
  }
}