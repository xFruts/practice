package ru.maxow.common.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
  private final String entityName;
  private final String identifier;

  @Serial
  private static final long serialVersionUID = 1L;

  public NotFoundException(String message) {
    super(message);
    this.entityName = null;
    this.identifier = null;
  }

  public NotFoundException(Class<?> entityClass, Object identifier) {
    super(String.format("%s with identifier [%s] not found", entityClass.getSimpleName(), identifier));
    this.entityName = entityClass.getSimpleName();
    this.identifier = String.valueOf(identifier);
  }
}
