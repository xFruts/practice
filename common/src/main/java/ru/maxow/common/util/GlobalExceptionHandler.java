package ru.maxow.common.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionHandler {

  private static final String CORRELATION_ID_KEY = "correlation-id";

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ErrorResponse handleNotFoundException(NotFoundException ex, WebRequest request) {
    log.info("Entity not found: entity={}, id={}, path={}",
        ex.getEntityName(),
        ex.getIdentifier(),
        extractPath(request));
    return new ErrorResponse(
        ex.getMessage(),
        System.currentTimeMillis(),
        "NOT_FOUND",
        getOrGenerateCorrelationId()
    );
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorResponse handleGlobalException(Exception ex, WebRequest request) {
    String correlationId = getOrGenerateCorrelationId();
    log.error("Unexpected error [correlationId={}]: path={}",
        correlationId, extractPath(request), ex);
    return new ErrorResponse(
        "An unexpected error occurred",
        System.currentTimeMillis(),
        "INTERNAL_ERROR",
        correlationId
    );
  }

  private String getOrGenerateCorrelationId() {
    String correlationId = MDC.get(CORRELATION_ID_KEY);
    if (correlationId == null || correlationId.isBlank()) {
      correlationId = java.util.UUID.randomUUID().toString();
      MDC.put(CORRELATION_ID_KEY, correlationId);
    }
    return correlationId;
  }

  private String extractPath(WebRequest request) {
    String description = request.getDescription(false); // uri=/path?query
    String path = description.startsWith("uri=") ? description.substring(4) : description;
    int queryIndex = path.indexOf('?');
    return queryIndex >= 0 ? path.substring(0, queryIndex) : path;
  }
}
