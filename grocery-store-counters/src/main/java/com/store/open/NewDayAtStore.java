package com.store.open;

import java.util.List;

import com.store.bo.StoreReadyBO;
import com.store.cust.ManageCustomers;
import com.store.cust.entities.ICustomer;
import com.store.queue.ManageQueue;
import com.store.ready.OpenStore;

public class NewDayAtStore {

	public static void main(String... args) throws Exception {

		String fileName = args[0];
		StoreReadyBO storeBO = OpenStore.openStoreForTheDay(fileName);
		ManageCustomers storeManager = new ManageCustomers(storeBO.getCustomersInStore());
		ManageQueue availableRegisters = new ManageQueue(storeBO.getCounters());

		int i = 0;

		while (true) {
			// for (int j=0; j < 10; j++){
			i++;
			System.out.println("T" + i + " ----- ");
			availableRegisters.proceedCheckout();
			List<ICustomer> readyCust = storeManager.checkReadyCustomers(i);
			readyCust.stream().forEachOrdered(customer -> {
				System.out.println( "Customer " + customer.getCustomerType()+customer.getTimeArrived() + " Arrives with " + customer.getItemsInHand() + " Items");
				availableRegisters.letCustomerChooseQueue(customer);
			});
			if (availableRegisters.isAllCheckoutsDone()) {
				break;
			}

		}

		System.out.println("Time taken is - " + i);

	}

}
