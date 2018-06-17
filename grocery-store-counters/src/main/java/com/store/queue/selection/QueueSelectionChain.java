package com.store.queue.selection;

import com.store.entities.ICustomerEntity;

public interface QueueSelectionChain {

	public void selectQueue(ICustomerEntity cust);
	public void tryNextAssignment(QueueSelectionChain tryNextSelection);
	
}
