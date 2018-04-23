package com.switchfully.vaadin.exercise_05_binding_beans.ui.components;

import com.switchfully.vaadin.domain.Accomodation;
import com.switchfully.vaadin.domain.City;
import com.switchfully.vaadin.service.AccomodationService;
import com.switchfully.vaadin.service.CityService;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import org.springframework.beans.factory.annotation.Autowired;

import static com.switchfully.vaadin.domain.Accomodation.AccomodationBuilder.accomodation;

//https://vaadin.com/docs/v8/framework/components/components-combobox.html

public class EditAccomodationForm extends FormLayout {

    private Accomodation accomodation;
    private AccomodationService accomodationService;
    private CityService cityService;
    private BeanFieldGroup<Accomodation> binder;

    @Autowired
    public EditAccomodationForm(CityService cityService, AccomodationService accomodationService) {
        this.accomodation = accomodation().build();
        this.accomodationService = accomodationService;
        this.cityService = cityService;
        this.binder = new BeanFieldGroup<>(Accomodation.class);

        addNameField();
        addCityField();
        addNumberOfRoomsField();
        addRatingField();
    }

    private void addNameField() {
        addComponent(binder.buildAndBind("Name", "name"));
    }

    private void addCityField() {
        binder.setItemDataSource(accomodation);

        ComboBox cities = new ComboBox("City", cityService.getCities());
        for (City city : cityService.getCities()) {
            cities.setItemCaption(city, city.getName());
        }
        cities.setInputPrompt("Choose city");
        binder.bind(cities, "city");
        addComponent(cities);
    }

    private void addNumberOfRoomsField() {
        addComponent(binder.buildAndBind("Number of rooms", "numberOfRooms"));
    }

    private void addRatingField() {
        addComponent(binder.buildAndBind("Rating", "starRating"));
    }

    public Accomodation getAccomodation() {
        return accomodation;
    }

    public AccomodationService getAccomodationService() {
        return accomodationService;
    }

    public CityService getCityService() {
        return cityService;
    }
}