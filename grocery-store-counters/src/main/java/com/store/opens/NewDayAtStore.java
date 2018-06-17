package com.store.opens;

import com.store.exception.GenericStoreException;
import com.store.managers.impl.StoreSupervisor;

public class NewDayAtStore {

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String... args) throws GenericStoreException{

		String fileName = "";
		
		try{
			fileName = args[0];
		} catch (Exception e){
			throw new GenericStoreException(e);
		}
		
		try {
		new StoreSupervisor(fileName)
			.openStore()
			.runStore()
			.closeStore();
		} catch (GenericStoreException ex){
			throw ex;
		}
	}

}
