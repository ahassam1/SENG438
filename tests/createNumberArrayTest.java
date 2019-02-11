package org.jfree.data.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.jfree.data.DataUtilities;

public class createNumberArrayTest {

	private Number[] realDataTest;
	private Number[] emptyDataTest;
	private Number[] nullDataTest;
	private Number[] emptyArgumentTest;
	
	private double data[] = {0, 1, 2, 3, 4, 5};
	private double emptyData[] = {};
	private double nullData[];
	
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
	
}