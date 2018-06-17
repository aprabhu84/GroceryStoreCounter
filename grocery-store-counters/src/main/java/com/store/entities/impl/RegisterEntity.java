package com.store.entities.impl;

import java.util.ArrayList;
import java.util.List;

import com.store.entities.ICustomerEntity;
import com.store.entities.IRegisterEntity;

public class RegisterEntity implements IRegisterEntity{

	private int assignedRegId;
	private List<ICustomerEntity> custQueue;
	//private int timeCounter;
	//private int itemsWithLastCustomer;

	/**
	 * 
	 * @param assignedRegId
	 * @param cashierType
	 */
	public RegisterEntity(int assignedRegId) {
		this.assignedRegId = assignedRegId;
		//this.timeCounter = cashierType.getExperties();
		this.custQueue = new ArrayList<ICustomerEntity>();
	}

	/**
	 * 
	 * @return
	 */
	public int getAssignedRegId() {
		return assignedRegId;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int getQueueLength() {
		return custQueue.size();
	}
	
	
	public List<ICustomerEntity> getCustQueue(){
		return custQueue;
	}

	@Override
	public int compareTo(IRegisterEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
