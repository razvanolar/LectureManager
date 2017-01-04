package com.google.lecture_manager.client.components.app.center;

import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.ElementTypes;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.client.utils.factories.AbstractFactory;

public class CenterController extends Controller<CenterController.ICenterView> {

  public interface ICenterView extends View {
    void setContent(Widget lecturesTreeView, Widget lectureFileContentView);
  }

  private static CenterController INSTANCE = null;

  private ICenterView view;

  @Override
  public void bind(ICenterView view) {
    this.view = view;
    view.setContent(
            AbstractFactory.getWidget(ElementTypes.LECTURES_TREE),
            AbstractFactory.getWidget(ElementTypes.LECTURE_FILE_CONTENT)
    );
    setIsBound(true);
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new CenterController();
    return INSTANCE;
  }
}