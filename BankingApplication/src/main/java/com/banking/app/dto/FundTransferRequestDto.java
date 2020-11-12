package com.banking.app.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FundTransferRequestDto
{
	@NotNull
	@Min(value=0, message="Enter valid  valid account number")
    @Digits(fraction = 0, integer = 13, message="account number not  accepted")
	@NotBlank(message="Please enter valid account number")
	private Long fromAccounNumber;
	
	@NotNull
	@Min(value=0, message="Enter valid  toAccount")
    @Digits(fraction = 0, integer = 13, message="account number not  accepted")
	@NotBlank(message="Please enter valid toAccount account number")
	private Long toAccount;

	private double amount;

	private String remarks;

	public Long getFromAccounNumber() {
		return fromAccounNumber;
	}

	public void setFromAccounNumber(Long fromAccounNumber) {
		this.fromAccounNumber = fromAccounNumber;
	}

	public Long getToAccount() {
		return toAccount;
	}

	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}