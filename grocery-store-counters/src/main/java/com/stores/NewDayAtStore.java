package com.stores;

import com.store.manage.ManageStore;
import com.stores.bo.StoreBO;
import com.stores.open.OpenStore;

public class NewDayAtStore {

	public static void main(String ...args) throws Exception{

		String fileName = args[0];
		StoreBO storeBO = OpenStore.openStoreForTheDay(fileName);
		ManageStore storeManager = new ManageStore(storeBO);
		
		int i = 0;
		
	//	while (true){
		for (int j=0; j < 10; j++){
			i++;
			storeManager.letCustomersChooseQueue(storeManager.checkReadyCustomers(i));
		}
		
	}
	
}
