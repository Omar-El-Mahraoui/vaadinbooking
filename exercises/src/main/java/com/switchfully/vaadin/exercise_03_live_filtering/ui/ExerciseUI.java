package com.switchfully.vaadin.exercise_03_live_filtering.ui;

import com.switchfully.vaadin.domain.Accomodation;
import com.switchfully.vaadin.service.AccomodationService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class ExerciseUI extends UI {

    private Grid grid = new Grid();
    private TextField textFieldFilter;
    private Button buttonDelete;

    private AccomodationService accomodationService;

    @Autowired
    public ExerciseUI(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
        textFieldFilter = new TextField();
        buttonDelete = new Button("X");
    }

    @Override
    protected void init(VaadinRequest request) {
        HorizontalLayout filterAndDeleteButtonLayout = new HorizontalLayout(textFieldFilter,buttonDelete);
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.addComponent(filterAndDeleteButtonLayout);
        mainLayout.addComponent(grid);

        BeanItemContainer<Accomodation> container =
                new BeanItemContainer<>(Accomodation.class, accomodationService.getAccomodations());

        container.addNestedContainerProperty("city.name");

        grid.setColumns("name", "starRating", "city.name");

        grid.getColumn("city.name").setHeaderCaption("City");

        grid.setContainerDataSource(container);
        // TODO Exercise 3: Add a filter TextField to the top of the grid to filter the list of accomodations by name.
        // switchfully presentation vaadin
        // https://stackoverflow.com/questions/42475774/how-to-add-grid-filters-in-vaadin-8
        textFieldFilter.addTextChangeListener(e -> container.addContainerFilter("name", e.getText()
                ,true, false));

        // TODO Exercise 3: Add a button next to the filter TextField to clear the filter.
        buttonDelete.addClickListener(event -> {
                                                    textFieldFilter.clear();
                                                    container.removeAllContainerFilters();
                                                });

        mainLayout.setMargin(true);
        setContent(mainLayout);
    }

}