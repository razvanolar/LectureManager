package com.google.lecture_manager.client.components.app.manage_users;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;
import com.google.lecture_manager.shared.model.User;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.grid.Grid;

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
  public void bind(IManageUsersView view) {
    this.view = view;
    setIsBound(true);
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