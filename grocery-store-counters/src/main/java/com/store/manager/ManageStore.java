package com.store.manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.stores.entities.ICustomer;

public class ManageStore {

	private List<ICustomer> customersInStore;
	
	public ManageStore(List<ICustomer> customersInStore){
		this.customersInStore = customersInStore;
	}
	
	
	public List<ICustomer> checkReadyCustomers(int atTimeT){
		List<ICustomer> readyCustomers = new ArrayList<ICustomer>();
		customersInStore.parallelStream().forEach(cust -> {
			if (cust.getTimeArrived() == atTimeT)
				readyCustomers.add(cust);
		});
		return readyCustomers.stream()
				.sorted(Comparator.comparing(ICustomer::getItemsInHand)
				.thenComparing(ICustomer::getCustomerType))
				.collect(Collectors.toList());
	}
	
	
	
	
	
}
