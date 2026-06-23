package ru.maxow.location.mapper;

import org.mapstruct.Mapper;
import ru.maxow.location.dto.LocationCreateDto;
import ru.maxow.location.dto.LocationResponseDto;
import ru.maxow.location.entity.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
  LocationResponseDto toLocationResponseDto(Location location);

  Location toEntity(LocationCreateDto locationCreateDto);
}
