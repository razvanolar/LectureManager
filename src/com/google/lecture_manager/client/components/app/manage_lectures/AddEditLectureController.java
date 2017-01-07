package com.google.lecture_manager.client.components.app.manage_lectures;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.client.events.LoadLecturesEvent;
import com.google.lecture_manager.client.events.PopulateLectureFields;
import com.google.lecture_manager.client.handlers.PopulateLectureFieldsHandler;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.shared.model.Lecture;
import com.google.lecture_manager.shared.model.Teacher;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.List;

public class AddEditLectureController extends Controller<AddEditLectureController.IAddEditLectureView> {

  public interface IAddEditLectureView extends View {
    TextField getNameField();
    TextField getEnrolmentField();
    ComboBox<Teacher> getTeacherComboBox();
    TextButton getApplyButton();
    void mask(String message);
    void unmask();
    void close();
  }

  private static AddEditLectureController INSTANCE = null;
  private Lecture selectedLecture;

  private IAddEditLectureView view;

  @Override
  public void bind(final IAddEditLectureView view) {
    this.view = view;
    setIsBound(true);
    AppUtils.EVENT_BUS.addHandler(PopulateLectureFields.TYPE, new PopulateLectureFieldsHandler() {
      @Override
      public void initEditMode(PopulateLectureFields event) {
        view.getNameField().setValue(event.getLecture().getLectureName());
        view.getEnrolmentField().setValue(event.getLecture().getEnrolmentKey());
        view.getTeacherComboBox().setValue(event.getLecture().getTeacher());
        selectedLecture = event.getLecture();
      }
    });
    loadTeachers();
    addListeners();
  }

  private void loadTeachers() {
    view.mask("Loading Teachers...");
    AppUtils.SERVICE_FACTORY.getUserService().getAllTeachers(new AsyncCallback<List<Teacher>>() {
      @Override
      public void onFailure(Throwable caught) {
        view.unmask();
        new AlertMessageBox("Info", "Error while loading teachers: " + caught.getMessage()).show();
      }

      @Override
      public void onSuccess(List<Teacher> result) {
        view.unmask();
        view.getTeacherComboBox().getStore().clear();
        view.getTeacherComboBox().getStore().addAll(result);
        view.getTeacherComboBox().setValue(view.getTeacherComboBox().getStore().size() > 0 ? view.getTeacherComboBox().getStore().get(0) : null);
      }
    });
  }

  private void addListeners() {
    view.getApplyButton().addSelectHandler(new SelectEvent.SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        if (!validFields()) {
          Info.display("Info", "Invalid input");
          return;
        }
        view.mask("Applying...");
        Lecture temp = collectData();
        if (selectedLecture == null) {
          AppUtils.SERVICE_FACTORY.getLectureService().addLecture(temp, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
              view.unmask();
              new AlertMessageBox("Info", "Error while adding new lecture: " + caught.getMessage()).show();
            }

            @Override
            public void onSuccess(Void result) {
              view.unmask();
              Info.display("Info", "Lecture added successfully.");
              view.close();
              AppUtils.EVENT_BUS.fireEvent(new LoadLecturesEvent());
            }
          });
        } else {
          temp.setId(selectedLecture.getId());
          AppUtils.SERVICE_FACTORY.getLectureService().editLecture(temp, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
              view.unmask();
              new AlertMessageBox("Info", "Error while editing new lecture: " + caught.getMessage()).show();
            }

            @Override
            public void onSuccess(Void result) {
              view.unmask();
              Info.display("Info", "Lecture edited successfully.");
              view.close();
              AppUtils.EVENT_BUS.fireEvent(new LoadLecturesEvent());
            }
          });
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
      INSTANCE = new AddEditLectureController();
    return INSTANCE;
  }

  private boolean validFields() {
    if (view.getNameField().getText() == null || view.getNameField().getText().isEmpty())
      return false;
    if (view.getEnrolmentField().getText() == null || view.getNameField().getText().isEmpty())
      return false;
    if (view.getTeacherComboBox().getValue() == null)
      return false;
    return true;
  }

  private Lecture collectData() {
    return new Lecture(view.getTeacherComboBox().getValue(), view.getNameField().getValue(), view.getEnrolmentField().getValue());
  }

  @Override
  public void setDefaults() {
    view.getNameField().setValue("");
    view.getEnrolmentField().setValue("");
    view.getTeacherComboBox().setValue(view.getTeacherComboBox().getStore().size() > 0 ? view.getTeacherComboBox().getStore().get(0) : null);
    selectedLecture = null;
  }
}