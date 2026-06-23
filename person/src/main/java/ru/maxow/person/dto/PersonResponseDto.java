package ru.maxow.person.dto;

public record PersonResponseDto(
    Long id,
    String name,
    String location
) {
}
