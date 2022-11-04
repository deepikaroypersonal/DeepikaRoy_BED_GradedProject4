package com.greatlearning.employees.exception;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestControllerAdvice
@Component
public class GlobalException 
{
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleInvalidOrderId(IllegalArgumentException ex)
	{
		return new Error(ex.getMessage());
	}
}

@Getter
@AllArgsConstructor
class Error
{
	private String message;
}