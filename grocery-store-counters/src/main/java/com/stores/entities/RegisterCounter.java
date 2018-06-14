package com.stores.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegisterCounter implements Comparable<RegisterCounter>{

	private int assignedRegId;
	private List<ICustomer> custQueue;
	private int experties;
	private int timeCounter;
	
	
	/**
	 * 
	 * @param assignedRegId
	 * @param cashierType
	 */
	public RegisterCounter(int assignedRegId, CashierTypeEnum cashierType){
		this.assignedRegId = assignedRegId;
		this.experties = cashierType.getExperties();
		this.timeCounter = cashierType.getExperties();
		this.custQueue = new ArrayList<ICustomer>();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int getAssignedRegId(){
		return assignedRegId;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getQueueLength(){
		return custQueue.size();
	}
	
	/**
	 * 
	 * @param cust
	 */
	public void assignCustomerToQueue(ICustomer cust){
		custQueue.add(cust);
	}
	
	/**
	 * 
	 */
	public void checkoutItems(){
		if (isTimeToBill() && !custQueue.isEmpty()){
			Optional<ICustomer> optCust = custQueue.stream().findFirst();
			ICustomer cust = optCust.get();
			cust.billItem();
			System.out.println(cust.getCustomerType() + " " + cust.getTimeArrived() + " " + cust.getItemsInHand());
			if (cust.getItemsInHand() == 0){
				System.out.println("Removing the customer from List : " + cust.getCustomerType() + " " + cust.getTimeArrived() + " " + cust.getItemsInHand());
				custQueue.remove(cust);
			}
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	private boolean isTimeToBill(){
		if(--timeCounter == 0){
			timeCounter = experties;
			return true;
		} else {
			return false;
		}
	}
	
	
	
	public static void main(String ...args){
		
		System.out.println(CashierTypeEnum.CASHIER);
		ICustomer cust1 = new Customer("A 1 3");
		ICustomer cust2 = new Customer("B 2 5");
				
		RegisterCounter cashier = new RegisterCounter(1, CashierTypeEnum.CASHIER);
		cashier.assignCustomerToQueue(cust1);
		cashier.assignCustomerToQueue(cust2);
		for (int i=0; i<10; i++){
			//System.out.println("Time - " + i );
			cashier.checkoutItems();
		}
		
		
	}


	@Override
	public int compareTo(RegisterCounter o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
