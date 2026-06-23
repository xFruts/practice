package ru.maxow.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.maxow.weather.model.Root;

@Service
@RequiredArgsConstructor
public class WeatherService {

  private final RestTemplate restTemplate;

  @Value("${openweathermap.api-url}")
  private String apiUrl;

  @Value("${openweathermap.api-key}")
  private String apiKey;

  @Cacheable(value = "weather", key = "#lat + '_' + #lon" , unless = "#result == null")
  public Root getWeather(Double lat, Double lon) {
    String url = String.format("%s?lat=%s&lon=%s&appid=%s&units=metric", apiUrl, lat, lon, apiKey);
    return restTemplate.getForObject(url, Root.class);
  }
}
