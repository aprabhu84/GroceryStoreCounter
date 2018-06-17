package com.store.queue;

import java.util.ArrayList;
import java.util.List;

import com.store.entities.IAssignedCashier;
import com.store.entities.impl.AssignedCashier;
import com.store.enums.CashierTypeEnum;

public class SingletonQueueCreator {

	private static SingletonQueueCreator queueCreator = null;
	
	private static List<IAssignedCashier> availableCashiers = null;
	
	/**
	 * 
	 * @param counters
	 */
	private SingletonQueueCreator(int counters){
		setupCashiersAtRegisters(counters);
	}
	
	/**
	 * 
	 * @param counters
	 * @return
	 */
	public static SingletonQueueCreator createInstance(int counters){
		if(queueCreator == null){
			queueCreator = new SingletonQueueCreator(counters);
		}
		return queueCreator;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<IAssignedCashier> getAvailableCashiers(){
		return availableCashiers;
	}
	
	/**
	 * 
	 * @param counters
	 */
	private static void setupCashiersAtRegisters(int counters){
		availableCashiers = new ArrayList<IAssignedCashier>();
		for (int i = 1; i<counters; i++){
			availableCashiers.add(new AssignedCashier(i, CashierTypeEnum.CASHIER));
		}
		availableCashiers.add(new AssignedCashier(counters, CashierTypeEnum.TRAINEE));
	}
	
}
