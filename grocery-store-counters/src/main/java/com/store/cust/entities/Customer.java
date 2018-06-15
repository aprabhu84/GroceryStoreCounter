package com.store.cust.entities;

public class Customer implements ICustomer {
	
	private static final String REC_SEPARATOR = " ";
	
	private String customerType;
	private int timeArrived;
	private int itemsInHand;
	
	public Customer(String customerEntry){
		String[] records = customerEntry.split(REC_SEPARATOR);
		this.customerType = records[0];
		this.timeArrived = Integer.parseInt(records[1]);
		this.itemsInHand = Integer.parseInt(records[2]);
	}
	
	public String getCustomerType() {
		return customerType;
	}

	public int getTimeArrived() {
		return timeArrived;
	}

	public int getItemsInHand() {
		return itemsInHand;
	}
	
	public void billItem(){
		itemsInHand--;
	}

	@Override
	public int compareTo(ICustomer o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
