package com.store.managers;

import java.util.List;

import com.store.entities.ICustomerEntity;

public interface ICustomerManager {

	public void customersChooseQueue(int atTimeT);
	public void customersWaitFullCheckout();
	public void customersLeaveQueue();
	public boolean checkAllCustomersDoneBilling();
	
}
