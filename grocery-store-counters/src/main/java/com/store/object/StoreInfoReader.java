package com.store.object;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.store.entities.ICustomerEntity;
import com.store.entities.impl.CustomerEntity;
import com.store.exception.GenericStoreException;
import com.store.logger.StoreEventLogger;
import com.store.util.ValidationUtil;

public class StoreInfoReader {

	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws GenericStoreException
	 */
	public static StoreReadyBO openStoreForTheDay(String fileName) throws GenericStoreException{
		BufferedReader inputReader = null;
		
		try {
			
			String cust;
			List<ICustomerEntity> customerInStore = new ArrayList<ICustomerEntity>();
			
			inputReader = new BufferedReader(new FileReader(fileName));

			int counters = Integer.parseInt(inputReader.readLine());
			while ((cust = inputReader.readLine()) != null) {
				if(!ValidationUtil.isValidCustomer(cust))
					throw new GenericStoreException("The Format of the Input File is incorrect or the Customer in the file is Invalid");
				else if (ValidationUtil.isCustomerWithNoItems(cust))
					StoreEventLogger.logMessage(new CustomerEntity(cust), "Customer did not have any items to Checkout. so leaving the store.");
				else
					customerInStore.add(new CustomerEntity(cust));
			}


			return new StoreReadyBO(counters, customerInStore);
			
		} catch (IOException | GenericStoreException e) {
			throw new GenericStoreException("There was an issue with the File Read", e);
		} finally {
			if (inputReader != null)
				try{
					inputReader.close();
				} catch (IOException  e){
					throw new GenericStoreException("Reader Connection Closing had an issue", e);
				}
		}
	}

}
