package com.store.entities;

import java.util.List;

public interface IRegisterEntity extends Comparable<IRegisterEntity>  {

	public int getAssignedRegId();
	public List<ICustomerEntity> getCustQueue();
	
}
