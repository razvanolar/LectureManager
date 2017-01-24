package com.google.lecture_manager.client.components.app.center;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.ElementTypes;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.client.utils.factories.AbstractFactory;
import com.google.lecture_manager.shared.UserTypes;

public class CenterController extends Controller<CenterController.ICenterView> {

  public interface ICenterView extends View {
    void setContent(Widget lecturesTreeView, Widget lectureFileContentView);
    void setContent(Label label);
  }

  private static CenterController INSTANCE = null;

  private ICenterView view;

  @Override
  public void bind(ICenterView view) {
    this.view = view;
    if (AppUtils.getInstance().getAuthenticatedUser().getType() == UserTypes.STUDENT) {
      view.setContent(
              AbstractFactory.getWidget(ElementTypes.LECTURES_TREE),
              AbstractFactory.getWidget(ElementTypes.LECTURE_FILE_CONTENT)
      );
    } else {
      view.setContent(new Label("No Lectures Available for this user"));
    }
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