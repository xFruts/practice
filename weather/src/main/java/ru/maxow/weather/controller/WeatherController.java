package ru.maxow.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maxow.weather.dto.WeatherResponseDto;
import ru.maxow.weather.service.WeatherService;

import java.util.List;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {
  private final WeatherService weatherService;

  @GetMapping
  public List<WeatherResponseDto> getAllWeather() {
    return weatherService.findAll();
  }

  @GetMapping
  public WeatherResponseDto getWeather(
      @RequestParam(name = "lat") double latitude,
      @RequestParam(name = "lot") double longitude
  ) {
    return weatherService.findByLatAndLon(latitude, longitude);
  }
}
