package com.store.ready;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.store.bo.StoreReadyBO;
import com.store.cust.entities.Customer;
import com.store.cust.entities.ICustomer;

public class OpenStore {

	public static StoreReadyBO openStoreForTheDay(String fileName) throws IOException, Exception {
		try {
			
			String cust;
			List<ICustomer> customerInStore = new ArrayList<ICustomer>();

			BufferedReader inputReader = new BufferedReader(new FileReader(fileName));

			int counters = Integer.parseInt(inputReader.readLine());
			while ((cust = inputReader.readLine()) != null) {
				customerInStore.add(new Customer(cust));
			}

			inputReader.close();
			return new StoreReadyBO(counters, customerInStore);
			
		} catch (IOException e) {
			throw e;
		}
	}

}
