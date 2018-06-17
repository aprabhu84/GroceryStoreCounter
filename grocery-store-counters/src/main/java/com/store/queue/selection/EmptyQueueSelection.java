package com.store.queue.selection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.store.entities.IAssignedCashier;
import com.store.entities.ICustomerEntity;
import com.store.queue.SingletonQueueCreator;

public class EmptyQueueSelection implements QueueSelectionChain {

	private QueueSelectionChain tryNextSelection;
	private List<IAssignedCashier> availableCashiers;
	
	/**
	 * 
	 */
	public EmptyQueueSelection() {
		availableCashiers = SingletonQueueCreator.getAvailableCashiers();
		tryNextAssignment(new CustTypeAQueueSelection());
	}
	
	/**
	 * 
	 */
	@Override
	public void selectQueue(ICustomerEntity cust) {
		if (availableCashiers.stream().anyMatch(registerCounter -> registerCounter.getQueueLength() != 0)){
			tryNextSelection.selectQueue(cust);;
		} else {
			availableCashiers
			.stream()
			.filter(registerCounter -> registerCounter.getQueueLength() == 0)
			.sorted(Comparator.comparing(IAssignedCashier::getAssignedRegisterId))
			.collect(Collectors.toList())
			.get(0)
			.customerArrivesAtQueue(cust);
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
