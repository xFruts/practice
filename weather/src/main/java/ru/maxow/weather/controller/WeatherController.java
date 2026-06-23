package ru.maxow.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maxow.weather.model.Root;
import ru.maxow.weather.service.WeatherService;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {
  private final WeatherService weatherService;

  @GetMapping
  public Root getWeather(
      @RequestParam(name = "lat") Double lat,
      @RequestParam(name = "lon") Double lon
  ) {
    return weatherService.getWeather(lat, lon);
  }
}
