package ru.maxow.practicespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxow.practicespring.dto.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
