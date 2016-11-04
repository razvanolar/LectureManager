package com.google.lecture_manager.client.components.app;

import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.MaskableView;
import com.google.lecture_manager.client.utils.View;

public class AppController extends Controller<AppController.IAppView> {

  public interface IAppView extends View, MaskableView {
  }

  private static AppController INSTANCE = null;

  private IAppView view;

  @Override
  public void bind(IAppView view) {
    this.view = view;
    setIsBound(true);
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new AppController();
    return INSTANCE;
  }
} 
