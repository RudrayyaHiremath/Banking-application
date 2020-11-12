package com.banking.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.banking.app.dto.FundTransferRequestDto;
import com.banking.app.dto.FundTransferResponseDto;
import com.banking.app.entity.Account;
import com.banking.app.entity.FundTransfer;
import com.banking.app.repository.AccountInfoRepository;
import com.banking.app.repository.FundTransferRepository;
import com.banking.app.service.BankService;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	FundTransferRepository fundTransferRepository;

	@Autowired
	AccountInfoRepository accountInfoRepository;
	

	public String fundTransfer(FundTransferRequestDto fundTransferRequestDto) {
		long fromAccount = fundTransferRequestDto.getFromAccounNumber();
		long toAccount = fundTransferRequestDto.getToAccount();
		double transferFund = fundTransferRequestDto.getAmount();
		this.debitTransferFundFromAccount(fromAccount, transferFund); 
		this.creditTransferFundtoAccount(toAccount, transferFund);
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setFromAccountNumber(fundTransferRequestDto.getFromAccounNumber());
		fundTransfer.setToAccount(fundTransferRequestDto.getToAccount());
		fundTransfer.setAmount(fundTransferRequestDto.getAmount());
		fundTransfer.setTransfer_type("Debit");
		fundTransfer.setRemarks(fundTransferRequestDto.getRemarks());
		fundTransfer.setTransferDate("23-10-2020 13:40");
		fundTransferRepository.save(fundTransfer);

		return "Funds Transfer Successfully";

	}

	private void debitTransferFundFromAccount(Long fromAccountNumber, double transferFund) {
		Account fromAccount = new Account();
		fromAccount = accountInfoRepository.findByaccount_number(fromAccountNumber);
		double beforeCurrentBalance;
		double presentCurrentBalance;
		beforeCurrentBalance = fromAccount.getCurrentBalance();
		if (transferFund != 0) {
			presentCurrentBalance = (beforeCurrentBalance - transferFund);
			fromAccount.setCurrentBalance(presentCurrentBalance);

		}

		accountInfoRepository.save(fromAccount);

	}

	private void creditTransferFundtoAccount(Long toAccount, double transferFund) {
		Account creditAccount = new Account();
		creditAccount = accountInfoRepository.findByaccount_number(toAccount);
		double beforeCurrentBalance;
		double presentCurrentBalance;
		beforeCurrentBalance = creditAccount.getCurrentBalance();
		if (transferFund != 0) {
			presentCurrentBalance = (beforeCurrentBalance + transferFund);
			creditAccount.setCurrentBalance(presentCurrentBalance);

		}

		accountInfoRepository.save(creditAccount);

	}

	public List<FundTransferResponseDto> getTransactionStatement(Long accountNumber, Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("transferId").descending());
		List<FundTransfer> pagesResult = fundTransferRepository.findByfromAccountNumber(accountNumber, paging);
	    List<FundTransferResponseDto> fundTransferResponseDtoList = new ArrayList ();
	    for (FundTransfer fundTransfer: pagesResult   )
	    {
	    	FundTransferResponseDto fundTransferResponseDto = new FundTransferResponseDto ();
	    	BeanUtils.copyProperties(fundTransfer, fundTransferResponseDto);
	    	fundTransferResponseDtoList.add(fundTransferResponseDto);
	    	
	    }
		return fundTransferResponseDtoList;

	}
}
