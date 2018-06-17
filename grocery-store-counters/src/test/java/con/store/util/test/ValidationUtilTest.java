package con.store.util.test;

import org.junit.Assert;
import org.junit.Test;

import con.store.util.ValidationUtil;

public class ValidationUtilTest {

	@Test
	public void ValidationUtil_testIsValidCustomer_Positive(){
		boolean result = ValidationUtil.isCustRecordCorrect("A 1 2");
		Assert.assertTrue(result);
	}
	
	@Test
	public void ValidationUtil_testIsValidCustomer_BlankRecord(){
		boolean result = ValidationUtil.isCustRecordCorrect("");
		Assert.assertFalse(result);
	}
	
	@Test
	public void ValidationUtil_testIsValidCustomer_SpaceRecord(){
		boolean result = ValidationUtil.isCustRecordCorrect(" ");
		Assert.assertFalse(result);
	}
	
	@Test
	public void ValidationUtil_testIsValidCustomer_MissingRecord(){
		boolean result = ValidationUtil.isCustRecordCorrect("A 1");
		Assert.assertFalse(result);
	}
	
	@Test
	public void ValidationUtil_testIsValidCustomer_Valid(){
		boolean result = ValidationUtil.isValidCustomer("A 1 2");
		Assert.assertTrue(result);
	}
	
	@Test
	public void ValidationUtil_IsValidCustomer_Invalid(){
		boolean result = ValidationUtil.isValidCustomer("X 1 2");
		Assert.assertFalse(result);
	}
	
	
}
