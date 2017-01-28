package com.google.lecture_manager.client.components.app.manage_lectures;

import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.components.app.lectures_grid.LecturesGrid;
import com.google.lecture_manager.client.events.AddLectureEvent;
import com.google.lecture_manager.client.events.DeleteLectureEvent;
import com.google.lecture_manager.client.events.EditLectureEvent;
import com.google.lecture_manager.client.events.FilesEvent;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class ManageLecturesView implements ManageLecturesController.IManageLecturesView {
  private VerticalLayoutContainer mainContainer;
  private Grid<LectureDTO> grid;
  private TextButton addButton, editButton, deleteButton, fileButton;

  public ManageLecturesView() {
    initGUI();
    addHandlers();
  }

  private void addHandlers() {
    addButton.addSelectHandler(new SelectEvent.SelectHandler() {
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new AddLectureEvent());
      }
    });

    editButton.addSelectHandler(new SelectEvent.SelectHandler() {
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new EditLectureEvent(grid.getSelectionModel().getSelectedItem()));
      }
    });

    deleteButton.addSelectHandler(new SelectEvent.SelectHandler() {
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new DeleteLectureEvent(grid.getSelectionModel().getSelectedItems()));
      }
    });

    fileButton.addSelectHandler(new SelectEvent.SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new FilesEvent(grid.getSelectionModel().getSelectedItem()));
      }
    });
  }

  @Override
  public void initGUI() {
    mainContainer = new VerticalLayoutContainer();
    grid = new LecturesGrid(Style.SelectionMode.MULTI, true).getLecturesGrid();
    addButton = new TextButton("Add");
    editButton = new TextButton("Edit");
    editButton.setEnabled(false);
    deleteButton = new TextButton("Delete");
    deleteButton.setEnabled(false);
    fileButton = new TextButton("Files");
    fileButton.setEnabled(false);
    HorizontalLayoutContainer horizontalLayoutContainer = new HorizontalLayoutContainer();
    horizontalLayoutContainer.add(addButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 10)));
    horizontalLayoutContainer.add(editButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 0)));
    horizontalLayoutContainer.add(deleteButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 0)));
    horizontalLayoutContainer.add(fileButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 0)));

    mainContainer.add(horizontalLayoutContainer, new VerticalLayoutContainer.VerticalLayoutData(1, 30));
    mainContainer.add(grid, new VerticalLayoutContainer.VerticalLayoutData(1, 1));
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
  public TextButton getFileButton() {
    return fileButton;
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