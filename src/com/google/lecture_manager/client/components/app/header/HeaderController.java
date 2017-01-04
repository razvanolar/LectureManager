package com.google.lecture_manager.client.components.app.header;

import com.google.gwt.user.client.ui.Label;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.View;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class HeaderController extends Controller<HeaderController.IHeaderView> {

  public interface IHeaderView extends View {
    Label getUserNameLabel();
    TextButton getLogoutButton();
    TextButton getManageUsersButton();
    TextButton getManageLecturesButton();
    TextButton getApplyForLectureButton();
  }

  private static HeaderController INSTANCE = null;

  private IHeaderView view;

  @Override
  public void bind(IHeaderView view) {
    this.view = view;
    if (!AppUtils.getInstance().isAdmin()) {
      view.getManageUsersButton().setVisible(false);
      view.getManageLecturesButton().setVisible(false);
    } else {
      view.getApplyForLectureButton().setVisible(false);
    }
    setIsBound(true);
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new HeaderController();
    return INSTANCE;
  }
}