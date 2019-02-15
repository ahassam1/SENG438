package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.*;
import org.junit.*;

public class DataUtilitiesColumnTotal{
	
	private Values2D values;
	private Mockery mockingContext;
	
	@Before
	public void beforeTestColumn0() throws Exception{
		mockingContext = new Mockery();
		values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				allowing(values).getRowCount();
				will(returnValue(3));
				allowing(values).getColumnCount();
				will(returnValue(3));
				//column 0
				allowing(values).getValue(0,0);
				will(returnValue(4));
				allowing(values).getValue(1,0);
				will(returnValue(10));
				allowing(values).getValue(2,0);
				will(returnValue(6));
				//column 1
				allowing(values).getValue(0,1);
				will(returnValue(10));
				allowing(values).getValue(1,1);
				will(returnValue(10));
				allowing(values).getValue(2,1);
				will(returnValue(10));
				//column 2
				allowing(values).getValue(0,2);
				will(returnValue(2));
				allowing(values).getValue(1,2);
				will(returnValue(3));
				allowing(values).getValue(2,2);
				will(returnValue(4));
				//column -1 (testing exterior boundaries)
				allowing(values).getValue(0,-1);
				will(returnValue(1));
				allowing(values).getValue(1,-1);
				will(returnValue(1));
				allowing(values).getValue(2,-1);
				will(returnValue(1));
				//column 3 (testing exterior boundaries)
				allowing(values).getValue(0,3);
				will(returnValue(4));
				allowing(values).getValue(1,3);
				will(returnValue(4));
				allowing(values).getValue(2,3);
				will(returnValue(4));
			}
		});
	}
	//Testing column 0's total (lower bound)
	@Test
	public void Column0() {
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals("Column 0 should have a total of 20", 20, result, 0.00001d);
	}
	
	//Testing column 1's total (a row in the center of the acceptable range)
	@Test
	public void nominalColumn() {
		double result = DataUtilities.calculateColumnTotal(values, 1);
		assertEquals("Column 1 should have a total of 30", 30, result, 0.00001d);
	}
	//Testing column -1's total, should return 0 since there is no column -1
	@Test
	public void ColumnMinus1() {
		double result = DataUtilities.calculateColumnTotal(values, -1);
		assertEquals("Column -1 should return 0 since invalid input", 0, result, 0.00001d);
	}
	
	//Testing column 2's total(upper bound)
	@Test
	public void Column2() {
		double result = DataUtilities.calculateColumnTotal(values, 2);
		assertEquals("Column 2 should have a total of 9", 9, result, 0.00001d);
	}
	//Testing column 3's total, should return 0 since there are only 3 columns in the mocked object
	@Test
	public void Column3() {
		double result = DataUtilities.calculateColumnTotal(values, 3);
		assertEquals("Column 3 should have a total of 0 due to out of bounds(invalid input)", 0, result, 0.00001d);
	}
	
	//Testing an invalid values2D object
	@Test 
	public void ColumnNullData() {
		try {
			DataUtilities.calculateColumnTotal(null, 0);
		}
		catch(Exception e) {
			assertEquals("An invalid parameter exception should be thrown", InvalidParameterException.class, e.getClass());
		}
		
	}
}
