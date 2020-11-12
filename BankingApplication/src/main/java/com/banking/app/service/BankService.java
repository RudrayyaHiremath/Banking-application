package com.banking.app.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import com.banking.app.dto.FundTransferRequestDto;
import com.banking.app.dto.FundTransferResponseDto;


public interface BankService {
	String fundTransfer(FundTransferRequestDto fundTransferRequestDto);

	List<FundTransferResponseDto> getTransactionStatement(Long accountNumber, Integer pageNo, Integer pageSize) ;
}
