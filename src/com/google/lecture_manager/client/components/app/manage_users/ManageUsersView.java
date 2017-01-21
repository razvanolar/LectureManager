package com.google.lecture_manager.client.components.app.manage_users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.client.events.AddUserEvent;
import com.google.lecture_manager.client.events.DeleteUserEvent;
import com.google.lecture_manager.client.events.EditUserEvent;
import com.google.lecture_manager.client.utils.AppUtils;
import com.google.lecture_manager.client.utils.properties.UsersProperties;
import com.google.lecture_manager.shared.model.UserDTO;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class ManageUsersView implements ManageUsersController.IManageUsersView {
  private VerticalLayoutContainer mainContainer;
  private ListStore<UserDTO> listStore;
  private Grid<UserDTO> grid;
  private UsersProperties usersProperties = GWT.create(UsersProperties.class);
  private HorizontalLayoutContainer horizontalLayoutContainer;
  private TextButton addButton, editButton, deleteButton;

  public ManageUsersView() {
    initGUI();
    addHandlers();
  }

  private void addHandlers() {
    addButton.addSelectHandler(new SelectEvent.SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new AddUserEvent());
      }
    });

    editButton.addSelectHandler(new SelectEvent.SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new EditUserEvent(grid.getSelectionModel().getSelectedItem()));
      }
    });

    deleteButton.addSelectHandler(new SelectEvent.SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        AppUtils.EVENT_BUS.fireEvent(new DeleteUserEvent(grid.getSelectionModel().getSelectedItems()));
      }
    });
  }

  @Override
  public void initGUI() {
    mainContainer = new VerticalLayoutContainer();
    horizontalLayoutContainer = new HorizontalLayoutContainer();
    grid = createGrid();
    addButton = new TextButton("Add");
    editButton = new TextButton("Edit");
    editButton.setEnabled(false);
    deleteButton = new TextButton("Delete");
    deleteButton.setEnabled(false);
    horizontalLayoutContainer.add(addButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 10)));
    horizontalLayoutContainer.add(editButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 0)));
    horizontalLayoutContainer.add(deleteButton, new HorizontalLayoutContainer.HorizontalLayoutData(-1, -1, new Margins(0, 10, 0, 0)));

    mainContainer.add(horizontalLayoutContainer, new VerticalLayoutContainer.VerticalLayoutData(1, 30));
    mainContainer.add(grid, new VerticalLayoutContainer.VerticalLayoutData(1, 1));
  }

  private Grid<UserDTO> createGrid() {
    IdentityValueProvider<UserDTO> identityValueProvider = new IdentityValueProvider<>("sm");
    CheckBoxSelectionModel<UserDTO> selectionModel = new CheckBoxSelectionModel<>(identityValueProvider);
    selectionModel.setSelectionMode(Style.SelectionMode.MULTI);

    List<ColumnConfig<UserDTO, ?>> columnConfigs = new ArrayList<>();
    columnConfigs.add(selectionModel.getColumn());
    columnConfigs.add(new ColumnConfig<>(usersProperties.id(), 20, "ID"));
    columnConfigs.add(new ColumnConfig<>(usersProperties.firstName(), 100, "First Name"));
    columnConfigs.add(new ColumnConfig<>(usersProperties.lastName(), 100, "Last Name"));
    columnConfigs.add(new ColumnConfig<>(usersProperties.userName(), 100, "UserDTO Name"));
    columnConfigs.add(new ColumnConfig<>(usersProperties.email(), 100, "Email"));
    columnConfigs.add(new ColumnConfig<>(new ValueProvider<UserDTO, String>() {
      @Override
      public String getValue(UserDTO object) {
        return object.getType().name();
      }
      @Override
      public void setValue(UserDTO object, String value) {
      }
      @Override
      public String getPath() {
        return "type";
      }
    }, 100, "Type"));

    ColumnModel<UserDTO> columnModel = new ColumnModel<>(columnConfigs);
    listStore = new ListStore<>(usersProperties.key());
    Grid<UserDTO> userGrid = new Grid<>(listStore, columnModel);
    userGrid.getView().setAutoFill(true);
    userGrid.setSelectionModel(selectionModel);

    return userGrid;
  }

  public Grid<UserDTO> getGrid() {
    return grid;
  }

  public TextButton getEditButton() {
    return editButton;
  }

  public TextButton getDeleteButton() {
    return deleteButton;
  }

  @Override
  public Widget asWidget() {
    return mainContainer;
  }

  @Override
  public void mask(String message) {
    mainContainer.mask(message);
  }

  @Override
  public void unmask() {
    mainContainer.unmask();
  }
}