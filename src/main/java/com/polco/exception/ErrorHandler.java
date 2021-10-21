package com.polco.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends Exception {

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity handleMissingParams(MissingServletRequestParameterException ex) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error", ex.getParameterName() + " parameter is missing");
		return ResponseEntity.badRequest().body(map);
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity handleMissingParams(Exception ex) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("error", ex.getMessage() + " parameter is missing");
//		return ResponseEntity.badRequest().body(map);
//	}	
}
