package ru.maxow.person.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.maxow.person.dto.PersonCreateDto;
import ru.maxow.person.dto.PersonResponseDto;
import ru.maxow.person.model.Weather;
import ru.maxow.person.service.PersonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private final RestTemplate restTemplate;

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

    @PutMapping("/{id}")
    public PersonResponseDto update(@PathVariable Long id, @RequestBody PersonCreateDto person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        personService.deleteById(id);
    }

    @GetMapping("/{id}/weather")
    public ResponseEntity<Weather> getWeather(@PathVariable Long id) {
        try {
            PersonResponseDto person = personService.findById(id);
            if (person.location() != null) {
                Weather weather = restTemplate.getForObject(
                        "http://location/location/weather?name=" + person.location(),
                        Weather.class
                );
                return ResponseEntity.ok(weather);
            }
        } catch (Exception e) {
            // Person not found
        }
        return ResponseEntity.notFound().build();
    }
}
