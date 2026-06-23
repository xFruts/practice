package ru.maxow.person.mapper;

import org.mapstruct.Mapper;
import ru.maxow.person.entity.Person;
import ru.maxow.person.dto.PersonCreateDto;
import ru.maxow.person.dto.PersonResponseDto;

@Mapper(componentModel = "spring")
public interface PersonMapper {
  Person toEntity(PersonCreateDto personCreateDto);
  PersonResponseDto toDto(Person person);
}
