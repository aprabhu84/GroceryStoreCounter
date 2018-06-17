package com.store.entities;

import com.store.enums.CustomerTypeEnum;

public interface ICustomerEntity extends Comparable<ICustomerEntity> {
	
	public static final String REC_SEPARATOR = " ";
	
	public CustomerTypeEnum getCustomerType();
	public int getTimeArrived();
	public int getItemsInHand();
	public void billItem();
	
}
