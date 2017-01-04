package com.google.lecture_manager.client.components.app.center.lectures_tree;

import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;

public class LecturesTreeController extends Controller<LecturesTreeController.ILecturesTreeView> {

  public interface ILecturesTreeView extends View {
  }

  private static LecturesTreeController INSTANCE = null;

  private ILecturesTreeView view;

  @Override
  public void bind(ILecturesTreeView view) {
    this.view = view;
    setIsBound(true);
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