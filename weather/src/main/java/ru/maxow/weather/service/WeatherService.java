package ru.maxow.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maxow.common.util.NotFoundException;
import ru.maxow.weather.dto.WeatherResponseDto;
import ru.maxow.weather.mapper.WeatherMapper;
import ru.maxow.weather.repository.WeatherRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

  private final WeatherRepository weatherRepository;
  private final WeatherMapper weatherMapper;

  public List<WeatherResponseDto> findAll() {
    return weatherRepository.findAll().stream()
        .map(weatherMapper::toDto)
        .toList();
  }

  public WeatherResponseDto findByLatAndLon(Double latitude, Double longitude) {
    return weatherRepository.findByLatitudeAndLongitude(latitude, longitude)
        .map(weatherMapper::toDto)
        .orElseThrow(() -> new NotFoundException("Weather not found for lat " + latitude + " and lon " + longitude));
  }
}
