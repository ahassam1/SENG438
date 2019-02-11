package org.jfree.data.test;
import static org.junit.Assert.fail;
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
		exampleRange.getUpperBound();
	}

	@Test(expected=NullPointerException.class)
	public void testNullInput() {
	   exampleRange2.getUpperBound();
	}
	@Test
	public void sameBoundTest()
	{
		exampleRange3.getUpperBound();
	}

}
