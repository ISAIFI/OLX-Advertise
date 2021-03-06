package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidUserException extends RuntimeException {

	  private String message;
	  
	    
	    public InvalidUserException() {
	        super();
	        this.message = "";
	    }

	    public InvalidUserException(String message) {
	        super();
	        this.message = message;
	    }
	 
	    @Override
	    public String toString() {
	        return "InvalidUserException [message=" + message + "]"+ this.message;
	    }
}
