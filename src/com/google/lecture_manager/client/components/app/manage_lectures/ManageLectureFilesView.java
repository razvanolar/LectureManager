package com.google.lecture_manager.client.components.app.manage_lectures;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.utils.properties.FileDataProperties;
import com.google.lecture_manager.shared.model.FileData;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

import java.util.ArrayList;
import java.util.List;

public class ManageLectureFilesView implements ManageLectureFilesController.IManageLectureFilesView {

  private FileDataProperties fileDataProperties = GWT.create(FileDataProperties.class);
  private Window window;
  private VerticalLayoutContainer mainContainer;
  private ContentPanel contentPanel;
  private TreeGrid<FileData> treeGrid;
  private TextButton addButton, deleteButton;

  public ManageLectureFilesView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    window = new Window();

    treeGrid = createFilesGrid();
    contentPanel = new ContentPanel();
    contentPanel.setHeaderVisible(false);
    contentPanel.add(treeGrid);

    mainContainer = new VerticalLayoutContainer();

    addButton = new TextButton("Add");
    addButton.setEnabled(false);
    deleteButton = new TextButton("Delete");
    deleteButton.setEnabled(false);
    HorizontalLayoutContainer horizontalLayoutContainer = new HorizontalLayoutContainer();
    horizontalLayoutContainer.add(addButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 10)));
    horizontalLayoutContainer.add(deleteButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 0)));

    mainContainer.add(horizontalLayoutContainer, new VerticalLayoutContainer.VerticalLayoutData(1, 30));
    mainContainer.add(contentPanel, new VerticalLayoutContainer.VerticalLayoutData(1, 1));

    window.add(mainContainer);
    window.setSize("600", "400");
    window.setModal(true);
    window.setResizable(false);
    window.setHeadingText("Lecture Files Management");
  }


  private TreeGrid<FileData> createFilesGrid() {
    TreeStore<FileData> treeStore = new TreeStore<>(fileDataProperties.path());

    List<ColumnConfig<FileData, ?>> columns;
    columns = new ArrayList<>();
    ColumnConfig<FileData, String> nameColumn = new ColumnConfig<>(fileDataProperties.name(), 200, "Name");
    columns.add(nameColumn);

    TreeGrid<FileData> treeGrid = new TreeGrid<>(treeStore, new ColumnModel<>(columns), nameColumn);
    treeGrid.getTreeView().setAutoFill(true);
    return treeGrid;
  }
  @Override
  public Widget asWidget() {
    return window;
  }

  @Override
  public void mask(String message) {
    mainContainer.mask(message);
  }

  @Override
  public void unmask() {
    mainContainer.unmask();
  }

  public void close() {
    window.hide();
  }

  public TreeGrid<FileData> getTreeGrid() {
    return treeGrid;
  }

  public TextButton getAddButton() {
    return addButton;
  }

  public TextButton getDeleteButton() {
    return deleteButton;
  }
}