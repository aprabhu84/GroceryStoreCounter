package com.store.logger;

import com.store.entities.IAssignedCashier;
import com.store.entities.ICustomerEntity;
import com.store.entities.IRegisterEntity;

public interface StoreEventLogger {

	static boolean logEnabled = true;
	
	static void logCustomerMessage(ICustomerEntity cust, String message){
		if (logEnabled){
			System.out.println(message + " - : - " + cust.getCustomerType() + " " + cust.getTimeArrived()
			+ " " + cust.getItemsInHand());
		}
	}
	
	static void logRegisterCounterMessage(IAssignedCashier regCount, String message){
		if (logEnabled){
			System.out.println(message + " - : - " + regCount.getAssignedRegisterId() + " is with " + regCount.getQueueLength()
			+ " Customer and " + regCount.getItemCountFromLastCustomer() + " Items with Last Customer");
		}
	}
	
	
	public static void logMessage(Object obj, String message){
		if (obj instanceof ICustomerEntity){
			logCustomerMessage ((ICustomerEntity)obj, message);
		} else if (obj instanceof IRegisterEntity){
			logRegisterCounterMessage((IAssignedCashier)obj, message);
		} else {
			System.out.println(message);
		}
	}
	
	public static void logMessage(String message){
		System.out.println(message);
	}
	
}
