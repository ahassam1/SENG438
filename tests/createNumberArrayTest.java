package org.jfree.data.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jfree.data.DataUtilities;
import org.junit.Before;
import org.junit.Test;

public class createNumberArrayTest {

	private Number[] realDataTest;
	private Number[] emptyDataTest;
	private Number[] nullDataTest;
	private Number[] emptyArgumentTest;
	
	private double data[] = {0, 1, 2, 3, 4, 5};
	private double emptyData[] = {};
	private double nullData[];

	private Number[] expected = {0, 1, 2, 3, 4, 5};
	
	@Before
	public void setUp() throws Exception {
		realDataTest = DataUtilities.createNumberArray(data);
		emptyDataTest = DataUtilities.createNumberArray(emptyData);
	}

	@Test
	public void testRealData() {
		assertNotNull(realDataTest);
	}
	
	@Test
	public void testEmptyData() {
		assertNotNull(emptyDataTest);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNullData() {
		nullDataTest = DataUtilities.createNumberArray(nullData);
	}
	@Test
	public void testExpectedArray()
	{
		boolean result = expected.equals(realDataTest);
		assertTrue(result);
	}
	
}