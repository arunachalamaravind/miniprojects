package com.example.bookingsystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.bookingsystem.exception.InvalidException;
import com.example.bookingsystem.response.Response;

@ControllerAdvice
public class ExceptionController {
	
	@Autowired
	private Response response;
	
	@ExceptionHandler(InvalidException.class)
	public ResponseEntity<Response> excepHandler(InvalidException employeeException) {
		response.setMessage(employeeException.getMessage());
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND); 	
	}

}
