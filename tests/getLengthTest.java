package org.jfree.data.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.jfree.data.Range;

public class getLengthTest {
	
	private Range testRange1;
	private Range testRange2;
	private Range testRange3;
	private Range testRange4;
	
	@Before
	public void setUp() {
		
		//Testing Pass
		testRange1 = new Range(-1, 1);
		testRange2 = new Range(-1, 1);
		
		//Testing Fail
		testRange3 = new Range(-1, 1);
		testRange4 = new Range(-1, 2);
	}
	
	@Test
	public void testLengthTrue() {
		assertEquals(testRange1.getLength(), testRange2.getLength(), 0);
	}
	
	@Test
	public void testLengthFalse() {
		assertNotSame(testRange3.getLength(), testRange4.getLength());
	}
	
	@Test
	public void testLengthNull() {
		assertNotSame(testRange1.getLength(), null);
	}

}
