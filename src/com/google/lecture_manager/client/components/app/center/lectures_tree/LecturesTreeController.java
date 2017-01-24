package com.google.lecture_manager.client.components.app.center.lectures_tree;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.client.events.AttendLectureEvent;
import com.google.lecture_manager.client.events.MaskUIEvent;
import com.google.lecture_manager.client.events.SelectFileEvent;
import com.google.lecture_manager.client.events.UnmaskUIEvent;
import com.google.lecture_manager.client.handlers.AttendLectureEventHandler;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.shared.FileTypes;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.tree.Node;
import com.google.lecture_manager.shared.model.tree.Tree;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

import java.util.List;

public class LecturesTreeController extends Controller<LecturesTreeController.ILecturesTreeView> {

  public interface ILecturesTreeView extends View {
    TreeGrid<FileData> getTreeGrid();
  }

  private static LecturesTreeController INSTANCE = null;

  private ILecturesTreeView view;
  private TreeStore<FileData> treeStore;
  private static FileData defaultTreeRoot = new FileData("root", "", FileTypes.FOLDER, -1, null);

  @Override
  public void bind(ILecturesTreeView view) {
    this.view = view;
    this.treeStore = view.getTreeGrid().getTreeStore();
    loadLecturesTree();

    view.getTreeGrid().getSelectionModel().addSelectionHandler(new SelectionHandler<FileData>() {
      public void onSelection(SelectionEvent<FileData> selectionEvent) {
        Info.display("Selected File", selectionEvent.getSelectedItem() != null ? selectionEvent.getSelectedItem().getName() : "null");
        AppUtils.EVENT_BUS.fireEvent(new SelectFileEvent(selectionEvent.getSelectedItem()));
      }
    });

    AppUtils.EVENT_BUS.addHandler(AttendLectureEvent.TYPE, new AttendLectureEventHandler() {
      public void onAttendLectureEvent(AttendLectureEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new MaskUIEvent("Load Lecture Files..."));
        AppUtils.SERVICE_FACTORY.getLectureService().getLectureFilesForUser(event.getLecture().getId(),
                AppUtils.getInstance().getAuthenticatedUser().getId(), new AsyncCallback<Node<FileData>>() {
                  public void onFailure(Throwable throwable) {
                    AppUtils.EVENT_BUS.fireEvent(new UnmaskUIEvent());
                  }

                  public void onSuccess(Node<FileData> fileDataNode) {
                    AppUtils.EVENT_BUS.fireEvent(new UnmaskUIEvent());
                    treeStore.add(defaultTreeRoot, fileDataNode.getValue());
                    addStoreChildrenHierarchy(fileDataNode.getValue(), fileDataNode.getChildren());
                  }
                });
      }
    });

    setIsBound(true);
  }

  private void loadLecturesTree() {
    AppUtils.EVENT_BUS.fireEvent(new MaskUIEvent("Loading Lectures Files..."));
    AppUtils.SERVICE_FACTORY.getLectureService().getLecturesFilesForUser(
            AppUtils.getInstance().getAuthenticatedUser().getId(), new AsyncCallback<Tree<FileData>>() {
      public void onFailure(Throwable throwable) {
        AppUtils.EVENT_BUS.fireEvent(new UnmaskUIEvent());
        Info.display("Error", throwable.getMessage());
      }

      public void onSuccess(Tree<FileData> fileDataTree) {
        AppUtils.EVENT_BUS.fireEvent(new UnmaskUIEvent());
        loadTreeStore(fileDataTree);
      }
    });
  }

  private void loadTreeStore(Tree<FileData> tree) {
    treeStore.clear();
    treeStore.add(defaultTreeRoot);
    if (tree == null)
      return;
    List<Node<FileData>> roots = tree.getRoots();
    for (Node<FileData> node : roots) {
      FileData root = node.getValue();
      treeStore.add(defaultTreeRoot, root);
      addStoreChildrenHierarchy(root, node.getChildren());
    }

//    for (FileData root : treeStore.getRootItems())
//      view.getTreeGrid().setExpanded(root, true, true);
  }

  private void addStoreChildrenHierarchy(FileData parent, List<Node<FileData>> children) {
    if (children == null || children.isEmpty())
      return;
    for (Node<FileData> node : children) {
      FileData n = node.getValue();
      treeStore.add(parent, n);
      addStoreChildrenHierarchy(n, node.getChildren());
    }
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new LecturesTreeController();
    return INSTANCE;
  }
}