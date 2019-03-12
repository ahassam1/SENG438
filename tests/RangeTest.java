package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest{

	private Range testRange1;
	private Range testRange2;
	private Range testRange3;
	private Range testRange4;
	
	private Range exampleRange;
	private Range exampleRange2;
	private Range exampleRange3;
	private Range exampleRange4;
	private Range exampleRange5;
	private Range exampleRange6;
	
	@Before
	public void setUp() throws Exception {
		
		//Comparing two Equal Ranges
		testRange1 = new Range(-1, 1);
		testRange2 = new Range(-1, 1);
		
		//Comparing two Different Ranges;
		testRange3 = new Range(-1, 1);
		testRange4 = new Range(-2, 2);
		
		exampleRange = new Range(-1, 1);
		exampleRange2 = null;
		exampleRange3 = new Range(0, 0);
		exampleRange4 = new Range(-101, 1);
		exampleRange5 = new Range(20, 200);
		exampleRange6 = new Range(-150, 150);
	}
	
	/* equals() test */
	
	@Test
	public void testEqualsTrue() {
		assertTrue(testRange1.equals(testRange2));
	}
	
	@Test 
	public void testEqualsFalse() {
		assertFalse(testRange3.equals(testRange4));
	}
	
	@Test
	public void testEqualsSelf() {
		assertTrue(testRange1.equals(testRange1));
	}
	
	@Test
	public void testEqualsNull() {
		assertFalse(testRange1.equals(null));
	}
	
	/* getLength() test */
	@Test
	public void testGetLengthTrue() {
		assertEquals(testRange1.getLength(), testRange2.getLength(), 0);
	}
	
	@Test
	public void testGetLengthFalse() {
		assertNotSame(testRange3.getLength(), testRange4.getLength());
	}
	
	@Test
	public void testGetLengthNull() {
		assertNotSame(testRange1.getLength(), null);
	}
	
	/* getLowerBound() test */
	@Test
	public void testGetLowerBoundNormalInput() {
		assertEquals("The lowerbound should be -1",
				-1, exampleRange.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testGetLowerBoundLowerBoundaryInput() {
		assertEquals("The lowerbound should be -101",
				-101, exampleRange4.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testGetLowerBoundUpperBoundaryInput() {
		assertEquals("The lowerbound should be 20",
				20, exampleRange5.getLowerBound(), .000000001d);
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetLowerBoundNullInput() {
	   exampleRange2.getLowerBound();
	   fail("Test should not get to this point.");
	}
	
	@Test
	public void testGetLowerBoundSameBound() {
		assertEquals("The lowerbound should be 0",
				0, exampleRange3.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testGetLowerBoundWorstCase() {
		assertEquals("The lowerbound should be -150",
				-150, exampleRange6.getLowerBound(), .000000001d);
	}
	
	/* getUpperBound() tests */
	@Test
	public void testGetUpperBoundNormalInput() {
		assertEquals("The upper bound should be 1",
				1, exampleRange.getUpperBound(), .000000001d);
	}
	
	@Test
	public void testGetUpperBoundLowerBoundaryInput() {
		assertEquals("The upper bound should be 1",
				1, exampleRange4.getUpperBound(), .000000001d);
	}
	
	@Test
	public void testGetUpperBoundUpperInputBoundaryTest() {
		assertEquals("The upper bound should be 200",
				200, exampleRange5.getUpperBound(), .000000001d);
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetUpperBoundNullInput() {
	   exampleRange2.getUpperBound();
	}
	
	@Test
	public void testGetUpperBoundSameBound() {
		assertEquals("The upper bound should be 0",
				0, exampleRange3.getUpperBound(), .000000001d);
	}
	
	@Test
	public void testGetUpperBoundWorstCase() {
		assertEquals("The upper bound should be 150",
				150, exampleRange6.getUpperBound(), .000000001d);
	}
}
