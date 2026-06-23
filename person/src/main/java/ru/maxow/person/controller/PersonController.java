package ru.maxow.person.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.maxow.person.dto.PersonCreateDto;
import ru.maxow.person.dto.PersonResponseDto;
import ru.maxow.person.service.PersonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
  private final PersonService personService;

  @GetMapping
  public List<PersonResponseDto> findAll() {
    return personService.findAll();
  }

  @GetMapping("/{id}")
  public PersonResponseDto findById(@PathVariable Long id) {
    return personService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonResponseDto save(@RequestBody PersonCreateDto person) {
    return personService.save(person);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id) {
    personService.deleteById(id);
  }
}
