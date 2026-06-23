package ru.maxow.practicespring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.maxow.practicespring.dto.Person;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
  private List<Person> persons = new ArrayList<>();

  @GetMapping("/person")
  public Iterable<Person> getPersons() {
    return persons;
  }

  @GetMapping("/person/{id}")
  public Person getPerson(@PathVariable int id) {
    if (id < 0 || id >= persons.size()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return persons.get(id);
  }

  @PostMapping("/person")
  public Person addPerson(@RequestBody Person person) {
    persons.add(person);
    return person;
  }

  @PutMapping("/person/{id}")
  public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
    int index = - 1;
    for (Person p : persons) {
      if (p.getId() == id) {
        index = persons.indexOf(p);
        persons.set(index, person);
      }
    }
    return index == -1
        ? new ResponseEntity<>(addPerson(person), HttpStatus.CREATED)
        : new ResponseEntity<>(person, HttpStatus.OK);
  }

  @DeleteMapping("/person/{id}")
  public void deletePerson(@PathVariable int id) {
    persons.removeIf(p -> p.getId() == id);
  }
}
