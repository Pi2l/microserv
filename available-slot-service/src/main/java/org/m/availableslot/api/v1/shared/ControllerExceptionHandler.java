package org.m.availableslot.api.v1.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler( ItemNotFoundException.class )
  @ResponseStatus( HttpStatus.NOT_FOUND )
  Map<String, String> handler(RuntimeException exception) {
    return Map.of( "cause", exception.getMessage() );
  }

  @ExceptionHandler( IllegalArgumentException.class )
  @ResponseStatus( HttpStatus.BAD_REQUEST )
  Map<String, String> handler(IllegalArgumentException exception) {
    return Map.of( "cause", exception.getMessage() );
  }
}
