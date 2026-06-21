package ru.maxow.practicespring.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Message {
  @Id
  @GeneratedValue
  private int id;
  private String title;
  private String text;
  private LocalDateTime time;
}
