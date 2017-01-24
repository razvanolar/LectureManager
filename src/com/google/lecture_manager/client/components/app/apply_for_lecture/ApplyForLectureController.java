package com.google.lecture_manager.client.components.app.apply_for_lecture;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.client.events.AttendLectureEvent;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.client.utils.services.LectureServiceAsync;
import com.google.lecture_manager.shared.InputValidator;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
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

  private IApplyForLectureView view;

  @Override
  public void bind(final IApplyForLectureView view) {
    this.view = view;

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

    view.getApplyButton().addSelectHandler(new SelectEvent.SelectHandler() {
      public void onSelect(SelectEvent event) {
        if (isValidConfiguration()) {
          final LectureDTO lecture = getSelectedLecture();
          String key = view.getEnrolmentKeyTextField().getText();
          if (!key.equals(lecture.getEnrolmentKey())) {
            Info.display("Error", "Incorrect enrolment key!");
            return;
          }
          LectureServiceAsync lectureService = AppUtils.SERVICE_FACTORY.getLectureService();
          view.mask("Enrolling user...");
          lectureService.addUserForLecture(AppUtils.getInstance().getAuthenticatedUser().getId(), lecture.getId(),
                  new AsyncCallback<Void>() {
                    public void onFailure(Throwable throwable) {
                      view.unmask();
                      new AlertMessageBox("Error", "Error while enrolling to lecture. " + throwable.getMessage()).show();
                    }

                    public void onSuccess(Void aVoid) {
                      view.unmask();
                      AppUtils.EVENT_BUS.fireEvent(new AttendLectureEvent(lecture));
                      loadLectures();
                    }
                  });
        }
      }
    });

    loadLectures();

    setIsBound(true);
  }

  private void loadLectures() {
    LectureServiceAsync lectureService = AppUtils.SERVICE_FACTORY.getLectureService();
    view.mask("Loading Lectures...");
    lectureService.getUnattendedLectures(AppUtils.getInstance().getAuthenticatedUser().getId(), new AsyncCallback<List<LectureDTO>>() {
      public void onFailure(Throwable throwable) {
        new AlertMessageBox("Error", "Unable to retrieve lectures.").show();
        view.unmask();
      }

      public void onSuccess(List<LectureDTO> result) {
        view.unmask();
        ListStore<LectureDTO> store = view.getLecturesGrid().getStore();
        store.clear();
        if (result != null) {
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

  private boolean isValidConfiguration() {
    return isValidApply() && isValidSelection();
  }

  private LectureDTO getSelectedLecture() {
    return view.getLecturesGrid().getSelectionModel().getSelectedItem();
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    return new ApplyForLectureController();
  }
}