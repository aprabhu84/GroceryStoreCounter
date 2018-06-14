package com.stores.open;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.stores.entities.Customer;
import com.stores.entities.ICustomer;

public class CounterFileReader {
	
	private int counters;
	private List<ICustomer> customerQueue;
	
	/**
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public CounterFileReader(String fileName) throws Exception{
		String cust;
		customerQueue = new ArrayList<ICustomer>();
		
		BufferedReader inputReader = new BufferedReader(new FileReader(fileName));
		
		this.counters = Integer.parseInt(inputReader.readLine());
		while ((cust = inputReader.readLine()) != null){
			customerQueue.add(new Customer(cust));
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int getCounters(){
		return counters;
	}
	
	/**
	 * 
	 * @param currentTimeT
	 * @return
	 */
	public List<ICustomer> getNextCustomers(int currentTimeT){
		List<ICustomer> nextCust = new ArrayList<ICustomer>();
		customerQueue.parallelStream().forEachOrdered(cust -> {
			if (cust.getTimeArrived() == currentTimeT)
				nextCust.add(cust);
		});
		return nextCust.stream()
				.sorted(Comparator.comparing(ICustomer::getCustomerType).thenComparing(ICustomer::getItemsInHand))
				.collect(Collectors.toList());
		//return nextCust;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ICustomer> getCustomers(){
		return customerQueue;
	}
	
	
	public static void main(String ...args) throws Exception{
		CounterFileReader cfr = new CounterFileReader("C:/Users/prabhua/git/GroceryStoreRegister/grocery-store-counters/src/main/resources/input/input.txt");
		//System.out.println(cfr.getCounters());
		List<ICustomer> custList = 	cfr.getNextCustomers(2);
		custList.forEach(cust -> System.out.println(cust.getCustomerType() + " " + cust.getTimeArrived() + " " + cust.getItemsInHand()));
		
		/*List<ICustomer> newList = custList.stream().sorted(Comparator.comparing(ICustomer::getItemsInHand)).collect(Collectors.toList());
		newList.stream().forEach(cust -> System.out.println("@@@@ " + cust.getCustomerType() + " " + cust.getTimeArrived() + " " + cust.getItemsInHand()));
		
		Stream<ICustomer> str = custList.stream().filter(cust -> cust.getCustomerType().equals("A"));
		str.forEach(cust -> System.out.println("----- " + cust.getCustomerType() + " " + cust.getTimeArrived() + " " + cust.getItemsInHand()));
		
		//while (custList.size > 0){
		for (int i=0; i<10; i++){
			custList.forEach(cust -> System.out.println(cust.getCustomerType() + " " + cust.getTimeArrived() + " " + cust.getItemsInHand()));
			if(custList.stream().anyMatch(cust -> cust.getCustomerType().contains("A"))){
				System.out.println("Contains A");
				if(custList.stream().filter(cust -> cust.getCustomerType().equals("A")).count() > 1){
					
					System.out.println("With more than 1");
				}else{
					System.out.println("Has only 1");
				}
			}else{
				System.out.println("Contains B");
			}
		}*/

	}
}
