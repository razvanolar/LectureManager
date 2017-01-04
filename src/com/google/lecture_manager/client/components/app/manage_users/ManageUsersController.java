package com.google.lecture_manager.client.components.app.manage_users;

import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;

public class ManageUsersController extends Controller<ManageUsersController.IManageUsersView> {

  public interface IManageUsersView extends View {
  }

  private static ManageUsersController INSTANCE = null;

  private IManageUsersView view;

  @Override
  public void bind(IManageUsersView view) {
    this.view = view;
    setIsBound(true);
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