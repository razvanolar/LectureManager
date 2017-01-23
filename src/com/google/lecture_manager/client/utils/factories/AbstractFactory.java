package com.google.lecture_manager.client.utils.factories;

import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.utils.Controller;
import com.google.lecture_manager.client.utils.ElementTypes;
import com.google.lecture_manager.client.utils.View;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Created by razvanolar on 27.10.2016
 */
public class AbstractFactory {

  private static ViewFactory VIEW_FACTORY = new ViewFactory();
  private static ControllerFactory CONTROLLER_FACTORY = new ControllerFactory();

  @SuppressWarnings("unchecked")
  public static Controller getController(ElementTypes type) {
    Controller controller = CONTROLLER_FACTORY.getController(type);
    if (type == ElementTypes.APP) {
      bindController(CONTROLLER_FACTORY.getController(ElementTypes.HEADER), ElementTypes.HEADER);
      bindController(CONTROLLER_FACTORY.getController(ElementTypes.CENTER), ElementTypes.CENTER);
    }
    bindController(controller, type);
    return controller;
  }

  @SuppressWarnings("unchecked")
  private static void bindController(Controller controller, ElementTypes type) {
    if (!controller.isBound() || controller.getView() == null) {
      View view = VIEW_FACTORY.getView(type);
      controller.bind(view);
      if (type == ElementTypes.SHOW_APPLY_FOR_LECTURE)
        Info.display("Factory", "get app controller");
    }
    controller.setDefaults();
  }

  public static Widget getWidget(ElementTypes type) {
    return getController(type).getView().asWidget();
  }
}
