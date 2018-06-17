package com.store.queue.selection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.store.entities.IAssignedCashier;
import com.store.entities.ICustomerEntity;
import com.store.entities.IRegisterEntity;
import com.store.enums.CustomerTypeEnum;
import com.store.queue.SingletonQueueCreator;

public class CustTypeAQueueSelection implements QueueSelectionChain{

	private QueueSelectionChain tryNextSelection;
	private List<IAssignedCashier> availableCashiers;
	
	/**
	 * 
	 */
	public CustTypeAQueueSelection() {
		availableCashiers = SingletonQueueCreator.getAvailableCashiers();
		tryNextAssignment(new CustTypeBQueueSelection());
	}
	
	/**
	 * 
	 * @param cust
	 */
	@Override
	public void selectQueue(ICustomerEntity cust) {
		if(cust.getCustomerType().equals(CustomerTypeEnum.A)){
			availableCashiers
			.stream()
			.sorted(Comparator.comparing(IAssignedCashier::getQueueLength)
					.thenComparing(IAssignedCashier::getAssignedRegisterId))
			.collect(Collectors.toList())
			.get(0)
			.customerArrivesAtQueue(cust);
		} else {
			tryNextSelection.selectQueue(cust);
		}
		
	}

	/**
	 * 
	 * @param tryNextSelection
	 */
	@Override
	public void tryNextAssignment(QueueSelectionChain tryNextSelection) {
		this.tryNextSelection = tryNextSelection;
	}

	
}
