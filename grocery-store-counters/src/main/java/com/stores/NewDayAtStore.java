package com.stores;

import java.util.List;

import com.store.manager.ManageRegisters;
import com.store.manager.ManageStore;
import com.stores.bo.StoreBO;
import com.stores.entities.ICustomer;
import com.stores.open.OpenStore;

public class NewDayAtStore {

	public static void main(String... args) throws Exception {

		String fileName = args[0];
		StoreBO storeBO = OpenStore.openStoreForTheDay(fileName);
		ManageStore storeManager = new ManageStore(storeBO.getCustomersInStore());
		ManageRegisters availableRegisters = new ManageRegisters(storeBO.getCounters());

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
