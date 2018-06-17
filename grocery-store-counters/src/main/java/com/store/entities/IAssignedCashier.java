package com.store.entities;

public interface IAssignedCashier {

	public void customerArrivesAtQueue(ICustomerEntity cust);
	public void cashierBillsAnItem();
	public void customerLeavesQueue(ICustomerEntity cust);
	public int getItemCountFromLastCustomer();
	public int getQueueLength();
	public int getAssignedRegisterId();
	public boolean isRegWithZeroItemsCust();
	
}
