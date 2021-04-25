package ru.sin666.tagcounter.domain.controller.advices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandleControllerAdvice {

  @ExceptionHandler(value
      = {IllegalArgumentException.class})
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
}
