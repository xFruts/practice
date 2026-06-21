package ru.maxow.practicespring.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Person {
  @Id
  @GeneratedValue
  private int id;
  private String firstname;
  private String surname;
  private String lastname;
  private LocalDate birthday;
}
