package com.google.lecture_manager.client.components.app.center.lecture_file_content;

import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;

public class LectureFileContentController extends Controller<LectureFileContentController.ILectureFileContentView> {

  public interface ILectureFileContentView extends View {
  }

  private static LectureFileContentController INSTANCE = null;

  private ILectureFileContentView view;

  @Override
  public void bind(ILectureFileContentView view) {
    this.view = view;
    setIsBound(true);
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new LectureFileContentController();
    return INSTANCE;
  }
}