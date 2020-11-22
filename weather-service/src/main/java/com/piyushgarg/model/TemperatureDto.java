package com.piyushgarg.model;

import lombok.Data;

@Data
public class TemperatureDto {

    private String date;
    private Double max;
    private Double min;
    private String description;
    
}
