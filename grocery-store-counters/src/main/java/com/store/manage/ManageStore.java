package com.store.manage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.stores.bo.StoreBO;
import com.stores.entities.CashierTypeEnum;
import com.stores.entities.ICustomer;
import com.stores.entities.RegisterCounter;

public class ManageStore {

	private List<ICustomer> customersInStore;
	private List<RegisterCounter> availableRegisters;
	
	public ManageStore(StoreBO storeBO){
		this.customersInStore = storeBO.getCustomersInStore();
		setupRegisterCounters(storeBO.getCounters());
	}
	
	
	private void setupRegisterCounters(int counters){
		availableRegisters = new ArrayList<RegisterCounter>();
		for (int i = 1; i<counters; i++){
			availableRegisters.add(new RegisterCounter(i, CashierTypeEnum.CASHIER));
		}
		availableRegisters.add(new RegisterCounter(counters, CashierTypeEnum.TRAINEE));
	}
	
	
	public List<ICustomer> checkReadyCustomers(int atTimeT){
		System.out.println("Getting Ready Customers at time - " + atTimeT);
		List<ICustomer> readyCustomers = new ArrayList<ICustomer>();
		customersInStore.parallelStream().forEach(cust -> {
			if (cust.getTimeArrived() == atTimeT)
				readyCustomers.add(cust);
		});
		return readyCustomers.stream()
				.sorted(Comparator.comparing(ICustomer::getCustomerType)
				.thenComparing(ICustomer::getItemsInHand))
				.collect(Collectors.toList());
	}
	
	
	public void letCustomersChooseQueue(List<ICustomer> sortedReadyCustomers){
		sortedReadyCustomers.forEach(cust -> System.out.println(cust.getCustomerType() + " " + cust.getTimeArrived() + " " + cust.getItemsInHand()));
	}
	
	
	
}
