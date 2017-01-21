package com.google.lecture_manager.client.components.app;

import com.google.lecture_manager.client.events.ManageLecturesEvent;
import com.google.lecture_manager.client.events.ManageUsersEvent;
import com.google.lecture_manager.client.events.ShowHomeEvent;
import com.google.lecture_manager.client.handlers.ManageLecturesHandler;
import com.google.lecture_manager.client.handlers.ManageUsersHandler;
import com.google.lecture_manager.client.handlers.ShowHomeEventHandler;
import com.google.lecture_manager.client.utils.*;
import com.google.lecture_manager.client.utils.factories.AbstractFactory;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

public class AppController extends Controller<AppController.IAppView> {

  public interface IAppView extends View, MaskableView {
    BorderLayoutContainer getBorderLayoutContainer();
  }

  private static AppController INSTANCE = null;

  private IAppView view;

  @Override
  public void bind(final IAppView view) {
    this.view = view;
    setIsBound(true);
    AppUtils.EVENT_BUS.addHandler(ManageUsersEvent.TYPE, new ManageUsersHandler() {
      public void onManageUsersEvent(ManageUsersEvent event) {
        view.getBorderLayoutContainer().setCenterWidget(AbstractFactory.getWidget(ElementTypes.MANAGE_USERS));
        view.getBorderLayoutContainer().forceLayout();
      }
    });

    AppUtils.EVENT_BUS.addHandler(ManageLecturesEvent.TYPE, new ManageLecturesHandler() {
      public void onManageLecturesEvent(ManageLecturesEvent event) {
        view.getBorderLayoutContainer().setCenterWidget(AbstractFactory.getWidget(ElementTypes.MANAGE_LECTURES));
        view.getBorderLayoutContainer().forceLayout();
      }
    });

    AppUtils.EVENT_BUS.addHandler(ShowHomeEvent.TYPE, new ShowHomeEventHandler() {
      public void onShowHomeEvent(ShowHomeEvent event) {
        view.getBorderLayoutContainer().setCenterWidget(AbstractFactory.getWidget(ElementTypes.CENTER));
        view.getBorderLayoutContainer().forceLayout();
      }
    });
  }

  @Override
  public View getView() {
    return view;
  }

  public static Controller getInstance() {
    if (INSTANCE == null)
      INSTANCE = new AppController();
    return INSTANCE;
  }
} 
