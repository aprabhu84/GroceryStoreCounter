package com.store.enums;

public enum CashierTypeEnum {
	CASHIER ("CASHIER",1),
	TRAINEE ("TRAINEE",2);
	
	private final String cashierType;
	private final int experties;
	
	
	private CashierTypeEnum (String cashierType, int experties){
		this.cashierType = cashierType;
		this.experties = experties;
	}
	
	public int getExperties(){
		return experties;
	}
	
	public String getCashierType(){
		return cashierType;
	}
}
