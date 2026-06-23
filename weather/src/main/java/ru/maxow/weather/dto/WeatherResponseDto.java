package ru.maxow.weather.dto;

public record WeatherResponseDto(
    Long id,
    Double latitude,
    Double longitude,
    Double temperature,
    String description
) {}
