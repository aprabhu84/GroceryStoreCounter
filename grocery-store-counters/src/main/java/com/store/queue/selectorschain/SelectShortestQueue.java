package com.store.queue.selectorschain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.store.cust.entities.ICustomer;
import com.store.enums.CustomerTypeEnum;
import com.store.queue.SingletonQueueCreator;
import com.store.queue.entity.IRegisterCounter;

public class SelectShortestQueue implements SelectQueueChain{

	private SelectQueueChain tryNextSelection;
	private List<IRegisterCounter> availableRegisters;
	
	/**
	 * 
	 */
	public SelectShortestQueue() {
		availableRegisters = SingletonQueueCreator.getAvailableRegisters();
		tryNextAssignment(new SelectLastLeastItems());
	}
	
	/**
	 * 
	 * @param cust
	 */
	@Override
	public void selectQueue(ICustomer cust) {
		if(cust.getCustomerType().equals(CustomerTypeEnum.A.getCustType())){
			availableRegisters
			.stream()
			.sorted(Comparator.comparing(IRegisterCounter::getQueueLength)
					.thenComparing(IRegisterCounter::getAssignedRegId))
			.collect(Collectors.toList())
			.get(0)
			.assignCustomerToQueue(cust);
		} else {
			tryNextSelection.selectQueue(cust);
		}
		
	}

	/**
	 * 
	 * @param tryNextSelection
	 */
	@Override
	public void tryNextAssignment(SelectQueueChain tryNextSelection) {
		this.tryNextSelection = tryNextSelection;
	}

	
}
