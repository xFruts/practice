package ru.maxow.person.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maxow.common.util.NotFoundException;
import ru.maxow.person.mapper.PersonMapper;
import ru.maxow.person.repository.PersonRepository;
import ru.maxow.person.entity.Person;
import ru.maxow.person.dto.PersonResponseDto;
import ru.maxow.person.dto.PersonCreateDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  public List<PersonResponseDto> findAll() {
    return personRepository.findAll().stream()
        .map(personMapper::toDto)
        .toList();
  }

  public PersonResponseDto findById(Long id) {
    return personRepository.findById(id)
        .map(personMapper::toDto)
        .orElseThrow(() -> new NotFoundException(Person.class, id));
  }

  public PersonResponseDto save(PersonCreateDto personCreateDto) {
    Person savedPerson = personRepository.save(personMapper.toEntity(personCreateDto));
    return personMapper.toDto(savedPerson);
  }

  public PersonResponseDto update(Long id, PersonCreateDto personCreateDto) {
    Person person = personRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(Person.class, id));
    
    person.setFirstname(personCreateDto.firstname());
    person.setSurname(personCreateDto.surname());
    person.setLastname(personCreateDto.lastname());
    person.setBirthday(personCreateDto.birthday());
    person.setLocation(personCreateDto.location());
    
    Person updatedPerson = personRepository.save(person);
    return personMapper.toDto(updatedPerson);
  }

  public void deleteById(Long id) {
    personRepository.deleteById(id);
  }
}
