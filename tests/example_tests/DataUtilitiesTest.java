package org.jfree.data.junit;

import static org.junit.Assert.*;

import org.jfree.data.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DataUtilitiesTest
{
	private Values2D values;
	
	@Before
	public void setUp()
	{
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values = testValues;
		
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullDataColumnTotal()
	{
		assertEquals(0.0, DataUtilities.calculateColumnTotal(null, 0), 0.0000001d);
	}
	
	@Test
	public void testValidDataAndColumnColumnTotal()
	{
		assertEquals(5.0, DataUtilities.calculateColumnTotal(values, 0), 0.0000001d);
	}
	
	@Test(expected=NullPointerException.class)
	public void testValidDataInvalidColumnColumnTotal()
	{
		assertEquals(0.0, DataUtilities.calculateColumnTotal(null, 1), 0.0000001d);
	}
	
	@After
	public void tearDown()
	{
		values = null;
	}
}
