package com.store.managers;

public interface ICustomerManager {

	public void customersChooseQueue(int atTimeT);
	public void customersWaitFullCheckout();
	public void customersLeaveQueue();
	public boolean checkAllCustomersDoneBilling();
	
}
