package com.zshnb.ballplatform.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handler(Exception e, WebRequest webRequest) {
	    System.out.println(e.getMessage());
    	return handleExceptionInternal(e, Response.error(e.getMessage()),
		    HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, webRequest);
    }

}
