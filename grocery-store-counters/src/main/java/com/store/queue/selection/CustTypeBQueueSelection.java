package com.store.queue.selection;

import java.util.Comparator;
import java.util.List;

import com.store.entities.IAssignedCashier;
import com.store.entities.ICustomerEntity;
import com.store.entities.IRegisterEntity;
import com.store.enums.CustomerTypeEnum;
import com.store.queue.SingletonQueueCreator;

public class CustTypeBQueueSelection implements QueueSelectionChain {

	private QueueSelectionChain tryNextSelection;
	private List<IAssignedCashier> availableCashiers;

	/**
	 * 
	 */
	public CustTypeBQueueSelection() {
		availableCashiers = SingletonQueueCreator.getAvailableCashiers();
		tryNextAssignment(new InvalidCustomerInChain());
	}

	@Override
	public void selectQueue(ICustomerEntity cust) {
		if (cust.getCustomerType().equals(CustomerTypeEnum.B)) {
			availableCashiers.stream()
					.sorted(Comparator.comparing(IAssignedCashier::getItemCountFromLastCustomer)
							.thenComparing(IAssignedCashier::getAssignedRegisterId))
					.findFirst().get().customerArrivesAtQueue(cust);
		} else {
			tryNextSelection.selectQueue(cust);
		}
	}

	@Override
	public void tryNextAssignment(QueueSelectionChain tryNextSelection) {
		this.tryNextSelection = tryNextSelection;
	}

}
