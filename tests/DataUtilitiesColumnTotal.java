package org.jfree.data.test;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

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
	
	@Test
	public void Column0() {
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals("Column 0 should have a total of 20", 20, result, 0.00001d);
	}
	
	
	@Test
	public void nominalColumn() {
		double result = DataUtilities.calculateColumnTotal(values, 1);
		assertEquals("Column 1 should have a total of 30", 30, result, 0.00001d);
	}
	
	@Test
	public void ColumnMinus1() {
		double result = DataUtilities.calculateColumnTotal(values, -1);
		assertEquals("Column -1 should return 0 since invalid input", 0, result, 0.00001d);
	}
	
	
	@Test
	public void Column2() {
		double result = DataUtilities.calculateColumnTotal(values, 2);
		assertEquals("Column 2 should have a total of 9", 9, result, 0.00001d);
	}
	
	@Test
	public void Column3() {
		double result = DataUtilities.calculateColumnTotal(values, 3);
		assertEquals("Column 3 should have a total of 0 due to out of bounds(invalid input)", 0, result, 0.00001d);
	}
	
	
	@Test (expected = InvalidParameterException.class)
	public void ColumnNullData() {
		double result = DataUtilities.calculateColumnTotal(null, 0);
		assertEquals("Null data should throw an exception", 20, result, 0.00001d);
	}
	
	


}
