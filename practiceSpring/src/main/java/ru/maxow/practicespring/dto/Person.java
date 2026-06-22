package ru.maxow.practicespring.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

  @OneToMany(cascade = CascadeType.ALL)
  List<Message> messages;

  public void addMessage(Message message) {
    messages.add(message);
  }
}
