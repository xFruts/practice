package ru.maxow.location.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maxow.common.util.NotFoundException;
import ru.maxow.location.dto.LocationCreateDto;
import ru.maxow.location.dto.LocationResponseDto;
import ru.maxow.location.entity.Location;
import ru.maxow.location.mapper.LocationMapper;
import ru.maxow.location.repository.LocationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
  private final LocationRepository locationRepository;
  private final LocationMapper locationMapper;

  public List<LocationResponseDto> findAll() {
    return locationRepository.findAll().stream()
        .map(locationMapper::toLocationResponseDto)
        .toList();
  }

  public LocationResponseDto findById(Long id) {
    return locationRepository.findById(id)
        .map(locationMapper::toLocationResponseDto)
        .orElseThrow(() -> new NotFoundException(Location.class, id));
  }

  public LocationResponseDto save(LocationCreateDto location) {
    Location savedLocation = locationRepository.save(locationMapper.toEntity(location));
    return locationMapper.toLocationResponseDto(savedLocation);
  }

  public void deleteById(Long id) {
    locationRepository.deleteById(id);
  }
}
