package com.google.lecture_manager.client.components.app.manage_lectures;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.client.events.PopulateLectureFileEvent;
import com.google.lecture_manager.client.handlers.PopulateLectureFileEventHandler;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.shared.FileTypes;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.google.lecture_manager.shared.model.tree.Node;
import com.google.lecture_manager.shared.model.tree.Tree;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

import java.util.List;

public class ManageLectureFilesController extends Controller<ManageLectureFilesController.IManageLectureFilesView> {

  public interface IManageLectureFilesView extends View {
    void mask(String message);
    void unmask();
    void close();
    TreeGrid<FileData> getTreeGrid();
    TextButton getAddButton();
    TextButton getDeleteButton();
  }

  private static ManageLectureFilesController INSTANCE = null;
  private LectureDTO selectedLecture;
  private static FileData defaultTreeRoot = new FileData("root", "", FileTypes.FOLDER, -1, null);

  private IManageLectureFilesView view;

  @Override
  public void bind(final IManageLectureFilesView view) {
    this.view = view;
    setIsBound(true);
    AppUtils.EVENT_BUS.addHandler(PopulateLectureFileEvent.TYPE, new PopulateLectureFileEventHandler() {
      @Override
      public void loadFiles(PopulateLectureFileEvent event) {
        selectedLecture = event.getSelectedItem();
        loadLectureFiles();
      }
    });
    if (selectedLecture != null) {
      loadLectureFiles();
    }
    view.getTreeGrid().getSelectionModel().addSelectionHandler(new SelectionHandler<FileData>() {
      @Override
      public void onSelection(SelectionEvent<FileData> event) {
        view.getAddButton().setEnabled(event.getSelectedItem().getType().equals(FileTypes.FOLDER));
        view.getDeleteButton().setEnabled(true);
      }
    });
  }

  private void loadLectureFiles() {
    view.mask("Loading Files...");
    AppUtils.SERVICE_FACTORY.getLectureService().getLectureFiles(selectedLecture, new AsyncCallback<Tree<FileData>>() {
      @Override
      public void onFailure(Throwable caught) {
        view.unmask();
        new AlertMessageBox("Info", "Error while getting lecture files: " + caught.getMessage()).show();
      }

      @Override
      public void onSuccess(Tree<FileData> result) {

        TreeStore<FileData> treeStore = view.getTreeGrid().getTreeStore();
        treeStore.clear();
        treeStore.add(defaultTreeRoot);
        if (result == null)
          return;
        List<Node<FileData>> roots = result.getRoots();
        for (Node<FileData> node : roots) {
          FileData root = node.getValue();
          treeStore.add(defaultTreeRoot, root);
          addStoreChildrenHierarchy(root, node.getChildren());
        }
        view.unmask();
      }
    });
  }

  private void addStoreChildrenHierarchy(FileData parent, List<Node<FileData>> children) {
    if (children == null || children.isEmpty())
      return;
    for (Node<FileData> node : children) {
      FileData n = node.getValue();
      view.getTreeGrid().getTreeStore().add(parent, n);
      addStoreChildrenHierarchy(n, node.getChildren());
    }
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new ManageLectureFilesController();
    return INSTANCE;
  }
}