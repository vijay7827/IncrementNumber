package com.galacus.test.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 2457189159617736175L;
	
	private final HttpStatus status;
    private final String message;
    
	public CustomException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
    
    
 
}
