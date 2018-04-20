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
        this.labelNameTop = new Label("Name");

        this.name = new ObjectProperty<>("");
        this.textField = new TextField(name);
        this.textField.setBuffered(true);

        this.labelNameBottom = new Label(name);
        this.checkBoxAutoCommit = new CheckBox("Auto commit?");
        this.buttonUpdate = new Button("Update");
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
        buttonUpdate.addClickListener(event -> this.textField.commit());
        // TODO Exercise 4 (Extra): Add a checkbox to hide the button and make the TextField auto-commit.
        checkBoxAutoCommit.addFocusListener(event ->
                            textField.addTextChangeListener(event1 -> name.setValue(event1.getText())));

        mainLayout.setMargin(true);
        setContent(mainLayout);
    }

}