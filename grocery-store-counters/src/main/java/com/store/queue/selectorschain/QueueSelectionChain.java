package com.store.queue.selectorschain;

import com.store.cust.entities.ICustomer;

public interface QueueSelectionChain {

	public void selectQueue(ICustomer cust);
	public void tryNextAssignment(QueueSelectionChain tryNextSelection);
	
}
