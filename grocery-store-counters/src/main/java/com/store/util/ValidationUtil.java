package com.store.util;

import java.util.Arrays;
import java.util.stream.Stream;

import com.store.entities.ICustomerEntity;
import com.store.enums.CustomerTypeEnum;

public class ValidationUtil {
	
	

	/**
	 * 
	 * @param custRecord
	 * @return
	 */
	public static boolean isCustRecordCorrect(String custRecord){
		
		if(custRecord == null || custRecord.length() <= 0){
			return false;
		}
		
		String[] records = custRecord.split(ICustomerEntity.REC_SEPARATOR);
		
		if(records.length < 3)
			return false;

		Stream<String> str = Arrays.stream(records);
		return str.noneMatch(custRec -> (custRec == null || custRec.equals("")));
	}
	
	/**
	 * 
	 * @param custRecord
	 * @return
	 */
	public static boolean isValidCustomer(String custRecord){
		if (isCustRecordCorrect(custRecord)){
			CustomerTypeEnum custType = CustomerTypeEnum.getCustomerTypeEnum(custRecord
							.split(ICustomerEntity.REC_SEPARATOR)[0]);
			if(CustomerTypeEnum.INVALID.equals(custType))
				return false;
		} else
			return false;
		return true;
	}
	
	/**
	 * 
	 * @param custRecord
	 * @return
	 */
	public static boolean isCustomerWithNoItems(String custRecord){
		String custItemInHand = custRecord
				.split(ICustomerEntity.REC_SEPARATOR)[2];
		if(custItemInHand.trim().equals("") || custItemInHand.trim().equals("0")){
			return true;
		}
		return false;
	}
	
	
}
