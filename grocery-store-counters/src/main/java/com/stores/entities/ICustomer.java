package com.stores.entities;

public interface ICustomer extends Comparable<ICustomer> {
	
	public String getCustomerType();
	public int getTimeArrived();
	public int getItemsInHand();
	public void billItem();
	
}
