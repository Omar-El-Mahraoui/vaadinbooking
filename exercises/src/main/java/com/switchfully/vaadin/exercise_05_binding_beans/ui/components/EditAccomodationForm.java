package com.switchfully.vaadin.exercise_05_binding_beans.ui.components;

import com.switchfully.vaadin.domain.Accomodation;
import com.switchfully.vaadin.domain.City;
import com.switchfully.vaadin.domain.StarRating;
import com.switchfully.vaadin.service.AccomodationService;
import com.switchfully.vaadin.service.CityService;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

//https://vaadin.com/docs/v8/framework/components/components-combobox.html

public class EditAccomodationForm extends FormLayout {

    private Accomodation accomodation;
    private AccomodationService accomodationService;
    private CityService cityService;
    private BeanFieldGroup<Accomodation> binder;

    @Autowired
    public EditAccomodationForm(CityService cityService, AccomodationService accomodationService, Accomodation accomodation) {
        this.accomodation = accomodation;
        this.accomodationService = accomodationService;
        this.cityService = cityService;
        this.binder = new BeanFieldGroup<>(Accomodation.class);
        binder.setItemDataSource(accomodation);

        addNameField();
        addCityField();
        addNumberOfRoomsField();
        addRatingField();
    }

    private void addNameField() {
        addComponent(binder.buildAndBind("Name", "name"));
    }

    private void addCityField() {
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
        ComboBox ratings = new ComboBox("Rating", Arrays.asList(StarRating.values()));

        for (StarRating rating : StarRating.values()) {
            ratings.setItemCaption(rating, rating.toString());
        }
        ratings.setInputPrompt("Choose rating");
        binder.bind(ratings, "starRating");
        addComponent(ratings);
    }

    public void commitBinder() throws FieldGroup.CommitException {
        binder.commit();
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