package ru.maxow.weather.mapper;

import org.mapstruct.Mapper;
import ru.maxow.weather.dto.WeatherResponseDto;
import ru.maxow.weather.entity.Weather;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    WeatherResponseDto toDto(Weather weather);
}
