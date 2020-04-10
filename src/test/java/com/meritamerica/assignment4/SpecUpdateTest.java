package com.meritamerica.assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class SpecUpdateTest {

	@Test
	public void processTransactionTest() throws ExceedsCombinedBalanceLimitException, NegativeAmountException, ExceedsFraudSuspicionLimitException, ExceedsAvailableBalanceException {
		AccountHolder accountHolder = new AccountHolder(
            	"Sadiq",
            	"",
            	"Manji",
            	"123456789");
		
		accountHolder.addSavingsAccount(300);
    	
		accountHolder.addCheckingAccount(500);

		DepositTransaction dt1 = new DepositTransaction(accountHolder.getSavingsAccounts()[0], 50, new Date());
		DepositTransaction dt2 = new DepositTransaction(accountHolder.getSavingsAccounts()[0], 500, new Date());
		WithdrawTransaction wd1 = new WithdrawTransaction(accountHolder.getCheckingAccounts()[0], 150, new Date());
		
		
		MeritBank.processTransaction(dt1);
		MeritBank.processTransaction(dt2);
		
		MeritBank.processTransaction(wd1);
		
		BankAccount checking1 = accountHolder.getCheckingAccounts()[0];
		SavingsAccount sa1 = accountHolder.getSavingsAccounts()[0];
		
		ArrayList<Transaction> trans = sa1.getTransactions();
		
		Transaction tran = trans.get(1);	// index 1 because when create an account, there is already a deposit transaction
		Transaction tran2 = trans.get(2);
		
		// check some data of dt1, dt2, dt3
		 assertEquals(50, tran.getAmount(),0);
		 assertEquals(500, tran2.getAmount(),0);
		 
		 // check savingaccount balance after 2 deposits
		 assertEquals(850, tran.getTargetAccount().getBalance(), 0);

		 // checking checking account
		 Transaction withdrawTran = checking1.getTransactions().get(1);
		 
		 assertEquals(150, withdrawTran.getAmount(), 0);
		 assertEquals(350, withdrawTran.getTargetAccount().getBalance(), 0);
		 
		
		System.out.println(tran.toString());
		System.out.println(tran2);
		
		System.out.println(wd1.getTargetAccount().getBalance());
	}
	
	@Test
	public void transferTransactionTest() throws ExceedsCombinedBalanceLimitException, NegativeAmountException, ExceedsFraudSuspicionLimitException, ExceedsAvailableBalanceException {
		AccountHolder accountHolder = new AccountHolder(
            	"Sadiq",
            	"",
            	"Manji",
            	"123456789");
		
		accountHolder.addSavingsAccount(300);
    	
		accountHolder.addCheckingAccount(500);
		
		BankAccount acc1 = accountHolder.getSavingsAccounts()[0];
		BankAccount acc2 = accountHolder.getCheckingAccounts()[0];
		
		TransferTransaction tt1 = new TransferTransaction(acc1, acc2, 200, new Date());
		
		MeritBank.processTransaction(tt1);
		
		// balance of each account after the transaction
		assertEquals(100, acc1.getBalance(), 0);
		
		assertEquals(700, acc2.getBalance(), 0);
	}
	
	@Test
	public void extendArrayTest() throws ExceedsCombinedBalanceLimitException {
		AccountHolder accountHolder = new AccountHolder(
            	"Sadiq",
            	"",
            	"Manji",
            	"123456789");
		
		for (int i = 0; i < 10; i++) {
			accountHolder.addSavingsAccount(100 + i);
		}
		
		assertEquals(10, accountHolder.getSavingsAccounts().length, 0);
		
		// add one more saving account to account holder so the saving account array will be extended
		accountHolder.addSavingsAccount(200);

		assertEquals(20, accountHolder.getSavingsAccounts().length, 0);
	}
}
