package com.google.lecture_manager.client.components.app.center.lectures_tree;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.utils.properties.FileDataProperties;
import com.google.lecture_manager.shared.model.FileData;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

import java.util.ArrayList;
import java.util.List;

public class LecturesTreeView implements LecturesTreeController.ILecturesTreeView {

  private FileDataProperties fileDataProperties = GWT.create(FileDataProperties.class);

  private ContentPanel mainContainer;
  private TreeGrid<FileData> treeGrid;

  public LecturesTreeView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    treeGrid = createFilesGrid();
    mainContainer = new ContentPanel();
    mainContainer.setHeaderVisible(false);
    mainContainer.add(treeGrid);
  }

  private TreeGrid<FileData> createFilesGrid() {
    TreeStore<FileData> store = new TreeStore<>(fileDataProperties.path());

    List<ColumnConfig<FileData, ?>> columns = new ArrayList<>();
    ColumnConfig<FileData, String> nameColumn = new ColumnConfig<>(fileDataProperties.name(), 200, "Name");
    columns.add(nameColumn);

    TreeGrid<FileData> treeGrid = new TreeGrid<>(store, new ColumnModel<>(columns), nameColumn);
    treeGrid.getTreeView().setAutoFill(true);
    return treeGrid;
  }

  public TreeGrid<FileData> getTreeGrid() {
    return treeGrid;
  }

  @Override
  public Widget asWidget() {
    return mainContainer;
  }
}