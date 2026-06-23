package ru.maxow.person.dto;

import java.time.LocalDate;

public record PersonCreateDto (
    String firstname,
    String surname,
    String lastname,
    LocalDate birthday,
    String location
) {}
