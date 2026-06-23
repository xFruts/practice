package ru.maxow.practicespring.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class Person {
  private int id;
  private String firstname;
  private String surname;
  private String lastname;
  private LocalDate birthday;
}