package com.product.exception;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends{
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ExceptionResponse> handleApiException(ApiException exception, WebRequest request){
      ExceptionResponse response = new ExceptionResponse();
      response.setTimesStamp(LocalDateTime.now());
      response.setStatus(exception.getStatus().value());
      response.setError(exception.getStatus());
      response.setMessage(exception.getMessage());
      responser.setPath(((ServletWebRequest) request).getRequest().getRequestURI().toString());
      return new ResponseEntity(response, response.getError())
    }
}
