package com.store.bo;

import java.util.List;

import com.store.cust.entities.ICustomer;

public class StoreBO {
	private int counters;
	private List<ICustomer> customersInStore;
	
	/**
	 * 
	 * @param counters
	 * @param customerInStore
	 */
	public StoreBO(int counters, List<ICustomer> customerInStore){
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
	public List<ICustomer> getCustomersInStore(){
		return customersInStore;
	}
	
}
