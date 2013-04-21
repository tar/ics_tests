package ru.spbstu.qsp.source;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MathUtilsTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public MathUtilsTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MathUtilsTest.class);
	}

	@org.junit.Test
	public void testFoo() {
		assertTrue(true);
	}
}
