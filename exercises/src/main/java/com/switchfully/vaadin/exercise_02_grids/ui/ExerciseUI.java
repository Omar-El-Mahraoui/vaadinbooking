package com.switchfully.vaadin.exercise_02_grids.ui;

import com.switchfully.vaadin.domain.Accomodation;
import com.switchfully.vaadin.service.AccomodationService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class ExerciseUI extends UI {

    private AccomodationService accomodationService;

    @Autowired
    public ExerciseUI(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout mainLayout = new VerticalLayout();

        // TODO Exercise 2: Show the list of accomodations from accomodationService.getAccomodations() in a Grid.

        // Use BeanItemContainer as the ContainerDataSource for the Grid.

        // Try to only show the following properties of an accomodation:
        // - Name
        // - Star Rating
        // - City Name
        // https://vaadin.com/docs/v7/framework/layout/layout-gridlayout.html
        // switchfully presentations

        BeanItemContainer<Accomodation> itemContainer =
                new BeanItemContainer<>(Accomodation.class, accomodationService.getAccomodations());
        itemContainer.addNestedContainerProperty("city.name");

        Grid grid = new Grid(itemContainer);
        grid.setColumns("name", "starRating", "city.name");
        //https://stackoverflow.com/questions/45423636/change-column-name-vaadin-7-8-4
        grid.getColumn("city.name").setHeaderCaption("city");
        mainLayout.addComponent(grid);


        mainLayout.setMargin(true);
        setContent(mainLayout);
    }

}