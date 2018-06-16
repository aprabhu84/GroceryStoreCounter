package com.store.cust.entities;

import com.store.enums.CustomerTypeEnum;

public class Customer implements ICustomer {

	private static final String REC_SEPARATOR = " ";

	private CustomerTypeEnum customerType;
	private int timeArrived;
	private int itemsInHand;

	public Customer(String customerEntry) {
		String[] records = customerEntry.split(REC_SEPARATOR);
		this.customerType = CustomerTypeEnum.getCustomerTypeEnum(records[0]);
		this.timeArrived = Integer.parseInt(records[1]);
		this.itemsInHand = Integer.parseInt(records[2]);
	}

	public CustomerTypeEnum getCustomerType() {
		return customerType;
	}

	public int getTimeArrived() {
		return timeArrived;
	}

	public int getItemsInHand() {
		return itemsInHand;
	}

	public void billItem() {
		itemsInHand--;
	}

	@Override
	public int compareTo(ICustomer o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "Customer Type : " + getCustomerType() 
			+ ", Arrived At : " + getTimeArrived() 
			+ ", With Items : " + getItemsInHand();
	}

}
