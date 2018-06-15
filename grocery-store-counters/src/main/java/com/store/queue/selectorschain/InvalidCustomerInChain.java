package com.store.queue.selectorschain;

import com.store.cust.entities.ICustomer;

public class InvalidCustomerInChain implements SelectQueueChain {

	@Override
	public void selectQueue(ICustomer cust) {
		System.out.println("The customer type '" + cust.getCustomerType() + "' sent for selection at time 'T" + cust.getTimeArrived() + "' did not match any criteria or was not as per the requirements of the system.");
	}

	@Override
	public void tryNextAssignment(SelectQueueChain tryNextSelection) {
		// TODO Auto-generated method stub

	}

}
