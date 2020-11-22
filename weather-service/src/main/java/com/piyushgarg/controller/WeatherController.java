package com.piyushgarg.controller;

import com.piyushgarg.model.WeatherDto;
import com.piyushgarg.service.WeatherService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(path = "/weather/{cityId}")
    public WeatherDto getWeatherInfo(@PathVariable("cityId") Integer cityId ) {

        return weatherService.getWeatherInfo(cityId);
    }
}
