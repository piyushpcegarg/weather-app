package com.piyushgarg.controller;

import java.util.List;

import com.piyushgarg.model.City;
import com.piyushgarg.service.CityService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(path = "/cities")
    public List<City> getCities() {

        return cityService.fetchCities();
    }
    
}
