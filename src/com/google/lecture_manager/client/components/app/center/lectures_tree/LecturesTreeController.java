package com.google.lecture_manager.client.components.app.center.lectures_tree;

import com.google.gwt.user.client.rpc.AsyncCallback;
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
    setIsBound(true);
  }

  private void loadLecturesTree() {
    AppUtils.SERVICE_FACTORY.getLectureService().getLecturesFilesForUser(
            AppUtils.getInstance().getAuthenticatedUser().getId(), new AsyncCallback<Tree<FileData>>() {
      public void onFailure(Throwable throwable) {
        Info.display("Error", throwable.getMessage());
      }

      public void onSuccess(Tree<FileData> fileDataTree) {
        loadTreeStore(fileDataTree);
      }
    });
  }

  private void loadTreeStore(Tree<FileData> tree) {
    treeStore.clear();
    treeStore.add(defaultTreeRoot);
    List<Node<FileData>> roots = tree.getRoots();
    for (Node<FileData> node : roots) {
      FileData root = node.getValue();
      treeStore.add(defaultTreeRoot, root);
      addStoreChildrenHierarchy(root, node.getChildren());
    }

    for (FileData root : treeStore.getRootItems())
      view.getTreeGrid().setExpanded(root, true, true);
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