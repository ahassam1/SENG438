package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.Test;
import org.junit.Before;


public class equalsTest {
	
	private Range testRange1;
	private Range testRange2;
	private Range testRange3;
	private Range testRange4;
	private Range temp1;
	private Range temp2;
	
	@Before
	public void setUp() {
		
		//Comparing two Equal Ranges
		testRange1 = new Range(-1, 1);
		testRange2 = new Range(-1, 1);
		
		//Comparing two Different Ranges;
		testRange3 = new Range(-1, 1);
		testRange4 = new Range(-2, 2);
		
		//Interesting Case Found
		temp1 = new Range(-1, 1);
		temp2 = new Range(-1, 2);
	}
	
	@Test
	public void testEqualTrue() {
		assertTrue(testRange1.equals(testRange2));
	}
	
	@Test 
	public void testEqualFalse() {
		assertFalse(testRange3.equals(testRange4));
		//assertFalse(temp1.equals(temp2));
	}
	
	@Test
	public void testEqualSelf() {
		assertTrue(testRange1.equals(testRange1));
	}
	
	@Test
	public void testEqualNull() {
		assertFalse(testRange1.equals(null));
	}

}
