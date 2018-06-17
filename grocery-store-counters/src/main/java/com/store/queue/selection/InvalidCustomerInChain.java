package com.store.queue.selection;

import com.store.entities.ICustomerEntity;

public class InvalidCustomerInChain implements QueueSelectionChain {

	@Override
	public void selectQueue(ICustomerEntity cust) {
		System.out.println("The customer type '" + cust.getCustomerType() + "' sent for selection at time 'T" + cust.getTimeArrived() + "' did not match any criteria or was not as per the requirements of the system.");
	}

	@Override
	public void tryNextAssignment(QueueSelectionChain tryNextSelection) {
		// TODO Auto-generated method stub

	}

}
