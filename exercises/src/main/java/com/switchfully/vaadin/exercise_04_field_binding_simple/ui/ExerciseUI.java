package com.switchfully.vaadin.exercise_04_field_binding_simple.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

@SpringUI
@Theme("valo")
public class ExerciseUI extends UI {

    private Label labelNameTop;
    private TextField textField;
    private Label labelNameBottom;
    private Button buttonUpdate;
    private Property name;
    private CheckBox checkBoxAutoCommit;

    public ExerciseUI() {
        labelNameTop = new Label("Name");
        name = new ObjectProperty<>("");
        textField = new TextField(name);
        textField.setBuffered(true);

        labelNameBottom = new Label(name);
        checkBoxAutoCommit = new CheckBox("Auto commit?");
        buttonUpdate = new Button("Update");
    }

    @Override
    protected void init(VaadinRequest request) {
        // TODO Exercise 4: Create a TextField and a Label, both bound to the same 'name' Property.
        VerticalLayout mainLayout = new VerticalLayout(labelNameTop
                                                        , textField
                                                        , labelNameBottom
                                                        , checkBoxAutoCommit
                                                        , buttonUpdate);

        // TODO Exercise 4: Add a button to commit the field.
        // TODO Exercise 4: Clicking the button should update the Label with the value in the TextField.
        buttonUpdate.addClickListener(event -> textField.commit());
        // TODO Exercise 4 (Extra): Add a checkbox to hide the button and make the TextField auto-commit.
        checkBoxAutoCommit.addFocusListener(event ->
                            textField.addTextChangeListener(event1 -> name.setValue(event1.getText())));

        mainLayout.setMargin(true);
        setContent(mainLayout);
    }

}