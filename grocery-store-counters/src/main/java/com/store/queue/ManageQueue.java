package com.store.queue;

import java.util.List;

import com.store.cust.entities.ICustomer;
import com.store.queue.entity.IRegisterCounter;
import com.store.queue.selectorschain.EmptyQueueSelection;
import com.store.queue.selectorschain.QueueSelectionChain;

public class ManageQueue {

	private List<IRegisterCounter> availableRegisters;
	private QueueSelectionChain selectQueueForCust;
	private boolean firstCustomerArrived = false;
	
	/**
	 * 
	 * @param activeRegisterCount
	 */
	public ManageQueue(int activeRegisterCount){
		SingletonQueueCreator.getInstance(activeRegisterCount);
		availableRegisters = SingletonQueueCreator.getAvailableRegisters();
		selectQueueForCust = new EmptyQueueSelection();
	}
	
	/**
	 * 
	 * @param cust
	 */
	public void letCustomerChooseQueue(ICustomer cust){
		firstCustomerArrived = true;
		selectQueueForCust.selectQueue(cust);	
	}
	
	/**
	 * 
	 */
	public void proceedCheckout(){
		availableRegisters
			.forEach(registerCounter -> registerCounter.checkoutItems());
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isAllCheckoutsDone(){
		return (availableRegisters.stream().allMatch(registerCounter -> registerCounter.getQueueLength() == 0) && firstCustomerArrived);
	}
}
