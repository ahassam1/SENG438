package org.jfree.data.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.jfree.data.DataUtilities;

public class createNumberArray2DTest {

	private Number[][] realDataTest;
	private Number[][] emptyDataTest;
	private Number[][] nullDataTest;
	
	private double data[][] = {{0, 1, 0},
							  {1, 0, 1},
							  {0, 1, 0}};
	
	private double emptyData[][] = {};
	
	private double nullData[][];
	
	@Before
	public void setUp() throws Exception {
		realDataTest = DataUtilities.createNumberArray2D(data);
		emptyDataTest = DataUtilities.createNumberArray2D(emptyData);
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
		nullDataTest = DataUtilities.createNumberArray2D(nullData);
	}
	
}