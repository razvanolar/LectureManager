package com.google.lecture_manager.client.components.app.manage_lectures;

import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;

public class ManageLecturesController extends Controller<ManageLecturesController.IManageLecturesView> {

  public interface IManageLecturesView extends View {
  }

  private static ManageLecturesController INSTANCE = null;

  private IManageLecturesView view;

  @Override
  public void bind(IManageLecturesView view) {
    this.view = view;
    setIsBound(true);
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new ManageLecturesController();
    return INSTANCE;
  }
}