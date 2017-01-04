package com.google.lecture_manager.client.components.app.center.lecture_file_content;

import com.google.lecture_manager.client.events.SelectFileEvent;
import com.google.lecture_manager.client.handlers.SelectFileEventHandler;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.shared.model.FileData;
import com.sencha.gxt.widget.core.client.info.Info;

public class LectureFileContentController extends Controller<LectureFileContentController.ILectureFileContentView> {

  public interface ILectureFileContentView extends View {
    void addPanel(String path, String title);
    void clear();
  }

  private static LectureFileContentController INSTANCE = null;

  private ILectureFileContentView view;

  @Override
  public void bind(ILectureFileContentView view) {
    this.view = view;

    AppUtils.EVENT_BUS.addHandler(SelectFileEvent.TYPE, new SelectFileEventHandler() {
      public void onSelectFileEvent(SelectFileEvent event) {
        FileData fileData = event.getFileData();
        if (fileData == null) {
          Info.display("Error", "Unable to open null file.");
          return;
        }

        LectureFileContentController.this.view.addPanel(fileData.getPath(), fileData.getName());
      }
    });

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