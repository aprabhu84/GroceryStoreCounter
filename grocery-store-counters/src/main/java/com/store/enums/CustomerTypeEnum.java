package com.store.enums;

public enum CustomerTypeEnum {
	A("A"),
	B("B"),
	INVALID("INVALID");
	
	private String custType;
	
	/**
	 * 
	 * @param custType
	 */
	private CustomerTypeEnum(String custType){
		this.custType = custType;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCustType(){
		return custType;
	}
	
	/**
	 * 
	 * @param custType
	 * @return
	 */
	public static CustomerTypeEnum getCustomerTypeEnum(String custType){
		switch(custType){
		case "A": 
			return A;
		case "B":
			return B;
		default :
			return INVALID;	
		}
	}
}
