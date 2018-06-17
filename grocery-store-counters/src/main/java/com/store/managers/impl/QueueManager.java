package com.store.managers.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.store.entities.IAssignedCashier;
import com.store.entities.ICustomerEntity;
import com.store.managers.IQueueManager;
import com.store.queue.SingletonQueueCreator;

public class QueueManager implements IQueueManager {

	private boolean firstCustomerArrived = false;
	
	/**
	 * 
	 * @param activeRegisterCount
	 */
	public QueueManager(){
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isAllCheckoutsDone(){
		return (SingletonQueueCreator.getAvailableCashiers().stream().allMatch(registerCounter -> registerCounter.getQueueLength() == 0));
	}

	/**
	 * 
	 */
	@Override
	public void checkoutItemsFromQueues() {
		SingletonQueueCreator.getAvailableCashiers()
		.forEach(registerCounter -> registerCounter.cashierBillsAnItem());
	}

	/**
	 * 
	 */
	@Override
	public int getremainintItemsForCustomer() {
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public void removeCustomerFromQueue(ICustomerEntity cust) {
		List<IAssignedCashier> queueWithZeroItems = SingletonQueueCreator.getAvailableCashiers()
			.stream()
			.filter(cashier -> cashier.isRegWithZeroItemsCust())
			.collect(Collectors.toList());
		queueWithZeroItems.stream().forEach(cashier -> cashier.customerLeavesQueue(cust));
		
	}
}
