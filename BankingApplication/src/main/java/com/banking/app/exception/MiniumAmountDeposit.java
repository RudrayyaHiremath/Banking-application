package com.banking.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MiniumAmountDeposit extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public MiniumAmountDeposit(String message) {
		super(message);
	}

	public MiniumAmountDeposit(String message, Throwable t) {
		super(message, t);
	}


}
