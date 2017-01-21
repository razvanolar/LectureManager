package com.google.lecture_manager.client.components.app.apply_for_lecture;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.client.utils.services.LectureServiceAsync;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.List;

public class ApplyForLectureController extends Controller<ApplyForLectureController.IApplyForLectureView> {

  public interface IApplyForLectureView extends View {
    Grid<LectureDTO> getLecturesGrid();
    void mask(String message);
    void unmask();
  }

  private static ApplyForLectureController INSTANCE = null;

  private IApplyForLectureView view;

  @Override
  public void bind(IApplyForLectureView view) {
    this.view = view;
    loadLectures();
    setIsBound(true);
  }

  private void loadLectures() {
    LectureServiceAsync lectureService = AppUtils.SERVICE_FACTORY.getLectureService();
    view.mask("Loading Lectures...");
    lectureService.getAllLectures(new AsyncCallback<List<LectureDTO>>() {
      public void onFailure(Throwable throwable) {
        view.unmask();
      }

      public void onSuccess(List<LectureDTO> result) {
        view.unmask();
        if (result != null) {
          ListStore<LectureDTO> store = view.getLecturesGrid().getStore();
          store.clear();
          store.addAll(result);
        }
      }
    });
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new ApplyForLectureController();
    return INSTANCE;
  }
}