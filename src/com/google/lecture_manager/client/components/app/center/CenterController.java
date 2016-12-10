package com.google.lecture_manager.client.components.app.center;

import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;

public class CenterController extends Controller<CenterController.ICenterView> {

  public interface ICenterView extends View {
  }

  private static CenterController INSTANCE = null;

  private ICenterView view;

  @Override
  public void bind(ICenterView view) {
    this.view = view;
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