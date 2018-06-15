package com.store.open;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.store.bo.StoreBO;
import com.store.cust.entities.Customer;
import com.store.cust.entities.ICustomer;

public class OpenStore {

	public static StoreBO openStoreForTheDay(String fileName) throws IOException, Exception {
		try {
			
			String cust;
			List<ICustomer> customerInStore = new ArrayList<ICustomer>();

			BufferedReader inputReader = new BufferedReader(new FileReader(fileName));

			int counters = Integer.parseInt(inputReader.readLine());
			while ((cust = inputReader.readLine()) != null) {
				customerInStore.add(new Customer(cust));
			}

			inputReader.close();
			return new StoreBO(counters, customerInStore);
			
		} catch (IOException e) {
			throw e;
		}
	}

}
