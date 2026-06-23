package ru.maxow.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
  private String message;
  private Long timestamp;
  private String errorCode;
  private String correlationId;
}
