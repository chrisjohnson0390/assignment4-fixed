package com.meritamerica.assignment4;

import java.util.Date;

public class WithdrawTransaction extends Transaction {

	
	
	WithdrawTransaction(BankAccount targetAccount, double amount, Date date) {
		super(targetAccount, amount, date);
	}
	
	
}
