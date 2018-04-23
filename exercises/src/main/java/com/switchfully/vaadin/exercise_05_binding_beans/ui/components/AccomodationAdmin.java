package com.switchfully.vaadin.exercise_05_binding_beans.ui.components;

import com.switchfully.vaadin.domain.Accomodation;
import com.switchfully.vaadin.service.AccomodationService;
import com.switchfully.vaadin.service.CityService;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.switchfully.vaadin.domain.Accomodation.AccomodationBuilder.accomodation;
import static java.util.stream.Collectors.toList;

//https://vaadin.com/docs/v8/framework/layout/layout-splitpanel.html
// switchfully presentation vaadin

public class AccomodationAdmin extends CustomComponent {

    private EditAccomodationForm editAccomodationForm;
    private Grid grid = new Grid();

    private AccomodationService accomodationService;
    private CityService cityService;
    private TextField filter;

    @Autowired
    public AccomodationAdmin(AccomodationService accomodationService, CityService cityService) {
        this.accomodationService = accomodationService;
        this.cityService = cityService;

        HorizontalSplitPanel hsplit = new HorizontalSplitPanel();

        List<Accomodation> accomodations = accomodationService.getAccomodations();
        populateGrid(accomodations);
        CssLayout filtering = createFilterComponent(accomodations);

        // TODO Exercise 5: Add a 'New Accomodation' button.
        Button buttonAddNewAccomodation = addNewAccomodationButton();
        HorizontalLayout toolbar = new HorizontalLayout(filtering, buttonAddNewAccomodation);
        toolbar.setSpacing(true);
        VerticalLayout mainLayout = new VerticalLayout(toolbar, grid);
        mainLayout.setMargin(true);
        hsplit.setFirstComponent(mainLayout);

        // TODO Exercise 5: Create an EditAccomodationForm and add it to the right of the grid to add a new accomodation.
        hsplit.setSecondComponent(createEditAccomodationForm(accomodation().build()));

        // TODO Exercise 5: When selecting an accomodation in the grid, load it in the EditAccomodationForm to update it.
        updateExistingAccomodation();
        // TODO Exercise 5: Add a 'Delete' button to the form to delete an accomodation.
        // TODO Exercise 5: Add a 'Cancel' button to the form to close the form.
        // TODO Exercise 5 (Extra): Add ta DateField for creationDate to the form.

        setCompositionRoot(hsplit);
    }

    private void updateExistingAccomodation() {
        
    }

    private Component createEditAccomodationForm(Accomodation accomodation) {
        // switchfully presentation vaadin
        editAccomodationForm = new EditAccomodationForm(cityService, accomodationService, accomodation);
        editAccomodationForm.setVisible(false);

        Button buttonSave = new Button("Save");
        editAccomodationForm.addComponent(buttonSave);
        buttonSave.addClickListener(event -> {
            try {
                editAccomodationForm.commitBinder();
            } catch (FieldGroup.CommitException e) {
                e.printStackTrace();
            }
            accomodationService.save(editAccomodationForm.getAccomodation());
            populateGrid(accomodationService.getAccomodations());
        });
        return editAccomodationForm;
    }

    private Button addNewAccomodationButton() {
        Button addNewButton = new Button("Add new accomodation");
        addNewButton.addClickListener(event -> {
            if (!editAccomodationForm.isVisible()){
                editAccomodationForm.setVisible(true);
            }
        });
        return addNewButton;
    }

    private CssLayout createFilterComponent(List<Accomodation> accomodations) {
        filter = new TextField();
        filter.setInputPrompt("Filter by name...");
        filter.addTextChangeListener(e -> populateGrid(filterByName(accomodations, e.getText())));

        Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener(e -> {
            filter.clear();
            populateGrid(accomodations);
        });

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filter, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        return filtering;
    }

    private List<Accomodation> filterByName(List<Accomodation> accomodations, String filter) {
        return accomodations.stream()
                .filter(accomodation -> accomodation.getName().toLowerCase().contains(filter.toLowerCase()))
                .collect(toList());
    }

    private void populateGrid(List<Accomodation> accomodations) {
        BeanItemContainer<Accomodation> container =
                new BeanItemContainer<>(Accomodation.class, accomodations);

        container.addNestedContainerProperty("city.name");

        grid.setColumns("name", "starRating", "city.name");

        grid.getColumn("city.name").setHeaderCaption("City");

        grid.setContainerDataSource(container);
    }

}
