package com.store.queue.entity;

import com.store.cust.entities.ICustomer;

public interface IRegisterCounter extends Comparable<IRegisterCounter>  {

	public int getAssignedRegId();
	public int getQueueLength();
	public void assignCustomerToQueue(ICustomer cust);
	public int getItemsWithLastCustomer();
	public void checkoutItems();
	
}
