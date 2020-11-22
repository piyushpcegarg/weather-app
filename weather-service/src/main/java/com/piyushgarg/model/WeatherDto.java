package com.piyushgarg.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class WeatherDto {

    private List<TemperatureDto> temperature;
    private String suggestion;


    @Data
    class TemperatureDto {

        private Date date;
        private Float max;
        private Float min;
    }
}
