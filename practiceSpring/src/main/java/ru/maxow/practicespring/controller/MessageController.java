package ru.maxow.practicespring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.maxow.practicespring.dto.Message;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
  private List<Message> messages = new ArrayList<>();

  @GetMapping("/message")
  public Iterable<Message> getMessages() {
    return messages;
  }

  @GetMapping("/message/{id}")
  public Message getMessage(@PathVariable int id) {
    if (id < 0 || id >= messages.size()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return messages.get(id);
  }

  @PostMapping("/message")
  public Message addMessage(@RequestBody Message message) {
    messages.add(message);
    return message;
  }

  @PutMapping("/message/{id}")
  public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
    int index = - 1;
    for (Message p : messages) {
      if (p.getId() == id) {
        index = messages.indexOf(p);
        messages.set(index, message);
      }
    }
    return index == -1
        ? new ResponseEntity<>(addMessage(message), HttpStatus.CREATED)
        : new ResponseEntity<>(message, HttpStatus.OK);
  }

  @DeleteMapping("/message/{id}")
  public void deleteMessage(@PathVariable int id) {
    messages.removeIf(p -> p.getId() == id);
  }
}
