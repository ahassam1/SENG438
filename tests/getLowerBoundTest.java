package org.jfree.data.test;

import static org.junit.Assert.assertEquals;

import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

public class getLowerBoundTest {
	
	private Range exampleRange;
	private Range exampleRange2;
	private Range exampleRange3;
	
	@Before
	public void setUp() throws Exception 
	{
		exampleRange = new Range(-1, 1);
		exampleRange2 = null;
		exampleRange3 = new Range(0, 0);
	}
	@Test
	public void normalInputTest()
	{
		assertEquals("The lowerbound should be -1",
				-1, exampleRange.getLowerBound(), .000000001d);
	}

	@Test(expected=NullPointerException.class)
	public void testNullInput() {
	   exampleRange2.getLowerBound();
	}
	@Test
	public void sameBoundTest()
	{
		assertEquals("The lowerbound should be 0",
				0, exampleRange3.getLowerBound(), .000000001d);

	}

}

