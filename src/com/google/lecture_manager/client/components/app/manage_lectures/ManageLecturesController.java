package com.google.lecture_manager.client.components.app.manage_lectures;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.client.events.*;
import com.google.lecture_manager.client.handlers.AddLectureEventHandler;
import com.google.lecture_manager.client.handlers.DeleteLectureEventHandler;
import com.google.lecture_manager.client.handlers.EditLectureEventHandler;
import com.google.lecture_manager.client.handlers.LoadLecturesEventHandler;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.ElementTypes;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.client.utils.factories.AbstractFactory;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;

import java.util.List;

public class ManageLecturesController extends Controller<ManageLecturesController.IManageLecturesView> {

  public interface IManageLecturesView extends View {
    Grid<LectureDTO> getGrid();
    TextButton getEditButton();
    TextButton getDeleteButton();
    void mask(String message);
    void unmask();
  }

  private static ManageLecturesController INSTANCE = null;

  private IManageLecturesView view;

  @Override
  public void bind(final IManageLecturesView view) {
    this.view = view;
    setIsBound(true);
    AppUtils.EVENT_BUS.addHandler(AddLectureEvent.TYPE, new AddLectureEventHandler() {
      @Override
      public void onAddLectureEvent(AddLectureEvent event) {
        ((Window) AbstractFactory.getWidget(ElementTypes.ADD_EDIT_LECTURES)).show();
      }
    });

    AbstractFactory.getController(ElementTypes.ADD_EDIT_LECTURES);
    AppUtils.EVENT_BUS.addHandler(EditLectureEvent.TYPE, new EditLectureEventHandler() {
      @Override
      public void onEditLectureEvent(EditLectureEvent event) {
        ((Window) AbstractFactory.getWidget(ElementTypes.ADD_EDIT_LECTURES)).show();
        AppUtils.EVENT_BUS.fireEvent(new PopulateLectureFields(event.getSelectedItem()));
      }
    });

    AppUtils.EVENT_BUS.addHandler(LoadLecturesEvent.TYPE, new LoadLecturesEventHandler() {
      @Override
      public void reloadLectures(LoadLecturesEvent event) {
        loadLectures();
      }
    });

    AppUtils.EVENT_BUS.addHandler(DeleteLectureEvent.TYPE, new DeleteLectureEventHandler() {
      @Override
      public void onDeleteLectureEvent(DeleteLectureEvent event) {
        AppUtils.SERVICE_FACTORY.getLectureService().deleteLecture(event.getSelectedItems(), new AsyncCallback<Void>() {
          @Override
          public void onFailure(Throwable caught) {
            new AlertMessageBox("Info", "Error while deleting Lectures: " + caught.getMessage()).show();
          }

          @Override
          public void onSuccess(Void result) {
            Info.display("Info", "Lectures deleted successfully");
            AppUtils.EVENT_BUS.fireEvent(new LoadLecturesEvent());
          }
        });
      }
    });
    view.getGrid().getSelectionModel().addSelectionChangedHandler(new SelectionChangedEvent.SelectionChangedHandler<LectureDTO>() {
      @Override
      public void onSelectionChanged(SelectionChangedEvent<LectureDTO> event) {
        view.getDeleteButton().setEnabled(!(event.getSelection() == null || event.getSelection().size() == 0));
        view.getEditButton().setEnabled(event.getSelection() != null && event.getSelection().size() == 1);
      }
    });
    loadLectures();
  }

  private void loadLectures() {
    view.mask("Loading lectures...");
    AppUtils.SERVICE_FACTORY.getLectureService().getAllLectures(new AsyncCallback<List<LectureDTO>>() {
      @Override
      public void onFailure(Throwable caught) {
        view.unmask();
        new AlertMessageBox("Info", "Error while loading lectures: " + caught.getMessage()).show();
      }

      @Override
      public void onSuccess(List<LectureDTO> result) {
        view.unmask();
        view.getGrid().getStore().clear();
        view.getGrid().getStore().addAll(result);
      }
    });
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