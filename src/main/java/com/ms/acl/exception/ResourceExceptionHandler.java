package com.ms.acl.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<ApiError> handleNotFoundException(RegraNegocioException ex) {
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiError> handleMissingParams(MissingServletRequestParameterException ex) {
	    String name = ex.getParameterName();
	    
	    ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Parâmmetro " + name + " obrigatório", new Date());
	    
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex) {
		ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
		ApiError error = new ApiError(HttpStatus.FORBIDDEN.value(), ex.getMessage(), new Date());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	

}
