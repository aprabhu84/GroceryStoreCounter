package com.store.queue.selectorschain;

import java.util.Comparator;
import java.util.List;

import com.store.cust.entities.ICustomer;
import com.store.enums.CustomerTypeEnum;
import com.store.queue.SingletonQueueCreator;
import com.store.queue.entity.IRegisterCounter;

public class CustTypeBQueueSelection implements QueueSelectionChain {

	private QueueSelectionChain tryNextSelection;
	private List<IRegisterCounter> availableRegisters;

	/**
	 * 
	 */
	public CustTypeBQueueSelection() {
		availableRegisters = SingletonQueueCreator.getAvailableRegisters();
		tryNextAssignment(new InvalidCustomerInChain());
	}

	@Override
	public void selectQueue(ICustomer cust) {
		if (cust.getCustomerType().equals(CustomerTypeEnum.B)) {
			availableRegisters.stream()
					.sorted(Comparator.comparing(IRegisterCounter::getItemsWithLastCustomer)
							.thenComparing(IRegisterCounter::getAssignedRegId))
					.findFirst().get().assignCustomerToQueue(cust);
		} else {
			tryNextSelection.selectQueue(cust);
		}
	}

	@Override
	public void tryNextAssignment(QueueSelectionChain tryNextSelection) {
		this.tryNextSelection = tryNextSelection;
	}

}
