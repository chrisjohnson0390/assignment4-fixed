package com.meritamerica.assignment4;

    // This CDoffering class gets the term and interest rate by two getter methods
public class CDOffering {
	private int term;
	private double interestRate;
	
	static CDOffering readFromString(String cdOfferingDataString) {
		String[] data = cdOfferingDataString.split(",");
		int term = Integer.parseInt(data[0]);
		double interestRate = Double.parseDouble(data[1]);
		
		return new CDOffering(term, interestRate);
	}
	
	// constructor and parameters
	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public int getTerm() {
		return term;
	}
	
}
