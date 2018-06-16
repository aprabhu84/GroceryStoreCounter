package com.store.queue.selectorschain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.store.cust.entities.ICustomer;
import com.store.queue.SingletonQueueCreator;
import com.store.queue.entity.IRegisterCounter;

public class EmptyQueueSelection implements QueueSelectionChain {

	private QueueSelectionChain tryNextSelection;
	private List<IRegisterCounter> availableRegisters;
	
	/**
	 * 
	 */
	public EmptyQueueSelection() {
		availableRegisters = SingletonQueueCreator.getAvailableRegisters();
		tryNextAssignment(new CustTypeAQueueSelection());
	}
	
	/**
	 * 
	 */
	@Override
	public void selectQueue(ICustomer cust) {
		if (availableRegisters.stream().anyMatch(registerCounter -> registerCounter.getQueueLength() != 0)){
			tryNextSelection.selectQueue(cust);;
		} else {
			availableRegisters
			.stream()
			.filter(registerCounter -> registerCounter.getQueueLength() == 0)
			.sorted(Comparator.comparing(IRegisterCounter::getAssignedRegId))
			.collect(Collectors.toList())
			.get(0)
			.assignCustomerToQueue(cust);
		}
	}

	/**
	 * 
	 */
	@Override
	public void tryNextAssignment(QueueSelectionChain tryNextSelection) {
		this.tryNextSelection = tryNextSelection;
	}

}
