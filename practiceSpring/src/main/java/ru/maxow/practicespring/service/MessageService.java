package ru.maxow.practicespring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.maxow.practicespring.dto.Message;
import ru.maxow.practicespring.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

  private final MessageRepository repository;

  public List<Message> findAll() {
    return repository.findAll();
  }

  public Message findById(int id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Message save(Message message) {
    if (message.getTime() == null) {
      message.setTime(LocalDateTime.now());
    }
    return repository.save(message);
  }

  public Message update(int id, Message message) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    message.setId(id);

    if (message.getTime() == null) {
      message.setTime(LocalDateTime.now());
    }

    return repository.save(message);
  }

  public void delete(int id) {
    repository.deleteById(id);
  }

  public boolean existsById(int id) {
    return repository.existsById(id);
  }

  public Message getPersonMessageById(int personId, int messageId) {
    return repository.findByIdAndPerson_Id(messageId, personId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }
}
