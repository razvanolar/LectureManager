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
  private TextButton logoutButton;

  private HeaderView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    borderLayoutContainer = new BorderLayoutContainer();
    userNameLabel = new Label("Test");
    logoutButton = new TextButton("LOGOUT ", AppUtils.ICONS.logout());
    Label userText = new Label("Logged in as: ");

    HBoxLayoutContainer leftContainer = new HBoxLayoutContainer(HBoxLayoutContainer.HBoxLayoutAlign.MIDDLE);
    HBoxLayoutContainer rightContainer = new HBoxLayoutContainer(HBoxLayoutContainer.HBoxLayoutAlign.MIDDLE);
    leftContainer.setPack(BoxLayoutContainer.BoxLayoutPack.START);
    rightContainer.setPack(BoxLayoutContainer.BoxLayoutPack.END);

    leftContainer.add(userText, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 3, 0, 10)));
    leftContainer.add(userNameLabel);
    rightContainer.add(logoutButton, new BoxLayoutContainer.BoxLayoutData(new Margins(0, 10, 0, 0)));

    userText.getElement().getStyle().setProperty("fontWeight", "bold");
    userNameLabel.getElement().getStyle().setProperty("fontWeight", "bold");
    logoutButton.setIconAlign(ButtonCell.IconAlign.RIGHT);

    borderLayoutContainer.setWestWidget(leftContainer, new BorderLayoutContainer.BorderLayoutData(200));
    borderLayoutContainer.setEastWidget(rightContainer);
    borderLayoutContainer.getElement().getStyle().setBackgroundColor("#ffffff");
  }

  public Label getUserNameLabel() {
    return userNameLabel;
  }

  public TextButton getLogoutButton() {
    return logoutButton;
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