package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.Range;
import org.junit.*;

public class RangeTest{
	private Range boundRange;
	private Range boundRange2;
	private Range boundRange3;
	private Range boundRange4;
	private Range boundRange5;
	private Range boundRange6;
		
	private Range testRange1;
	private Range testRange2;
	private Range testRange3;
	private Range testRange4;
	private Range testRange5;
	
	@Before
	public void setUp() throws Exception {
		testRange1 = new Range(-1, 1);
		testRange2 = new Range(-1, 1);
		testRange3 = new Range(-1, 1);
		testRange4 = new Range(-2, 2);
		testRange5 = new Range(4, 8);
		
		boundRange = new Range(-1, 1);
		boundRange2 = null;
		boundRange3 = new Range(0, 0);
		boundRange4 = new Range(-101, 1);
		boundRange5 = new Range(20, 200);
		boundRange6 = new Range(-150, 150);
	}
	
	/* Constructor tests */
	@Test
	public void testConstructorNormalInput() {
		Range temp = new Range(-1, 1);
		assertEquals("The Ranges should match", temp, testRange1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testContstructorInvalidInput() {
		new Range(1, -1);
	}
	
	/* getLowerBound() tests */
	
	@Test
	public void testGetLowerBoundNormalInput() {
		assertEquals("The lowerbound should be -1",
				-1, boundRange.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testGetLowerBoundLowerBoundaryInput() {
		assertEquals("The lowerbound should be -101",
				-101, boundRange4.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testGetLowerBoundUpperBoundaryInput() {
		assertEquals("The lowerbound should be 20",
				20, boundRange5.getLowerBound(), .000000001d);
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetLowerBoundNullInput() {
		boundRange2.getLowerBound();
		fail("Test should not get to this point.");
	}
	
	@Test
	public void testGetLowerBoundSameBound() {
		assertEquals("The lowerbound should be 0",
				0, boundRange3.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testGetLowerBoundWorstCase() {
		assertEquals("The lowerbound should be -150",
				-150, boundRange6.getLowerBound(), .000000001d);
	}
	
	/* getUpperBound() tests */
	
	@Test
	public void testGetUpperBoundNormalInput() {
		assertEquals("The upper bound should be 1",
				1, boundRange.getUpperBound(), .000000001d);
	}
	
	@Test
	public void testGetUpperBoundLowerBoundaryInput() {
		assertEquals("The upper bound should be 1",
				1, boundRange4.getUpperBound(), .000000001d);
	}
	
	@Test
	public void testGetUpperBoundUpperInputBoundaryTest() {
		assertEquals("The upper bound should be 200",
				200, boundRange5.getUpperBound(), .000000001d);
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetUpperBoundNullInput() {
	   boundRange2.getUpperBound();
	}
	
	@Test
	public void testGetUpperBoundSameBound() {
		assertEquals("The upper bound should be 0",
				0, boundRange3.getUpperBound(), .000000001d);
	}
	
	@Test
	public void testGetUpperBoundWorstCase() {
		assertEquals("The upper bound should be 150",
				150, boundRange6.getUpperBound(), .000000001d);
	}
	
	/* getLength() tests */
	
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
	
	/* getCentralValue() tests */
	@Test
	public void testGetCentralValue() {
		double result = testRange1.getCentralValue();
		assertEquals("The Central Range should be 0.", 0, result, 0);
	}
	
	/* contains() tests */
	@Test
	public void testContainsBelowLowerBound() {
		boolean result = testRange1.contains(-2);
		assertEquals("The value of -2 should not be in range.", result, false);
	}
	
	@Test
	public void testContainsWithinBound() {
		boolean result = testRange1.contains(0);
		assertEquals("The value of 0 should be in range.", result, true);
	}
	
	@Test
	public void testContainsAboveUpperBound() {
		boolean result = testRange1.contains(2);
		assertEquals("The value of 2 should not be in range.", result, false);
	}
	
	/* intersects() tests */
	
	@Test
	public void testIntersects() {
		boolean result = testRange4.intersects(-1, 1);
		assertEquals("The ranges should intersect.", result, true);
	}
	
	/* constrain() tests */
	
	@Test
	public void testConstrainInBound() {
		double result = testRange1.constrain(0);
		assertEquals("Result should be 0.", 0, result, 0);
	}
	
	@Test
	public void testConstrainBelowLowerBound() {
		double result = testRange1.constrain(-2);
		assertEquals("Result should be -1.", -1, result, 0);
	}
	
	@Test
	public void testConstrainAboveUpperBound() {
		double result = testRange1.constrain(2);
		assertEquals("Result should be 1.", 1, result, 0);
	}
	
	/* combine() tests */
	
	@Test
	public void testCombineRange1Null() {
		Range result = Range.combine(null, testRange5);
		assertEquals("Should get testRange5.", testRange5, result);
	}
	
	@Test
	public void testCombineRange2Null() {
		Range result = Range.combine(testRange1, null);
		assertEquals("Should get null.", null, result);
	}
	
	@Test
	public void testCombine() {
		Range expected = new Range(-1, 8);
		Range result = Range.combine(testRange1, testRange5);
		assertEquals("Should get Range(-1, 8).", expected, result);
	}
	
	/* expandToInclude() tests */
	
	@Test
	public void testExpandToIncludeNull() {
		Range expected = new Range(2, 2);
		Range result = Range.expandToInclude(null, 2);
		assertEquals("Should get Range(2, 2)", expected, result);
	}
	
	@Test
	public void testExpandToIncludeBelowLowerBound() {
		Range expected = new Range(-2, 1);
		Range result = Range.expandToInclude(testRange1, -2);
		assertEquals("Should get Range(-2, 1)", expected, result);
	}
	
	@Test
	public void testExpandToIncludeAboveUpperBound() {
		Range expected = new Range(-1, 2);
		Range result = Range.expandToInclude(testRange1, 2);
		assertEquals("Should get Range(-1, 2)", expected, result);
	}
	
	@Test
	public void testExpandToIncludeWithinRange() {
		Range result = Range.expandToInclude(testRange1, 0);
		assertEquals("Should get Range(-1, 1)", testRange1, result);
	}
	
	/* expand() tests */
	
	@Test(expected=IllegalArgumentException.class) 
	public void testExpandNull() {
		Range.expand(null, -2, 2);
		fail("Test shouldn't get here.");
	}
	
	@Test
	public void testExpand() {
		Range result = Range.expand(new Range(2, 6), 0.25, .5);
		Range expected = new Range(1, 8);
		assertEquals("Should get Range(1, 8)", expected, result);
	}
	
	/* shift(base, delta) tests */
	
	@Test
	public void testShiftPositive() {
		Range expected = new Range(0, 2);
		Range result = Range.shift(testRange1, 1);
		assertEquals("Should have range(0, 2)", expected, result);
	}
	
	@Test
	public void testShiftNegative() {
		Range expected = new Range(-2, 0);
		Range result = Range.shift(testRange1, -1);
		assertEquals("Should have range(-2, 0)", expected, result);
	}
	
	/* shift(base, delta, allowZeroCrossing) tests */
	
	@Test
	public void testShiftZeroCrossingPositive() {
		Range expected = new Range(1, 3);
		Range result = Range.shift(testRange1, 2, true);
		assertEquals("Should have range(1, 3)", expected, result);
	}
	
	@Test
	public void testShiftZeroCrossingNegative() {
		Range expected = new Range(-3, -1);
		Range result = Range.shift(testRange1, -2, true);
		assertEquals("Should have range(-3, -1)", expected, result);
	}
	
	/* shiftWithNoZeroCrossing() tests */
	
	@Test
	public void testShiftWithNoZeroCrossing() {
		Range expected = new Range(-1, -1);
		Range result = Range.shift(new Range(0, 0), -1, false);
		assertEquals("Should have range(-1, -1)", expected, result);
	}
	
	/* equals() tests */
	
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
	
	/* hashCode() tests */
	
	@Test
	public void testHashCode() {
		int lower = -1, upper = 1, expected;
        long temp;
        temp = Double.doubleToLongBits(lower);
        expected = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(upper);
        expected = 29 * expected + (int) (temp ^ (temp >>> 32));
        
        int result = testRange1.hashCode();
        assertEquals("Should be equal.", expected, result);
	}
	
	/* toString() tests */
	
	@Test
	public void testToString() {
		String expected = "Range[-1.0,1.0]";
		String result = testRange1.toString();
		assertEquals("Strings should match", expected, result);
	}
}
