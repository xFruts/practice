package ru.maxow.practicespring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.maxow.practicespring.dto.Message;
import ru.maxow.practicespring.dto.Person;
import ru.maxow.practicespring.repository.PersonRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository personRepository;
  private final MessageService messageService;

  public List<Person> findAll() {
    return personRepository.findAll();
  }

  public Person findById(int id) {
    return personRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Person save(Person person) {
    return personRepository.save(person);
  }

  @Transactional
  public Person addMessageToPerson(int personId, Message message) {
    Person person = personRepository.findById(personId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    message.setPerson(person);
    message.setTime(LocalDateTime.now());
    person.addMessage(message);
    return personRepository.save(person);
  }

  @Transactional(readOnly = true)
  public List<Message> getPersonMessages(int id) {
    Person person = personRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    return person.getMessages();
  }

  @Transactional(readOnly = true)
  public Message getPersonMessageById(int personId, int messageId) {
    return messageService.getPersonMessageById(personId, messageId);
  }

  @Transactional
  public void deletePersonMessageById(int personId, int messageId) {
    Person person = personRepository.findById(personId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    Message messageToDelete = person.getMessages().stream()
        .filter(m -> m.getId() == messageId)
        .findFirst()
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    person.getMessages().remove(messageToDelete);

    personRepository.save(person);
  }

  public boolean existsById(int id) {
    return personRepository.existsById(id);
  }

  public Person update(int id, Person person) {
    person.setId(id);
    return personRepository.save(person);
  }

  public void deleteById(int id) {
    personRepository.deleteById(id);
  }
}
