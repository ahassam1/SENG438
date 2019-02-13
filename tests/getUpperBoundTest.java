package org.jfree.data.test;
import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;
import static org.junit.Assert.*;
import org.jfree.data.Range;

public class getUpperBoundTest {
	
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
		assertEquals("The upperbound should be 1",
				1, exampleRange.getUpperBound(), .000000001d);
	}

	@Test(expected=NullPointerException.class)
	public void testNullInput() {
	   exampleRange2.getUpperBound();
	}
	@Test
	public void sameBoundTest()
	{
		assertEquals("The upperbound should be 0",
				0, exampleRange3.getUpperBound(), .000000001d);
	}
	
	

}
