package ru.maxow.practicespring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.maxow.practicespring.dto.Message;
import ru.maxow.practicespring.repository.MessageRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
  private final MessageRepository repository;

  @GetMapping
  public List<Message> getMessages() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Message findMessageById(@PathVariable int id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public Message addMessage(@RequestBody Message message) {
    return repository.save(message);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
    HttpStatus status;

    if (repository.existsById(id)) {
      status = HttpStatus.OK;
      message.setId(id);
    }
    else {
      status = HttpStatus.CREATED;
    }

    return new ResponseEntity<>(repository.save(message), status);
  }

  @DeleteMapping("/{id}")
  public void deletePerson(@PathVariable int id) {
    repository.deleteById(id);
  }
}
