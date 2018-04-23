package com.switchfully.vaadin.exercise_05_binding_beans.ui;

import com.switchfully.vaadin.exercise_05_binding_beans.ui.components.AccomodationAdmin;
import com.switchfully.vaadin.service.AccomodationService;
import com.switchfully.vaadin.service.CityService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

// switchfully presentation vaadin

@SpringUI
@Theme("valo")
public class ExerciseUI extends UI {

    private AccomodationService accomodationService;
    private CityService cityService;

    @Autowired
    public ExerciseUI(AccomodationService accomodationService, CityService cityService) {
        this.accomodationService = accomodationService;
        this.cityService = cityService;
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(new AccomodationAdmin(this.accomodationService, this.cityService));
    }

}