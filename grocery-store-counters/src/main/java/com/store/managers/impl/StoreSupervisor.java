package com.store.managers.impl;

import com.store.exception.GenericStoreException;
import com.store.logger.StoreEventLogger;
import com.store.managers.ICustomerManager;
import com.store.managers.IStoreSupervisor;
import com.store.object.StoreInfoReader;
import com.store.object.StoreReadyBO;
import com.store.queue.SingletonQueueCreator;

public class StoreSupervisor implements IStoreSupervisor {

	String storeInfoFileName;
	int timeT = 0;

	ICustomerManager custManager;
	StoreReadyBO storeReadyBO;

	/**
	 * 
	 * @param storeInfoFileName
	 * @throws Exception
	 */
	public StoreSupervisor(String storeInfoFileName) throws GenericStoreException {
		this.storeInfoFileName = storeInfoFileName;

	}

	/**
	 * 
	 */
	@Override
	public IStoreSupervisor openStore() throws GenericStoreException {
		
		try {
			storeReadyBO = StoreInfoReader.openStoreForTheDay(storeInfoFileName);
		} catch (GenericStoreException e) {
			throw e;
		}
	
		SingletonQueueCreator.createInstance(storeReadyBO.getCounters());
		custManager = new CustomerManager(storeReadyBO.getCustomersInStore());
		return this;
	}

	/**
	 * 
	 */
	@Override
	public IStoreSupervisor runStore() {

		while (true) {
		//for (int j = 0; j < 15; j++) {
			timeT++;
			System.out.println("T" + timeT + " ----- ");
			custManager.customersWaitFullCheckout();
			custManager.customersLeaveQueue();
			custManager.customersChooseQueue(timeT);
			if (custManager.checkAllCustomersDoneBilling()) {
				break;
			}
		}

		return this;
	}

	/**
	 * 
	 */
	@Override
	public void closeStore() {
		StoreEventLogger.logMessage("Finished at: t=" + timeT + " minutes");
	}

}
