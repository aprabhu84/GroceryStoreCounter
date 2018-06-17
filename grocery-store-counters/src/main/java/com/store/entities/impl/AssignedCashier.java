package com.store.entities.impl;

import java.util.Optional;

import com.store.entities.IAssignedCashier;
import com.store.entities.ICustomerEntity;
import com.store.entities.IRegisterEntity;
import com.store.enums.CashierTypeEnum;
import com.store.logger.StoreEventLogger;

public class AssignedCashier implements IAssignedCashier {

	private IRegisterEntity register = null;

	private CashierTypeEnum cashierType;
	private int timeCounter;
	private int itemsWithLastCustomer;

	public AssignedCashier(int assignedRegId, CashierTypeEnum cashierType) {
		this.register = new RegisterEntity(assignedRegId);
		this.cashierType = cashierType;
		this.timeCounter = cashierType.getExperties();
	}

	@Override
	public void customerArrivesAtQueue(ICustomerEntity cust) {
		if (cust.getItemsInHand() > 0) {
			register.getCustQueue().add(cust);
			this.itemsWithLastCustomer = cust.getItemsInHand();
			StoreEventLogger.logMessage(cust, "Customer Goes to the Queue " + register.getAssignedRegId());
		} else {
			StoreEventLogger.logMessage(cust,
					"Customer is not added in Queue " + register.getAssignedRegId() + " since he has no items in hand");
		}
	}

	@Override
	public void cashierBillsAnItem() {
		if (isTimeToBill() && !register.getCustQueue().isEmpty()) {
			Optional<ICustomerEntity> optCust = register.getCustQueue().stream().findFirst();
			ICustomerEntity cust = optCust.get();
			cust.billItem();
			StoreEventLogger.logMessage(cust,
					"Customer is billed for 1 Item at the queue " + register.getAssignedRegId());
		}

	}

	@Override
	public void customerLeavesQueue(ICustomerEntity cust) {
		StoreEventLogger.logMessage(cust, "Customer Leaves the Queue " + register.getAssignedRegId());
		register.getCustQueue().remove(cust);
		if (register.getCustQueue().isEmpty()) {
			timeCounter = cashierType.getExperties() - 1;
		}
	}

	@Override
	public int getItemCountFromLastCustomer() {
		return itemsWithLastCustomer;

	}

	@Override
	public int getQueueLength() {
		return register.getCustQueue().size();

	}

	@Override
	public int getAssignedRegisterId() {
		return register.getAssignedRegId();
	}

	@Override
	public boolean isRegWithZeroItemsCust() {
		if (register.getCustQueue().size() >= 0)
			return register.getCustQueue().stream().anyMatch(cust -> cust.getItemsInHand() == 0);

		return false;
	}

	/**
	 * 
	 * @return
	 */
	private boolean isTimeToBill() {
		if (timeCounter <= 0) {
			timeCounter = cashierType.getExperties() - 1;
			return true;
		} else {
			timeCounter--;
			return false;
		}
	}

}
