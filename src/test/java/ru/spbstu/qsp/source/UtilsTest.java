package ru.spbstu.qsp.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

public class UtilsTest extends TestCase {

	private static final Logger _logger = Logger.getLogger(UtilsTest.class);

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public UtilsTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(UtilsTest.class);
	}

	@org.junit.Test
	public void testIsEmpty() {
		_logger.info("---------------Test isEmpty()----------------");
		_logger.info("Test null string");
		assertTrue(Util.isEmpty(null));
		_logger.info("Test empty string");
		assertTrue(Util.isEmpty(""));
		_logger.info("Test spaced string");
		assertTrue(Util.isEmpty("   "));
		_logger.info("---------------------------------------------");
	}

	@org.junit.Test
	public void testTrimOrNull() {
		_logger.info("---------------Test trimOrNull()----------------");
		_logger.info("Test null string");
		assertNull(Util.trimOrNull(null));
		_logger.info("Test trim");
		assertTrue(Util.trimOrNull("test   ").indexOf(" ") == -1);
		_logger.info("Test spaced string");
		assertNull(Util.trimOrNull("   "));
		_logger.info("---------------------------------------------");
	}

	@org.junit.Test
	public void testMD5() {
		_logger.info("---------------Test MD5()----------------");
		_logger.info("Test null array");
		assertNull(Util.MD5(null));
		_logger.info("Test empty byte array");
		assertNull(Util.MD5(new byte[0]));
		byte[] firstArray = { 11, 15, 24, 33 };
		byte[] secondArray = { 11, 15, 24, 32 };
		_logger.info("Test on equal arrays");
		assertEquals(Util.MD5(firstArray), Util.MD5(firstArray));
		_logger.info("Test on different arrays");
		assertNotSame(Util.MD5(firstArray), Util.MD5(secondArray));
		_logger.info("---------------------------------------------");
	}

	@org.junit.Test
	public void testGetMD5() {
		_logger.info("---------------Test getMD5()----------------");
		_logger.info("Test null string");
		assertNull(Util.getMD5(null));
		_logger.info("Test empty string");
		assertNull(Util.getMD5(""));
		String str1 = "first test string";
		String str2 = "second test string";
		_logger.info("Test on equal strings");
		assertEquals(Util.getMD5(str1), Util.getMD5(str1));
		_logger.info("Test on different strings");
		assertNotSame(Util.getMD5(str1), Util.getMD5(str2));
		_logger.info("---------------------------------------------");
	}

	@org.junit.Test
	public void testStrings() {
		_logger.info("---------------Test strings()----------------");
		List<Integer> ints = new ArrayList<Integer>();
		Set<String> outs = new HashSet<String>();
		ints.add(1);
		ints.add(2);
		_logger.info("Test null collections");
		assertNull(Util.strings(null, null));
		_logger.info("Test null input collection");
		assertNull(Util.strings(null, outs));
		_logger.info("Test empty input collection");
		assertNotNull(Util.strings(new ArrayList<Integer>(), outs));
		_logger.info("Test null output collection");
		assertNull(Util.strings(ints, null));
		_logger.info("Test stringify collection");
		assertTrue(Util.strings(ints, outs).contains("1")
				&& Util.strings(ints, outs).contains("2"));
		_logger.info("---------------------------------------------");
	}

	@org.junit.Test
	public void testSort() {
		_logger.info("---------------Test sort()----------------");
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(2);
		ints.add(3);
		ints.add(1);
		_logger.info("Test null collection");
		assertNull(Util.sort(null));
		_logger.info("Test empty input collection");
		assertNotNull(Util.sort(new ArrayList<Integer>()));
		_logger.info("Test sort collection");
		SortedSet<Integer> result = Util.sort(ints);
		Integer[] resultAsArray = new Integer[result.size()];
		resultAsArray = result.toArray(resultAsArray);
		Integer[] sortedResultArray = Arrays.copyOf(resultAsArray,
				resultAsArray.length);
		Arrays.sort(sortedResultArray);
		assertTrue(Arrays.equals(resultAsArray, sortedResultArray));
		_logger.info("---------------------------------------------");
	}
	@org.junit.Test
	public void testStrToInt() {
		_logger.info("---------------Test strToInt()----------------");
		_logger.info("Test null string");
		assertNull(Util.strToInt(null));		
		_logger.info("Test empty string");
		assertNull(Util.strToInt(""));
		_logger.info("Test invalid string");
		assertNull(Util.strToInt("1aa"));
		_logger.info("Test str to int conversion");
		assertEquals(Integer.valueOf(1), Util.strToInt("1"));
		assertEquals(Integer.valueOf(-1), Util.strToInt("-1"));
		_logger.info("---------------------------------------------");
	}
	@org.junit.Test
	public void testParseYear() {
		_logger.info("---------------Test parseYear()----------------");
		_logger.info("Test null string");
		assertNull(Util.parseYear(null));		
		_logger.info("Test empty string");
		assertNull(Util.parseYear(""));
		_logger.info("Test invalid string");
		assertNull(Util.strToInt("1915aa"));
		_logger.info("Test str to int conversion");
		assertEquals(Integer.valueOf(2013), Util.parseYear("2013"));
		assertEquals(Integer.valueOf(-1), Util.parseYear("-2013"));
		_logger.info("---------------------------------------------");
	}
	@org.junit.Test
	public void testgetBytesFromFile() {
		_logger.info("---------------Test getBytesFromFile()----------------");
		_logger.info("Test null file");
		try {
			assertNull(Util.getBytesFromFile(null));
		} catch (IOException e) {
		}		
		_logger.info("Test empty string");
		assertNull(Util.parseYear(""));
		_logger.info("Test invalid string");
		assertNull(Util.strToInt("1915aa"));
		_logger.info("Test str to int conversion");
		assertEquals(Integer.valueOf(2013), Util.parseYear("2013"));
		assertEquals(Integer.valueOf(-1), Util.parseYear("-2013"));
		_logger.info("---------------------------------------------");
	}
}
