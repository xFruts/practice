package ru.maxow.practicespring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maxow.practicespring.dto.Message;
import ru.maxow.practicespring.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
  private final MessageService messageService;

  @GetMapping
  public List<Message> getMessages() {
    return messageService.findAll();
  }

  @GetMapping("/{id}")
  public Message findMessageById(@PathVariable int id) {
    return messageService.findById(id);
  }

  @PostMapping
  public Message addMessage(@RequestBody Message message) {
    return messageService.save(message);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
    HttpStatus status = messageService.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;

    return new ResponseEntity<>(messageService.update(id, message), status);
  }

  @DeleteMapping("/{id}")
  public void deletePerson(@PathVariable int id) {
    messageService.delete(id);
  }
}
