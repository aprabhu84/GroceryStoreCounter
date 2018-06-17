package com.store.managers;

import com.store.exception.GenericStoreException;

public interface IStoreSupervisor {

	public IStoreSupervisor openStore() throws GenericStoreException;
	public IStoreSupervisor runStore();
	public void closeStore();
	
}
