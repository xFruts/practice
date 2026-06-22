package ru.maxow.practicespring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maxow.practicespring.dto.Message;
import ru.maxow.practicespring.dto.Person;
import ru.maxow.practicespring.service.PersonService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
  private final PersonService personService;

  @GetMapping
  public List<Person> getPersons() {
    return personService.findAll();
  }

  @GetMapping("/{id}")
  public Person findPersonById(@PathVariable int id) {
    return personService.findById(id);
  }

  @PostMapping
  public Person addPerson(@RequestBody Person person) {
    return personService.save(person);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
    HttpStatus status = personService.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
    return new ResponseEntity<>(personService.update(id, person), status);
  }

  @DeleteMapping("/{id}")
  public void deletePerson(@PathVariable int id) {
    personService.deleteById(id);
  }

  @GetMapping("/{p_id}/message")
  public List<Message> getPersonMessages(@PathVariable("p_id") int personId) {
    return personService.getPersonMessages(personId);
  }

  @GetMapping("/{p_id}/message/{m_id}")
  public Message getPersonMessageById(@PathVariable("p_id") int personId, @PathVariable("m_id") int messageId) {
    return personService.getPersonMessageById(personId, messageId);
  }

  @PostMapping("/{p_id}/message")
  public Person addMessage(@PathVariable("p_id") int pId, @RequestBody Message message) {
    return personService.addMessageToPerson(pId, message);
  }

  @DeleteMapping("/{p_id}/message/{m_id}")
  public void deletePersonMessage(@PathVariable("p_id") int personId, @PathVariable("m_id") int messageId) {
    personService.deletePersonMessageById(personId, messageId);
  }
}
