package ru.spbstu.qsp.source;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MatrixOperationsTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public MatrixOperationsTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MatrixOperationsTest.class);
	}

	@org.junit.Test
	public void testFoo() {
		assertTrue(true);
	}
}
