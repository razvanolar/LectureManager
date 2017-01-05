package com.google.lecture_manager.client.components.app.manage_users;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.client.events.AddUserEvent;
import com.google.lecture_manager.client.events.DeleteUserEvent;
import com.google.lecture_manager.client.events.EditUserEvent;
import com.google.lecture_manager.client.events.LoadUsersEvent;
import com.google.lecture_manager.client.handlers.AddUserEventHandler;
import com.google.lecture_manager.client.handlers.DeleteUserEventHandler;
import com.google.lecture_manager.client.handlers.EditUserEventHandler;
import com.google.lecture_manager.client.handlers.LoadUsersEventHandler;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.ElementTypes;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.client.utils.factories.AbstractFactory;
import com.google.lecture_manager.shared.model.User;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

import java.util.List;

public class ManageUsersController extends Controller<ManageUsersController.IManageUsersView> {

  public interface IManageUsersView extends View {
    Grid<User> getGrid();
    void mask(String message);
    void unmask();
  }

  private static ManageUsersController INSTANCE = null;

  private IManageUsersView view;

  @Override
  public void bind(final IManageUsersView view) {
    this.view = view;
    setIsBound(true);
    AppUtils.EVENT_BUS.addHandler(AddUserEvent.TYPE, new AddUserEventHandler() {
      @Override
      public void onAddUserEvent(AddUserEvent event) {
        AddEditUsersController.IAddEditUsersView view = new AddEditUsersView();
        AddEditUsersController controller = new AddEditUsersController();
        controller.bind(view);
      }
    });

    AppUtils.EVENT_BUS.addHandler(EditUserEvent.TYPE, new EditUserEventHandler() {
      @Override
      public void onEditUserEvent(EditUserEvent event) {
        AddEditUsersController.IAddEditUsersView view = new AddEditUsersView();
        AddEditUsersController controller = new AddEditUsersController();
        controller.bind(view);
        controller.loadFields(event.getSelectedItem());

      }
    });

    AppUtils.EVENT_BUS.addHandler(LoadUsersEvent.TYPE, new LoadUsersEventHandler() {
      @Override
      public void reloadUsers(final LoadUsersEvent event) {
        loadUsers();
      }
    });

    AppUtils.EVENT_BUS.addHandler(DeleteUserEvent.TYPE, new DeleteUserEventHandler() {
      @Override
      public void onDeleteUserEvent(DeleteUserEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new LoadUsersEvent());
      }
    });
    loadUsers();
  }

  private void loadUsers() {
    view.mask("Loading users...");
    AppUtils.SERVICE_FACTORY.getUserService().getAllUsers(new AsyncCallback<List<User>>() {
      @Override
      public void onFailure(Throwable caught) {
        view.unmask();
        new AlertMessageBox("Info", "Error while loading users: " + caught.getMessage()).show();
      }

      @Override
      public void onSuccess(List<User> result) {
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
      INSTANCE = new ManageUsersController();
    return INSTANCE;
  }
}