package com.piyushgarg.service;

import java.util.List;

import com.piyushgarg.model.City;
import com.piyushgarg.repository.CityRepository;

import org.springframework.stereotype.Service;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> fetchCities() {
        return cityRepository.findAll();
    }
     
}
