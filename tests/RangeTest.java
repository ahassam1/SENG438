package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest{

	private static Range exampleRange;
	@BeforeClass public static void setUpBeforeClass() throws Exception{
		exampleRange = new Range(-2,2);
	}


	@Test
	public void nominalRangeContains() {
		assertEquals("0 should be included in the range of -2,2"
						,true,exampleRange.contains(0));
	}
	
	@Test
	public void lowerBoundRangeContains() {
		assertEquals("-2 should be included in the range of -2,2"
						,true,exampleRange.contains(-2));
	}
	
	@Test
	public void aboveLowerBoundRangeContains() {
		assertEquals("-1.999 should be included in the range of -2,2"
						,true,exampleRange.contains(-1.999));
	}
	
	@Test
	public void belowLowerBoundRangeContains() {
		assertEquals("-2.001 should not be included in the range of -2,2"
						,false,exampleRange.contains(-2.001));
	}
	
	@Test
	public void upperBoundRangeContains() {
		assertEquals("2 should be included in the range of -2,2"
						,true,exampleRange.contains(2));
	}
	
	@Test
	public void belowUpperBoundRangeContains() {
		assertEquals("1.999 should be included in the range of -2,2"
						,true,exampleRange.contains(1.999));
	}
	
	@Test
	public void aboveUpperBoundRangeContains() {
		assertEquals("2.001 should not be included in the range of -2,2"
						,false,exampleRange.contains(2.001));
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		
	}

}
