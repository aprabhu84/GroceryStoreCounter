package com.store.manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.stores.entities.CashierTypeEnum;
import com.stores.entities.ICustomer;
import com.stores.entities.RegisterCounter;

public class ManageRegisters {

	private List<RegisterCounter> availableRegisters;
	private boolean firstCustomerArrived = false;
	
	public ManageRegisters(int activeRegisterCount){
		setupRegisterCounters(activeRegisterCount);
	}
	
	public void letCustomerChooseQueue(ICustomer cust){
		firstCustomerArrived  =true;
		RegisterCounter registerCounter = getRegisterWithLeastCustomers();
		
		if (registerCounter.getQueueLength() == 0){
			registerCounter.assignCustomerToQueue(cust);
		} else {
			if (cust.getCustomerType().equals("A")){
				registerCounter.assignCustomerToQueue(cust);
			} else {
				getRegisterWithLeastLastItems().assignCustomerToQueue(cust);
			}
		}
	}
	
	public void proceedCheckout(){
		
		availableRegisters
			.forEach(registerCounter -> registerCounter.checkoutItems());
	}
	
	public boolean isAllCheckoutsDone(){
		return (availableRegisters.stream().allMatch(registerCounter -> registerCounter.getQueueLength() == 0) && firstCustomerArrived);
	}
	
		
	private void setupRegisterCounters(int counters){
		availableRegisters = new ArrayList<RegisterCounter>();
		for (int i = 1; i<counters; i++){
			availableRegisters.add(new RegisterCounter(i, CashierTypeEnum.CASHIER));
		}
		availableRegisters.add(new RegisterCounter(counters, CashierTypeEnum.TRAINEE));
	}

	private RegisterCounter getRegisterWithLeastCustomers(){
		return availableRegisters
				.stream()
				.sorted(Comparator.comparing(RegisterCounter::getQueueLength)
						.thenComparing(RegisterCounter::getAssignedRegId))
				.collect(Collectors.toList())
				.get(0);
	}
	
	private RegisterCounter getRegisterWithLeastLastItems(){
		return availableRegisters
				.stream()
				.sorted(Comparator.comparing(RegisterCounter::getItemsWithLastCustomer)
						.thenComparing(RegisterCounter::getAssignedRegId))
				.findFirst()
				.get();
	}
	
	
	
	
}
