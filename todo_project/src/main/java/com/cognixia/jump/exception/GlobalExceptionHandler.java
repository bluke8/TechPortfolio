package com.cognixia.jump.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<?> resourceNotFound(ResourceNotFoundException ex, WebRequest request) {
		
		// what data will be returned back in response when exception thrown
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		// constructing the response
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
