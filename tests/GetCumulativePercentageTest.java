package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class GetCumulativePercentageTest {
	private KeyedValues values;
	private KeyedValues emptyValues;
	
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
		emptyValues = emptyContext.mock(KeyedValues.class);
		emptyContext.checking(new Expectations() {
			{
				allowing(emptyValues).getItemCount();
				will(returnValue(0));
			}
		});
	}
	
	@Test
	public void testRealData() {
		KeyedValues result = DataUtilities.getCumulativePercentages(values);
		for (int i = 0; i < result.getItemCount(); i++) {
			double r = (double) result.getValue(i);
			assertTrue(-1.0 <= r && r <= 1.0);
		}
	}
	
	@Test
	public void testEmptyData() {
		KeyedValues result = DataUtilities.getCumulativePercentages(emptyValues);
		assertEquals(0, result.getItemCount());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNullData() {
		DataUtilities.getCumulativePercentages(null);
	}
}
