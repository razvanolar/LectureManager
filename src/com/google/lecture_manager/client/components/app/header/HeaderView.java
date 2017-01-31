package com.google.lecture_manager.client.components.app.header;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.utils.AppUtils;
import com.sencha.gxt.cell.core.client.ButtonCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;

public class HeaderView implements HeaderController.IHeaderView {

  private static HeaderView INSTANCE;

  private BorderLayoutContainer borderLayoutContainer;
  private Label userNameLabel;
  private TextButton homeButton;
  private TextButton logoutButton;
  private TextButton manageUsersButton;
  private TextButton manageLecturesButton;
  private TextButton applyForLectureButton;

  private HeaderView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    borderLayoutContainer = new BorderLayoutContainer();
    homeButton = new TextButton("Home");
    userNameLabel = new Label("Test");
    manageUsersButton = new TextButton("Manage Users");
    manageUsersButton.setVisible(false);
    manageLecturesButton = new TextButton("Manage Lectures");
    manageLecturesButton.setVisible(false);
    applyForLectureButton = new TextButton("Apply for Lecture");
    applyForLectureButton.setVisible(false);
    logoutButton = new TextButton("LOGOUT ", AppUtils.ICONS.logout());
    Label userText = new Label("Logged in as: ");

    HBoxLayoutContainer leftContainer = new HBoxLayoutContainer(HBoxLayoutContainer.HBoxLayoutAlign.MIDDLE);
    HBoxLayoutContainer rightContainer = new HBoxLayoutContainer(HBoxLayoutContainer.HBoxLayoutAlign.MIDDLE);
    leftContainer.setPack(BoxLayoutContainer.BoxLayoutPack.START);
    rightContainer.setPack(BoxLayoutContainer.BoxLayoutPack.END);

    leftContainer.add(homeButton, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 0, 0, 5)));
    leftContainer.add(userText, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 3, 0, 10)));
    leftContainer.add(userNameLabel);
    BoxLayoutContainer.BoxLayoutData rightLayoutData = new BoxLayoutContainer.BoxLayoutData(new Margins(0, 10, 0, 0));
    rightContainer.add(manageUsersButton, rightLayoutData);
    rightContainer.add(manageLecturesButton, rightLayoutData);
    rightContainer.add(applyForLectureButton, rightLayoutData);
    rightContainer.add(logoutButton, rightLayoutData);

    userText.getElement().getStyle().setProperty("fontWeight", "bold");
    userNameLabel.getElement().getStyle().setProperty("fontWeight", "bold");
    logoutButton.setIconAlign(ButtonCell.IconAlign.RIGHT);

    borderLayoutContainer.setWestWidget(leftContainer, new BorderLayoutContainer.BorderLayoutData(200));
    borderLayoutContainer.setEastWidget(rightContainer, new BorderLayoutContainer.BorderLayoutData(400));
    borderLayoutContainer.getElement().getStyle().setBackgroundColor("#ffffff");
  }

  public TextButton getHomeButton() {
    return homeButton;
  }

  public Label getUserNameLabel() {
    return userNameLabel;
  }

  public TextButton getLogoutButton() {
    return logoutButton;
  }

  public TextButton getManageUsersButton() {
    return manageUsersButton;
  }

  public TextButton getManageLecturesButton() {
    return manageLecturesButton;
  }

  public TextButton getApplyForLectureButton() {
    return applyForLectureButton;
  }

  @Override
  public Widget asWidget() {
    return borderLayoutContainer;
  }

  public static HeaderView getInstance() {
    if (INSTANCE == null)
      INSTANCE = new HeaderView();
    return INSTANCE;
  }
}