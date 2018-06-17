package com.store.object;

import java.util.List;

import com.store.entities.ICustomerEntity;

public class StoreReadyBO {
	private int counters;
	private List<ICustomerEntity> customersInStore;
	
	/**
	 * 
	 * @param counters
	 * @param customerInStore
	 */
	public StoreReadyBO(int counters, List<ICustomerEntity> customerInStore){
		this.counters = counters;
		this.customersInStore = customerInStore;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCounters(){
		return counters;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ICustomerEntity> getCustomersInStore(){
		return customersInStore;
	}
	
}
