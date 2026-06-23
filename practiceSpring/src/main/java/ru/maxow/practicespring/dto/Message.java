package ru.maxow.practicespring.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class Message {
  private int id;
  private String title;
  private String text;
  private LocalDateTime time;
}
