package ru.maxow.location.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.maxow.location.dto.LocationCreateDto;
import ru.maxow.location.dto.LocationResponseDto;
import ru.maxow.location.service.LocationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

  private final LocationService locationService;

  @GetMapping
  public List<LocationResponseDto> getLocations() {
    return locationService.findAll();
  }

  @GetMapping("/{id}")
  public LocationResponseDto getLocationById(Long id) {
    return locationService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LocationResponseDto createLocation(LocationCreateDto locationCreateDto) {
    return locationService.save(locationCreateDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteLocationById(Long id) {
    locationService.deleteById(id);
  }
}
