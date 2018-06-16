package com.store.queue.selectorschain.test;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.store.cust.entities.Customer;
import com.store.cust.entities.ICustomer;
import com.store.queue.SingletonQueueCreator;
import com.store.queue.entity.IRegisterCounter;
import com.store.queue.selectorschain.EmptyQueueSelection;
import com.store.queue.selectorschain.QueueSelectionChain;

import junit.framework.Assert;

public class SelectionChainTest {
	
	private static QueueSelectionChain selectQueueForCust;
	List<ICustomer> custList;
	
	@BeforeClass
	public static void init(){
		SingletonQueueCreator.getInstance(2);
		selectQueueForCust = new EmptyQueueSelection();
	}
	
	@Test
	public void testSelectQueueChain_assignEmptyQueueToTypeA(){
		selectQueueForCust.selectQueue(new Customer("A 1 2"));
		List<IRegisterCounter> regList = SingletonQueueCreator.getAvailableRegisters();
		Assert.assertEquals(regList.get(0).getQueueLength(),1);
	}
	
	@Test 
	public void testSelectQueueChain_assignEmptyQueueToTypeB(){
		selectQueueForCust.selectQueue(new Customer("B 1 3"));
		List<IRegisterCounter> regList = SingletonQueueCreator.getAvailableRegisters();
		Assert.assertEquals(regList.get(0).getQueueLength(),1);
		Assert.assertEquals(regList.get(1).getQueueLength(),1);
	}
	
	@Test
	public void testSelectQueueChain_assignFirstQueueWhenQueueEqualTypeA(){
		selectQueueForCust.selectQueue(new Customer("A 2 5"));
		List<IRegisterCounter> regList = SingletonQueueCreator.getAvailableRegisters();
		Assert.assertEquals(regList.get(0).getQueueLength(),2);
		Assert.assertEquals(regList.get(1).getQueueLength(),1);
	}
	
	@Test
	public void testSelectQueueChain_assignShortestQueueToTypeA(){
		selectQueueForCust.selectQueue(new Customer("A 3 5"));
		List<IRegisterCounter> regList = SingletonQueueCreator.getAvailableRegisters();
		Assert.assertEquals(regList.get(0).getQueueLength(),2);
		Assert.assertEquals(regList.get(1).getQueueLength(),2);
	}
	
	@Test
	public void testSelectQueueChain_assignFirstQueueWhenItemsEqualTypeB(){
		selectQueueForCust.selectQueue(new Customer("B 3 4"));
		List<IRegisterCounter> regList = SingletonQueueCreator.getAvailableRegisters();
		Assert.assertEquals(regList.get(0).getQueueLength(),3);
		Assert.assertEquals(regList.get(1).getQueueLength(),2);
	}
	
	@Test
	public void testSelectQueueChain_assignQueueWithLeastLastItemsTypeB(){
		selectQueueForCust.selectQueue(new Customer("B 3 5"));
		List<IRegisterCounter> regList = SingletonQueueCreator.getAvailableRegisters();
		Assert.assertEquals(regList.get(0).getQueueLength(),4);
		Assert.assertEquals(regList.get(1).getQueueLength(),2);
	}

	@Test
	public void testSelectQueueChain_InvalidCustomerTypeC(){
		selectQueueForCust.selectQueue(new Customer("C 3 5"));
		List<IRegisterCounter> regList = SingletonQueueCreator.getAvailableRegisters();
		Assert.assertEquals(regList.get(0).getQueueLength(),4);
		Assert.assertEquals(regList.get(1).getQueueLength(),2);
	}
	
}
