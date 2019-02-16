package org.jfree.data.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jfree.data.DataUtilities;
import org.junit.Before;
import org.junit.Test;

public class createNumberArray2DTest {

	private Number[][] realDataTest;
	private Number[][] emptyDataTest;
	private Number[][] nullDataTest;
	
	private double data[][] = {{0, 1, 0},
							  {1, 0, 1},
							  {0, 1, 0}};
	
	private Number expected[][] = {{0.0, 1.0, 0.0},
								   {1.0, 0.0, 1.0},
								   {0.0, 1.0, 0.0}};
	
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
	@Test
	public void testExpectedArray()
	{
		boolean result = Arrays.equals(expected, realDataTest);
		System.out.println(result);
		for(int i = 0; i < 3; i++)
			for(int i1 =0; i1 < 3; i1++)
			{
				System.out.println(realDataTest[i][i1]);
			}
		assertTrue(result);
		
	}

	
}