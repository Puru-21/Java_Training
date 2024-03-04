package com.training.project.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	
	 @ExceptionHandler(BindException.class) 
	 public ResponseEntity<List<String>> handleBindException(BindException ex) { 
		List<String> errors = ex.getBindingResult() .getFieldErrors() .stream() .map(fieldError ->
	 fieldError.getDefaultMessage()) .collect(Collectors.toList());
	 return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); }
	 
}