package com.store.managers;

import com.store.entities.ICustomerEntity;

public interface IQueueManager {

	public boolean isAllCheckoutsDone();
	public void checkoutItemsFromQueues();
	public void removeCustomerFromQueue(ICustomerEntity cust);
	
}
