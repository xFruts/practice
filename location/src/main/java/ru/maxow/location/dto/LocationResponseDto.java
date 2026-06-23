package ru.maxow.location.dto;

public record LocationResponseDto(
   Long id,
   Double latitude,
   Double longitude,
   String cityName
) {}
