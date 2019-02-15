package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class DataUtilititesRowTotal {
	private Values2D values;
	private Mockery mockingContext;
	
	@Before
	public void beforeTestRow0() throws Exception{
		mockingContext = new Mockery();
		values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				allowing(values).getRowCount();
				will(returnValue(3));
				allowing(values).getColumnCount();
				will(returnValue(3));
				//row 0
				allowing(values).getValue(0,0);
				will(returnValue(4));
				allowing(values).getValue(0,1);
				will(returnValue(10));
				allowing(values).getValue(0,2);
				will(returnValue(6));
				//row 1
				allowing(values).getValue(1,0);
				will(returnValue(10));
				allowing(values).getValue(1,1);
				will(returnValue(10));
				allowing(values).getValue(1,2);
				will(returnValue(10));
				//row 2
				allowing(values).getValue(2,0);
				will(returnValue(2));
				allowing(values).getValue(2,1);
				will(returnValue(3));
				allowing(values).getValue(2,2);
				will(returnValue(4));
				//row -1 (testing exterior boundaries)
				allowing(values).getValue(-1,0);
				will(returnValue(1));
				allowing(values).getValue(-1,1);
				will(returnValue(1));
				allowing(values).getValue(-1,2);
				will(returnValue(1));
				//row 3 (testing exterior boundaries)
				allowing(values).getValue(3,0);
				will(returnValue(4));
				allowing(values).getValue(3,1);
				will(returnValue(4));
				allowing(values).getValue(3,2);
				will(returnValue(4));
			}
		});
	}
	//Testing row 0's total (lower bound)
	@Test
	public void Row0() {
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("Row 0 should have a total of 20", 20, result, 0.00001d);
	}
	
	//Testing row 1's total (central value of allowed rows)
	@Test
	public void nominalRow() {
		double result = DataUtilities.calculateRowTotal(values, 1);
		assertEquals("Row 1 should have a total of 30", 30, result, 0.00001d);
	}
	//Testing row -1's total (should return 0 since there is no row -1)
	@Test
	public void RowMinus1() {
		double result = DataUtilities.calculateRowTotal(values, -1);
		assertEquals("Row -1 should return 0 since invalid input", 0, result, 0.00001d);
	}
	
	//Testing row 2's total (upper bound)
	@Test
	public void Row2() {
		double result = DataUtilities.calculateRowTotal(values, 2);
		assertEquals("Row 2 should have a total of 9", 9, result, 0.00001d);
	}
	//Testing row 3's total (should return 0 since there is no row 3 in the given values2D object)
	@Test
	public void Row3() {
		double result = DataUtilities.calculateRowTotal(values, 3);
		assertEquals("Row 3 should have a total of 0 due to out of bounds(invalid input)", 0, result, 0.00001d);
	}
	
	//Testing an invalid Values2D object
	@Test 
	public void RowNullData() {
		try {
			DataUtilities.calculateRowTotal(null, 0);
		}
		catch(Exception e) {
			assertEquals("An invalid parameter exception should be thrown", InvalidParameterException.class, e.getClass());
		}
		
	}
}

