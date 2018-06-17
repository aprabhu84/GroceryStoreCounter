package com.store.queue.selectorschain.test;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.store.entities.ICustomerEntity;
import com.store.entities.impl.CustomerEntity;
import com.store.queue.SingletonQueueCreator;
import com.store.queue.selection.EmptyQueueSelection;
import com.store.queue.selection.QueueSelectionChain;

import junit.framework.Assert;

public class SelectionChainTest {
	
	private QueueSelectionChain selectQueueForCust;
	List<ICustomerEntity> custList;
	
	@BeforeClass
	public void init(){
		SingletonQueueCreator.createInstance(2);
		selectQueueForCust = new EmptyQueueSelection();
	}
	
	@Test
	public void testSelectQueueChain_assignEmptyQueueToTypeA(){
		selectQueueForCust.selectQueue(new CustomerEntity("A 1 2"));
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(0).getQueueLength(),1);
	}
	
	@Test 
	public void testSelectQueueChain_assignEmptyQueueToTypeB(){
		selectQueueForCust.selectQueue(new CustomerEntity("B 1 3"));
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(0).getQueueLength(),1);
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(1).getQueueLength(),1);
	}
	
	@Test
	public void testSelectQueueChain_assignFirstQueueWhenQueueEqualTypeA(){
		selectQueueForCust.selectQueue(new CustomerEntity("A 2 5"));
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(0).getQueueLength(),2);
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(1).getQueueLength(),1);
	}
	
	@Test
	public void testSelectQueueChain_assignShortestQueueToTypeA(){
		selectQueueForCust.selectQueue(new CustomerEntity("A 3 5"));
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(0).getQueueLength(),2);
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(1).getQueueLength(),2);
	}
	
	@Test
	public void testSelectQueueChain_assignFirstQueueWhenItemsEqualTypeB(){
		selectQueueForCust.selectQueue(new CustomerEntity("B 3 4"));
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(0).getQueueLength(),3);
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(1).getQueueLength(),2);
	}
	
	@Test
	public void testSelectQueueChain_assignQueueWithLeastLastItemsTypeB(){
		selectQueueForCust.selectQueue(new CustomerEntity("B 3 5"));
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(0).getQueueLength(),4);
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(1).getQueueLength(),2);
	}

	@Test
	public void testSelectQueueChain_InvalidCustomerTypeC(){
		selectQueueForCust.selectQueue(new CustomerEntity("C 3 5"));
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(0).getQueueLength(),4);
		Assert.assertEquals(SingletonQueueCreator.getAvailableCashiers().get(1).getQueueLength(),2);
	}
	
}
