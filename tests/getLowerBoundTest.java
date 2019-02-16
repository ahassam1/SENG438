package org.jfree.data.test;
import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;
import static org.junit.Assert.*;
import org.jfree.data.Range;

public class getLowerBoundTest {
	
	private Range exampleRange;
	private Range exampleRange2;
	private Range exampleRange3;
	private Range exampleRange4;
	private Range exampleRange5;
	private Range exampleRange6;

	@Before
	public void setUp() throws Exception 
	{
		exampleRange = new Range(-1, 1);
		exampleRange2 = null;
		exampleRange3 = new Range(0, 0);
		exampleRange4 = new Range(-101, 1);
		exampleRange5 = new Range(20, 200);
		exampleRange6 = new Range(-150, 150);
	}
	@Test
	public void normalInputTest()
	{
		assertEquals("The lowerbound should be -1",
				-1, exampleRange.getLowerBound(), .000000001d);
	}
	
	@Test
	public void boundaryLowerInputTest()
	{
		assertEquals("The lowerbound should be -101",
				-101, exampleRange4.getLowerBound(), .000000001d);
	}
	@Test
	public void boundaryUpperInputTest()
	{
		assertEquals("The lowerbound should be 20",
				20, exampleRange5.getLowerBound(), .000000001d);
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
	@Test
	public void worstCaseTest()
	{
		assertEquals("The lowerbound should be -150",
				-150, exampleRange6.getLowerBound(), .000000001d);
	}

}

