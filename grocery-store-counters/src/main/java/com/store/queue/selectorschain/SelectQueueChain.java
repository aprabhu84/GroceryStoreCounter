package com.store.queue.selectorschain;

import com.store.cust.entities.ICustomer;

public interface SelectQueueChain {

	public void selectQueue(ICustomer cust);
	public void tryNextAssignment(SelectQueueChain tryNextSelection);
	
}
