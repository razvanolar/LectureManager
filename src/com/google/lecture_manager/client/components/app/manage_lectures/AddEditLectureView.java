package com.google.lecture_manager.client.components.app.manage_lectures;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.lecture_manager.shared.model.Teacher;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class AddEditLectureView implements AddEditLectureController.IAddEditLectureView {
  private TextField nameField, enrolmentField;
  private ComboBox<Teacher> teacherComboBox;
  private ListStore<Teacher> listStore;
  private VerticalLayoutContainer mainContainer;
  private TextButton applyButton;
  private Window window;

  public AddEditLectureView() {
    initGUI();
  }

  @Override
  public void initGUI() {
    window = new Window();
    nameField = new TextField();
    enrolmentField = new TextField();
    listStore = new ListStore<>(new ModelKeyProvider<Teacher>() {
      @Override
      public String getKey(Teacher item) {
        return item.getId() + "";
      }
    });
    teacherComboBox = new ComboBox<>(listStore, new LabelProvider<Teacher>() {
      @Override
      public String getLabel(Teacher item) {
        return item.getFirstName() + " " + item.getLastName();
      }
    });
    teacherComboBox.setForceSelection(true);
    teacherComboBox.setTypeAhead(false);
    teacherComboBox.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
    teacherComboBox.setAllowBlank(false);
    teacherComboBox.setAllowTextSelection(false);
    teacherComboBox.setEditable(false);
    applyButton = new TextButton("Apply");

    FieldLabel nameLabel = new FieldLabel(nameField, "Lecture Name");
    FieldLabel teacherLabel = new FieldLabel(teacherComboBox, "Teacher");
    FieldLabel enrolmentLabel = new FieldLabel(enrolmentField, "Enrolment Key");
    int labelWidth = 120;
    int textFieldWidth = 300;
    nameLabel.setLabelWidth(labelWidth);
    teacherLabel.setLabelWidth(labelWidth);
    enrolmentLabel.setLabelWidth(labelWidth);
    nameLabel.setWidth(textFieldWidth);
    teacherLabel.setWidth(textFieldWidth);
    enrolmentLabel.setWidth(textFieldWidth);

    VBoxLayoutContainer vBoxLayoutContainer = new VBoxLayoutContainer(VBoxLayoutContainer.VBoxLayoutAlign.STRETCHMAX);
    vBoxLayoutContainer.add(nameLabel);
    vBoxLayoutContainer.add(teacherLabel);
    vBoxLayoutContainer.add(enrolmentLabel);

    HBoxLayoutContainer horizontalLayoutContainer = new HBoxLayoutContainer(HBoxLayoutContainer.HBoxLayoutAlign.MIDDLE);
    horizontalLayoutContainer.setPack(BoxLayoutContainer.BoxLayoutPack.END);
    BoxLayoutContainer.BoxLayoutData flex = new BoxLayoutContainer.BoxLayoutData();
    flex.setFlex(1);
    horizontalLayoutContainer.add(new Label(), flex);
    horizontalLayoutContainer.add(applyButton);
    vBoxLayoutContainer.add(horizontalLayoutContainer);

    mainContainer = new VerticalLayoutContainer();
    mainContainer.add(vBoxLayoutContainer, new VerticalLayoutContainer.VerticalLayoutData(1, 1, new Margins(5)));

    window.add(mainContainer);
    window.setSize("320", "180");
    window.setModal(true);
    window.setResizable(false);
    window.setHeadingText("Lecture Management");
  }

  @Override
  public Widget asWidget() {
    return window;
  }

  @Override
  public void mask(String message) {
    mainContainer.mask(message);
  }

  @Override
  public void unmask() {
    mainContainer.unmask();
  }

  public void close() {
    window.hide();
  }

  public TextField getNameField() {
    return nameField;
  }

  public TextField getEnrolmentField() {
    return enrolmentField;
  }

  public ComboBox<Teacher> getTeacherComboBox() {
    return teacherComboBox;
  }

  public TextButton getApplyButton() {
    return applyButton;
  }
}