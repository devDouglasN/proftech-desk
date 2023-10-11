package com.douglas.proftechdesk.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douglas.proftechdesk.services.exceptions.DataIntegrityViolationException;
import com.douglas.proftechdesk.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler({ObjectNotFoundException.class})
	  public ResponseEntity<StandardError> objectNotFoundExecption(ObjectNotFoundException ex, 
			  HttpServletRequest request) {
		
	    StandardError error = new StandardError(
	        System.currentTimeMillis(),
	        HttpStatus.NOT_FOUND.value(),
	        "Object Not Found",
	        ex.getMessage(),
	        request.getRequestURI()
	        );
	    
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	  }
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	  public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, 
			  HttpServletRequest request) {
		
	    StandardError error = new StandardError(
	        System.currentTimeMillis(),
	        HttpStatus.BAD_REQUEST.value(),
	        "Data breach",
	        ex.getMessage(),
	        request.getRequestURI()
	        );
	    
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	  }
}
