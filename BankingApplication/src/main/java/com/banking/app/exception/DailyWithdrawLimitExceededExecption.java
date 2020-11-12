package com.banking.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DailyWithdrawLimitExceededExecption extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DailyWithdrawLimitExceededExecption(String message) {
		super(message);
	}

	public DailyWithdrawLimitExceededExecption(String message, Throwable t) {
		super(message, t);
	}

}
