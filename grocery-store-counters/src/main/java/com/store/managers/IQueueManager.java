package com.store.managers;

import com.store.entities.ICustomerEntity;

public interface IQueueManager {

	public void checkoutItemsFromQueues();
	public int getremainintItemsForCustomer();
	public void removeCustomerFromQueue(ICustomerEntity cust);
	
}
