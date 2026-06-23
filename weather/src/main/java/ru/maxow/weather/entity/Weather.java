package ru.maxow.weather.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Weather {
  @Id
  @GeneratedValue
  private Long id;

  private Double latitude;
  private Double longitude;

  private Double temperature;

  private String description;
}
