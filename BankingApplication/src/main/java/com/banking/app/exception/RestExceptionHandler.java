package com.banking.app.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler  extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value=AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException accountnotfoundexception){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorCode("301");
		errorResponse.setErrorMessage(accountnotfoundexception.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=InsufficientFundsException.class)
	public ResponseEntity<ErrorResponse> handleInsufficientFundsException(InsufficientFundsException insufficientfundsexception){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorCode("405");
		errorResponse.setErrorMessage(insufficientfundsexception.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value=DailyWithdrawLimitExceededExecption.class)
	public ResponseEntity<ErrorResponse> handleDailyWithdrawLimitExceededExecption(DailyWithdrawLimitExceededExecption dailywithdrawlimitexceededexecption){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorCode("408");
		errorResponse.setErrorMessage(dailywithdrawlimitexceededexecption.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
		
	}
	
	@Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException argInvalidException,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode("409");
        String allFieldErrors = argInvalidException.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
        response.setErrorMessage(allFieldErrors);
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
	

}
