package com.store.managers.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.store.entities.ICustomerEntity;
import com.store.managers.ICustomerManager;
import com.store.queue.selection.EmptyQueueSelection;
import com.store.queue.selection.QueueSelectionChain;

public class CustomerManager implements ICustomerManager{

	private List<ICustomerEntity> customersInStore;
	private QueueManager queueManager;
	private QueueSelectionChain selectQueueForCust;
	private boolean firstCustomerStratsBill;
	
	public CustomerManager(List<ICustomerEntity> customersInStore){
		this.customersInStore = customersInStore
				.stream()
				.sorted(Comparator.comparing(ICustomerEntity::getTimeArrived)
						.thenComparing(ICustomerEntity::getItemsInHand)
						.thenComparing(ICustomerEntity::getCustomerType))
				.collect(Collectors.toList());
		queueManager = new QueueManager();
		selectQueueForCust = new EmptyQueueSelection();
	}
	
	/**
	 * 
	 */
	@Override
	public void customersChooseQueue(int atTimeT) {
		customerReadyToCheckout(atTimeT).forEach(customer -> selectQueueForCust.selectQueue(customer));
	}

	/**
	 * 
	 */
	@Override
	public void customersWaitFullCheckout() {
		queueManager.checkoutItemsFromQueues();
	}

	/**
	 * 
	 */
	@Override
	public void customersLeaveQueue() {
		List<ICustomerEntity> zeroItemsCusts = new ArrayList<ICustomerEntity>();
		customersInStore
			.stream()
			.filter(cust -> cust.getItemsInHand() == 0)
			.forEach(cust -> {
				queueManager.removeCustomerFromQueue(cust);
				zeroItemsCusts.add(cust);
				});
		customersInStore.removeAll(zeroItemsCusts);
	}
	
	/**
	 * 
	 * @param atTimeT
	 * @return
	 */
	private List<ICustomerEntity> customerReadyToCheckout(int atTimeT){
		List<ICustomerEntity> readyCustomers = new ArrayList<ICustomerEntity>();
		customersInStore.forEach(cust -> {
			if (cust.getTimeArrived() == atTimeT)
				readyCustomers.add(cust);
		});
		if (!firstCustomerStratsBill){
			firstCustomerStratsBill = (readyCustomers.size() > 0)? true : false;
		}
		return readyCustomers;
	}

	/**
	 * 
	 */
	@Override
	public boolean checkAllCustomersDoneBilling() {
		return (queueManager.isAllCheckoutsDone() && customersInStore.stream().allMatch(cust -> cust.getItemsInHand() == 0));
		
	}
}
