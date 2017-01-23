package com.google.lecture_manager.client.components.app.apply_for_lecture;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.client.utils.services.LectureServiceAsync;
import com.google.lecture_manager.shared.InputValidator;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;

import java.util.List;

public class ApplyForLectureController extends Controller<ApplyForLectureController.IApplyForLectureView> {

  public interface IApplyForLectureView extends View {
    Grid<LectureDTO> getLecturesGrid();
    TextField getEnrolmentKeyTextField();
    TextButton getApplyButton();
    void mask(String message);
    void unmask();
  }

  private static ApplyForLectureController INSTANCE = null;

  private IApplyForLectureView view;

  @Override
  public void bind(final IApplyForLectureView view) {
    this.view = view;
    loadLectures();

    view.getEnrolmentKeyTextField().setEnabled(false);
    view.getApplyButton().setEnabled(false);

    view.getLecturesGrid().getSelectionModel().addSelectionChangedHandler(new SelectionChangedEvent.SelectionChangedHandler<LectureDTO>() {
      public void onSelectionChanged(SelectionChangedEvent<LectureDTO> event) {
        view.getEnrolmentKeyTextField().setEnabled(isValidSelection());
        view.getApplyButton().setEnabled(isValidApply());
      }
    });

    view.getEnrolmentKeyTextField().addKeyUpHandler(new KeyUpHandler() {
      public void onKeyUp(KeyUpEvent keyUpEvent) {
        view.getApplyButton().setEnabled(isValidApply());
      }
    });

    setIsBound(true);
  }

  private void loadLectures() {
    LectureServiceAsync lectureService = AppUtils.SERVICE_FACTORY.getLectureService();
    view.mask("Loading Lectures...");
    lectureService.getUnattendedLectures(AppUtils.getInstance().getAuthenticatedUser().getId(), new AsyncCallback<List<LectureDTO>>() {
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

  private boolean isValidApply() {
    LectureDTO lecture = getSelectedLecture();
    return lecture != null && !InputValidator.isNullOrEmpty(view.getEnrolmentKeyTextField().getText());
  }

  private boolean isValidSelection() {
    return getSelectedLecture() != null;
  }

  private LectureDTO getSelectedLecture() {
    return view.getLecturesGrid().getSelectionModel().getSelectedItem();
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