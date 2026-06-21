package ru.maxow.practicespring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.maxow.practicespring.dto.Person;
import ru.maxow.practicespring.repository.PersonRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
  private final PersonRepository repository;

  @GetMapping
  public List<Person> getPersons() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Person findPersonById(@PathVariable int id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public Person addPerson(@RequestBody Person person) {
    repository.save(person);
    return person;
  }

  @PutMapping("/{id}")
  public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
    HttpStatus status;

    if (repository.existsById(id)) {
      status = HttpStatus.OK;
      person.setId(id);
    }
    else {
      status = HttpStatus.CREATED;
    }

    return new ResponseEntity<>(repository.save(person), status);
  }

  @DeleteMapping("/{id}")
  public void deletePerson(@PathVariable int id) {
    repository.deleteById(id);
  }
}
