package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class GetCumulativePercentageTest {
	private KeyedValues values;
	private KeyedValues empty;
	
	private Mockery mockingContext;
	private Mockery emptyContext;
	
	@Before
	public void setup() throws Exception {
		mockingContext = new Mockery();
		values = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
				allowing(values).getItemCount();
				will(returnValue(3));
				
				allowing(values).getKey(0);
				will(returnValue(0));
				allowing(values).getValue(0);
				will(returnValue(5));
				
				allowing(values).getKey(1);
				will(returnValue(1));
				allowing(values).getValue(1);
				will(returnValue(9));
				
				allowing(values).getKey(2);
				will(returnValue(2));
				allowing(values).getValue(2);
				will(returnValue(2));
			}
		});
		
		emptyContext = new Mockery();
		empty = emptyContext.mock(KeyedValues.class);
		emptyContext.checking(new Expectations() {
			{
				allowing(empty).getItemCount();
				will(returnValue(0));
			}
		});
	}
	
	@Test
	public void testRealDataRange() {
		KeyedValues result = DataUtilities.getCumulativePercentages(values);
		for (int i = 0; i < result.getItemCount(); i++) {
			double r = (double) result.getValue(i);
			assertTrue("Values should be between 0 and 1.0", 0 <= r && r <= 1.0);
		}
	}
	
	@Test
	public void testRealDataIncrease() {
		KeyedValues result = DataUtilities.getCumulativePercentages(values);
		for (int i = 0; i < result.getItemCount(); i++) {
			if (i > 0) {
				double r1 = (double) result.getValue(i);
				double r2 = (double) result.getValue(i-1);
				assertTrue("Values should be between increasing", r1 > r2);
			}
		}
	}
	
	@Test
	public void testEmptyData() {
		KeyedValues result = DataUtilities.getCumulativePercentages(empty);
		assertEquals(0, result.getItemCount());
	}
	
	@Test (expected = Error.class)
	public void testNullData() {
		try {
			DataUtilities.getCumulativePercentages(null);
		} catch (Exception e) {
			if (e.getClass() == InvalidParameterException.class) {
				assertTrue(true);
			}
			else {
				assertTrue("Exception should be InvalidParameterException", false);
			}
		}
	}
}
