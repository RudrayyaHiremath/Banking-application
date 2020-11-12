package com.banking.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.dto.FundTransferRequestDto;
import com.banking.app.dto.FundTransferResponseDto;
import com.banking.app.exception.AccountNotFoundException;
import com.banking.app.service.BankService;

@RestController
@RequestMapping("/bank")
@ControllerAdvice
public class BankController {

	@Autowired
	BankService bankService;

	@Autowired
	Environment enviroment;

	@PostMapping("/fundTransfer")
	@ExceptionHandler(value = AccountNotFoundException.class)
	public ResponseEntity<String> fundTransfer(@Valid @RequestBody FundTransferRequestDto fundTransferRequestDto) {
		bankService.fundTransfer(fundTransferRequestDto);
		return new ResponseEntity<String>("Fund Transfer Successfully", HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/transactionStatement")
	public ResponseEntity<List<FundTransferResponseDto>> getTransactions(
			@RequestParam("fromaccountNumber") Long fromaccountNumber, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		List<FundTransferResponseDto> fundTransfers = bankService.getTransactionStatement(fromaccountNumber, pageNo,
				pageSize);
		return new ResponseEntity<List<FundTransferResponseDto>>(fundTransfers, HttpStatus.OK);
	}

}
