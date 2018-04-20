package com.switchfully.vaadin.exercise_01_basic_layouts.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

//http://vaadin.com/download/release/6.4/6.4.1/docs/vaadin-tutorial.pdf

@SpringUI
public class ExerciseUI extends UI {

    @Autowired
    public ExerciseUI() {
    }

    @Override
    protected void init(VaadinRequest request) {
        // TODO Exercise 1: Using VerticalLayout and HorizontalLayout, create a button layout resembling the buttons of an old school cellphone.
        // Use the Button component to create the buttons:
        // Button one = new Button("1");

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout row1 = new HorizontalLayout();
        HorizontalLayout row2 = new HorizontalLayout();
        HorizontalLayout row3 = new HorizontalLayout();
        HorizontalLayout row4 = new HorizontalLayout();

        String[] charactersFirstRow = {"1", "2", "3"};
        String[] charactersSecondRow = {"4", "5", "6"};
        String[] charactersThirdRow = {"7", "8", "9"};
        String[] charactersFourthRow = {"*", "0", "#"};

        for (String number : charactersFirstRow) {
            row1.addComponent(new Button(number));
        }
        verticalLayout.addComponent(row1);
        for (String number : charactersSecondRow) {
            row2.addComponent(new Button(number));
        }
        verticalLayout.addComponent(row2);
        for (String number : charactersThirdRow) {
            row3.addComponent(new Button(number));
        }
        verticalLayout.addComponent(row3);
        for (String number : charactersFourthRow) {
            row4.addComponent(new Button(number));
        }
        verticalLayout.addComponent(row4);

        setContent(verticalLayout);

        // Don't forget to set your main layout using setContent(myLayout)

    }

}