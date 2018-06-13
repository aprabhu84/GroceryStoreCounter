package com.stores.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.stores.object.Customer;
import com.stores.object.ICustomer;

public class CounterFileReader {
	
	private int counters;
	private List<ICustomer> customerQueue;
	
	public CounterFileReader(String fileName) throws Exception{
		String cust;
		customerQueue = new ArrayList<ICustomer>();
		
		BufferedReader inputReader = new BufferedReader(new FileReader(fileName));
		
		this.counters = Integer.parseInt(inputReader.readLine());
		while ((cust = inputReader.readLine()) != null){
			customerQueue.add(new Customer(cust));
		}
	}
	
	
	public int getCounters(){
		return counters;
	}
	
	public ICustomer getNextCustomer(int currentTimeT){
		//Using Stream get the next customer in queue at time t
		return null;
	}
	
	public List<ICustomer> getCustomers(){
		return customerQueue;
	}
	
	
	public static void main(String ...args) throws Exception{
		CounterFileReader cfr = new CounterFileReader("/Users/aprabhu/Documents/workspace/GroceryStoreCounters/grocery-store-counters/src/main/resources/input/input.txt");
		System.out.println(cfr.getCounters());
		List<ICustomer> custList = cfr.getCustomers();
		
	}
}
