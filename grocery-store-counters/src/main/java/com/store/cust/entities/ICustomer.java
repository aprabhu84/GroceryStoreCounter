package com.store.cust.entities;

import com.store.enums.CustomerTypeEnum;

public interface ICustomer extends Comparable<ICustomer> {
	
	public CustomerTypeEnum getCustomerType();
	public int getTimeArrived();
	public int getItemsInHand();
	public void billItem();
	
}
