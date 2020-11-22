package com.piyushgarg.controller;

import java.util.List;

import com.piyushgarg.model.TemperatureDto;
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
    public List<TemperatureDto> getWeatherInfo(@PathVariable("cityId") Integer cityId ) {

        return weatherService.getWeatherInfo(cityId);
    }
}
