package ru.maxow.location.dto;

public record LocationCreateDto(
   Double latitude,
   Double longitude,
   String cityName
) {}
