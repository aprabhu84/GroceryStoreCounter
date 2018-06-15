package com.store.queue;

import java.util.ArrayList;
import java.util.List;

import com.store.enums.CashierTypeEnum;
import com.store.queue.entity.IRegisterCounter;
import com.store.queue.entity.RegisterCounter;

public class SingletonQueueCreator {

	private static SingletonQueueCreator queueCreator = null;
	
	private static List<IRegisterCounter> availableRegisters = null;
	
	/**
	 * 
	 * @param counters
	 */
	private SingletonQueueCreator(int counters){
		setupRegisterCounters(counters);
	}
	
	/**
	 * 
	 * @param counters
	 * @return
	 */
	public static SingletonQueueCreator getInstance(int counters){
		if(queueCreator == null){
			queueCreator = new SingletonQueueCreator(counters);
		}
		return queueCreator;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<IRegisterCounter> getAvailableRegisters(){
		return availableRegisters;
	}
	
	/**
	 * 
	 * @param counters
	 */
	private static void setupRegisterCounters(int counters){
		availableRegisters = new ArrayList<IRegisterCounter>();
		for (int i = 1; i<counters; i++){
			availableRegisters.add(new RegisterCounter(i, CashierTypeEnum.CASHIER));
		}
		availableRegisters.add(new RegisterCounter(counters, CashierTypeEnum.TRAINEE));
	}
	
}
