package com.store.enums;

public enum CustomerTypeEnum {
	A("A"),
	B("B");
	
	private String custType;
	
	private CustomerTypeEnum(String custType){
		this.custType = custType;
	}
	
	public String getCustType(){
		return custType;
	}
}
