package com.piyushgarg.service;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.piyushgarg.model.City;
import com.piyushgarg.model.TemperatureDto;
import com.piyushgarg.repository.CityRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

@Service
public class WeatherService {

    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/onecall?" +
        "lat={lat}" + 
        "&lon={lon}" + 
        "&units=metric" + 
        "&exclude=current,minutely,hourly,alerts" + 
        "&appid={key}";

    @Value("${api.openweathermap.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final CityRepository cityRepository;

    public WeatherService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper, CityRepository cityRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
        this.cityRepository = cityRepository;
    }

    public List<TemperatureDto> getWeatherInfo(Integer cityId) {

        City city = cityRepository.findById(cityId).orElse(null);
        if (city == null) {
            throw new RuntimeException("City not found");
        }
        URI url = new UriTemplate(WEATHER_URL).expand(city.getLatitude(), city.getLongitude(), apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return convert(response);
    }
    

    private List<TemperatureDto> convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());

            List<TemperatureDto> temperatureDtos = new ArrayList<>();

            TemperatureDto temperatureDto;
            root = root.path("daily");
            for (int loopCount = 0; loopCount < 3; loopCount++) {

                temperatureDto = new TemperatureDto();

                JsonNode node = root.get(loopCount);

                temperatureDto.setDate(LocalDate.now().plusDays(loopCount).toString());
                
                JsonNode temperature = node.path("temp");
                temperatureDto.setMax(temperature.path("max").asDouble());
                temperatureDto.setMin(temperature.path("min").asDouble());
                temperatureDto.setDescription(node.path("weather").get(0).path("main").asText());
                temperatureDto.setSuggestion(getSuggestion(temperatureDto));
                
                temperatureDtos.add(temperatureDto);
            }

            return temperatureDtos;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }

    private String getSuggestion(TemperatureDto temperatureDto) {

        String suggestion = "";

        if (temperatureDto.getDescription().equals("Rain")) {
            suggestion = "Carry Umbrella";
        } else if (temperatureDto.getMax() > 40) {
            suggestion = "Use Sunscreen Lotion";
        }
        return suggestion;
    }
}
