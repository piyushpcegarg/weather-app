package com.piyushgarg.repository;

import com.piyushgarg.model.City;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {

}
