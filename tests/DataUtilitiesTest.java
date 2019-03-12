package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest {
	// testCalculateColumnTotal() values
	private Values2D columnValues;
	private Mockery columnMockingContext;
	
	// calculateRowTotal() values
	private Values2D rowValues;
	private Mockery rowMockingContext;
	
	// createNumberArray() values
	private Number[] arrayRealDataTest;
	private Number[] arrayEmptyDataTest;
	private Number[] arrayExpected = {0, 1, 2, 3, 4, 5};
	
	private double arrayData[] = {0, 1, 2, 3, 4, 5};
	private double arrayEmptyData[] = {};
	private double arrayNullData[];
	
	private Number[][] array2DRealDataTest;
	private Number[][] array2DEmptyDataTest;
	private Number array2DExpected[][] = {{0.0, 1.0, 0.0},
										  {1.0, 0.0, 1.0},
										  {0.0, 1.0, 0.0}};
	
	// createNumberArray2D() values
	private double array2DData[][] = {{0, 1, 0},
							  		  {1, 0, 1},
							  		  {0, 1, 0}};
	private double array2DEmptyData[][] = {};
	private double array2DNullData[][];
	
	// getCumulativePercentages() values
	private KeyedValues cumulativeValues;
	private KeyedValues cumulativeEmpty;
	
	private Mockery cumulativeMockingContext;
	private Mockery cumulativeEmptyContext;
	
	@Before
	public void setup() throws Exception{
		columnMockingContext = new Mockery();
		columnValues = columnMockingContext.mock(Values2D.class);
		columnMockingContext.checking(new Expectations() {
			{
				allowing(columnValues).getRowCount();
				will(returnValue(3));
				allowing(columnValues).getColumnCount();
				will(returnValue(3));
				//column 0
				allowing(columnValues).getValue(0,0);
				will(returnValue(4));
				allowing(columnValues).getValue(1,0);
				will(returnValue(10));
				allowing(columnValues).getValue(2,0);
				will(returnValue(6));
				//column 1
				allowing(columnValues).getValue(0,1);
				will(returnValue(10));
				allowing(columnValues).getValue(1,1);
				will(returnValue(10));
				allowing(columnValues).getValue(2,1);
				will(returnValue(10));
				//column 2
				allowing(columnValues).getValue(0,2);
				will(returnValue(2));
				allowing(columnValues).getValue(1,2);
				will(returnValue(3));
				allowing(columnValues).getValue(2,2);
				will(returnValue(4));
				//column -1 (testing exterior boundaries)
				allowing(columnValues).getValue(0,-1);
				will(returnValue(1));
				allowing(columnValues).getValue(1,-1);
				will(returnValue(1));
				allowing(columnValues).getValue(2,-1);
				will(returnValue(1));
				//column 3 (testing exterior boundaries)
				allowing(columnValues).getValue(0,3);
				will(returnValue(4));
				allowing(columnValues).getValue(1,3);
				will(returnValue(4));
				allowing(columnValues).getValue(2,3);
				will(returnValue(4));
			}
		});
		
		rowMockingContext = new Mockery();
		rowValues = rowMockingContext.mock(Values2D.class);
		rowMockingContext.checking(new Expectations() {
			{
				allowing(rowValues).getRowCount();
				will(returnValue(3));
				allowing(rowValues).getColumnCount();
				will(returnValue(3));
				//row 0
				allowing(rowValues).getValue(0,0);
				will(returnValue(4));
				allowing(rowValues).getValue(0,1);
				will(returnValue(10));
				allowing(rowValues).getValue(0,2);
				will(returnValue(6));
				//row 1
				allowing(rowValues).getValue(1,0);
				will(returnValue(10));
				allowing(rowValues).getValue(1,1);
				will(returnValue(10));
				allowing(rowValues).getValue(1,2);
				will(returnValue(10));
				//row 2
				allowing(rowValues).getValue(2,0);
				will(returnValue(2));
				allowing(rowValues).getValue(2,1);
				will(returnValue(3));
				allowing(rowValues).getValue(2,2);
				will(returnValue(4));
				//row -1 (testing exterior boundaries)
				allowing(rowValues).getValue(-1,0);
				will(returnValue(1));
				allowing(rowValues).getValue(-1,1);
				will(returnValue(1));
				allowing(rowValues).getValue(-1,2);
				will(returnValue(1));
				//row 3 (testing exterior boundaries)
				allowing(rowValues).getValue(3,0);
				will(returnValue(4));
				allowing(rowValues).getValue(3,1);
				will(returnValue(4));
				allowing(rowValues).getValue(3,2);
				will(returnValue(4));
			}
		});
		
		arrayRealDataTest = DataUtilities.createNumberArray(arrayData);
		arrayEmptyDataTest = DataUtilities.createNumberArray(arrayEmptyData);
		
		array2DRealDataTest = DataUtilities.createNumberArray2D(array2DData);
		array2DEmptyDataTest = DataUtilities.createNumberArray2D(array2DEmptyData);
		
		cumulativeMockingContext = new Mockery();
		cumulativeValues = cumulativeMockingContext.mock(KeyedValues.class);
		cumulativeMockingContext.checking(new Expectations() {
			{
				allowing(cumulativeValues).getItemCount();
				will(returnValue(3));
				
				allowing(cumulativeValues).getKey(0);
				will(returnValue(0));
				allowing(cumulativeValues).getValue(0);
				will(returnValue(5));
				
				allowing(cumulativeValues).getKey(1);
				will(returnValue(1));
				allowing(cumulativeValues).getValue(1);
				will(returnValue(9));
				
				allowing(cumulativeValues).getKey(2);
				will(returnValue(2));
				allowing(cumulativeValues).getValue(2);
				will(returnValue(2));
			}
		});
		
		cumulativeEmptyContext = new Mockery();
		cumulativeEmpty = cumulativeEmptyContext.mock(KeyedValues.class);
		cumulativeEmptyContext.checking(new Expectations() {
			{
				allowing(cumulativeEmpty).getItemCount();
				will(returnValue(0));
			}
		});
	}
	
	/* testCalculateColumnTotal() tests */
	
	//Testing column 0's total (lower bound)
	@Test
	public void testCalculateColumnTotalColumn0() {
		double result = DataUtilities.calculateColumnTotal(columnValues, 0);
		assertEquals("Column 0 should have a total of 20", 20, result, 0.00001d);
	}
	
	//Testing column 1's total (a row in the center of the acceptable range)
	@Test
	public void testCalculateColumnTotalNominalColumn() {
		double result = DataUtilities.calculateColumnTotal(columnValues, 1);
		assertEquals("Column 1 should have a total of 30", 30, result, 0.00001d);
	}
	
	//Testing column -1's total, should return 0 since there is no column -1
	@Test
	public void testCalculateColumnTotalColumnMinus1() {
		double result = DataUtilities.calculateColumnTotal(columnValues, -1);
		assertEquals("Column -1 should return 0 since invalid input", 0, result, 0.00001d);
	}
	
	//Testing column 2's total(upper bound)
	@Test
	public void testCalculateColumnTotalColumn2() {
		double result = DataUtilities.calculateColumnTotal(columnValues, 2);
		assertEquals("Column 2 should have a total of 9", 9, result, 0.00001d);
	}
	//Testing column 3's total, should return 0 since there are only 3 columns in the mocked object
	@Test
	public void testCalculateColumnTotalColumn3() {
		double result = DataUtilities.calculateColumnTotal(columnValues, 3);
		assertEquals("Column 3 should have a total of 0 due to out of bounds(invalid input)", 0, result, 0.00001d);
	}
	
	//Testing an invalid values2D object
	@Test(expected=InvalidParameterException.class)
	public void testCalculateColumnTotalNullData() {
		DataUtilities.calculateColumnTotal(null, 0);
		fail("Test should not get to this point.");	
	}
	
	/* calculateRowTotal() tests */
	
	//Testing row 0's total (lower bound)
	@Test
	public void testCalculateRowTotalRow0() {
		double result = DataUtilities.calculateRowTotal(rowValues, 0);
		assertEquals("Row 0 should have a total of 20", 20, result, 0.00001d);
	}
	
	//Testing row 1's total (central value of allowed rows)
	@Test
	public void testCalculateRowTotalNominalRow() {
		double result = DataUtilities.calculateRowTotal(rowValues, 1);
		assertEquals("Row 1 should have a total of 30", 30, result, 0.00001d);
	}
	
	//Testing row -1's total (should return 0 since there is no row -1)
	@Test
	public void testCalculateRowTotalRowMinus1() {
		double result = DataUtilities.calculateRowTotal(rowValues, -1);
		assertEquals("Row -1 should return 0 since invalid input", 0, result, 0.00001d);
	}
	
	//Testing row 2's total (upper bound)
	@Test
	public void testCalculateRowTotalRow2() {
		double result = DataUtilities.calculateRowTotal(rowValues, 2);
		assertEquals("Row 2 should have a total of 9", 9, result, 0.00001d);
	}
	
	//Testing row 3's total (should return 0 since there is no row 3 in the given values2D object)
	@Test
	public void testCalculateRowTotalRow3() {
		double result = DataUtilities.calculateRowTotal(rowValues, 3);
		assertEquals("Row 3 should have a total of 0 due to out of bounds(invalid input)", 0, result, 0.00001d);
	}
	
	//Testing an invalid Values2D object
	@Test(expected=InvalidParameterException.class)
	public void testCalculateRowTotalRowNullData() {
		DataUtilities.calculateRowTotal(null, 0);
		fail("Test should not get to this point.");
	}
	
	/* createNumberArray() tests */
	
	@Test
	public void testCreateNumberArrayRealData() {
		assertNotNull(arrayRealDataTest);
	}
	
	@Test
	public void testCreateNumberArrayEmptyData() {
		assertNotNull(arrayEmptyDataTest);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCreateNumberArrayNullData() {
		DataUtilities.createNumberArray(arrayNullData);
		fail("Test should not get to this point.");
	}
	
	@Test
	public void testCreateNumberArrayExpectedArray() {
		boolean result = arrayExpected.equals(arrayRealDataTest);
		assertTrue(result);
	}
	
	/* createNumberArray2D() */
	
	@Test
	public void testCreateNumberArray2DRealData() {
		assertNotNull(array2DRealDataTest);
	}
	
	@Test
	public void testCreateNumberArray2DEmptyData() {
		assertNotNull(array2DEmptyDataTest);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCreateNumberArray2DNullData() {
		DataUtilities.createNumberArray2D(array2DNullData);
		fail("Test should not get to this point.");
	}
	
	@Test
	public void testCreateNumberArray2DExpectedArray() {
		boolean result = Arrays.equals(array2DExpected, array2DRealDataTest);
		assertTrue(result);
	}
	
	/* getCumulativePercentages() tests */
	@Test
	public void testGetCumulativePercentagesRealDataRange() {
		KeyedValues result = DataUtilities.getCumulativePercentages(cumulativeValues);
		for (int i = 0; i < result.getItemCount(); i++) {
			double r = (double) result.getValue(i);
			assertTrue("Values should be between 0 and 1.0", 0 <= r && r <= 1.0);
		}
	}
	
	@Test
	public void testGetCumulativePercentagesRealDataIncrease() {
		KeyedValues result = DataUtilities.getCumulativePercentages(cumulativeValues);
		for (int i = 0; i < result.getItemCount(); i++) {
			if (i > 0) {
				double r1 = (double) result.getValue(i);
				double r2 = (double) result.getValue(i-1);
				assertTrue("Values should be between increasing", r1 > r2);
			}
		}
	}
	
	@Test
	public void testGetCumulativePercentagesEmptyData() {
		KeyedValues result = DataUtilities.getCumulativePercentages(cumulativeEmpty);
		assertEquals(0, result.getItemCount());
	}
	
	@Test (expected = InvalidParameterException.class)
	public void testGetCumulativePercentagesNullData() {
		DataUtilities.getCumulativePercentages(null);
		fail("Test should not get to this point.");
	}
}
