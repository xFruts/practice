package ru.maxow.practicespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxow.practicespring.dto.Message;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
  Optional<Message> findByIdAndPerson_Id(int messageId, int personId);
}
