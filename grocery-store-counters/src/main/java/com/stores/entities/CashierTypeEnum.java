package com.stores.entities;

public enum CashierTypeEnum {
	CASHIER (1),
	TRAINEE (2);
	
	private final int experties;
	
	private CashierTypeEnum (int experties){
		this.experties = experties;
	}
	
	public int getExperties(){
		return experties;
	}
}
