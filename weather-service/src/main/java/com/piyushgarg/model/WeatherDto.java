package com.piyushgarg.model;

import java.util.List;

import lombok.Data;

@Data
public class WeatherDto {

    private List<TemperatureDto> temperature;
    private String suggestion;
}
