package ru.maxow.person.dto;

import java.time.LocalDate;

public record PersonResponseDto(
    Long id,
    String firstname,
    String surname,
    String lastname,
    LocalDate birthday,
    String location
) {
}
