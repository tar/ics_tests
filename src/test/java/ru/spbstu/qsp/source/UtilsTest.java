package ru.spbstu.qsp.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
		byte[] fileData = "Test file reading data".getBytes(Charset
				.forName("UTF-8"));
		File fileToRead = new File("file.test");
		try {
			_logger.info("Test read file data");
			FileChannel ch = new FileInputStream(fileToRead).getChannel();
			ByteBuffer bb = ByteBuffer.wrap(fileData);
			ch.write(bb);
			ch.close();
			byte[] result = Util.getBytesFromFile(fileToRead);
			assertTrue(Arrays.equals(fileData, result));
			_logger.info("Test read directory");
			File directory = new File("/");
			assertNull(Util.getBytesFromFile(directory));
			fileToRead.delete();
		} catch (FileNotFoundException e) {
			_logger.error(e.getMessage());
		} catch (IOException e) {
			_logger.error(e.getMessage());
		}
		_logger.info("---------------------------------------------");
	}

	@org.junit.Test
	public void testReplaceQuotes() {
		_logger.info("---------------Test replace quotes()----------------");
		_logger.info("Test null string");
		assertNull(Util.replaceQuotes(null));
		_logger.info("Test empty string");
		assertTrue(Util.replaceQuotes("").isEmpty());
		_logger.info("Test string without quotes");
		String test = "test";
		assertEquals(test, Util.replaceQuotes(test));
		_logger.info("Test replace quotes");
		String result = Util.replaceQuotes("\"test\"");
		assertTrue(result.startsWith("«") && result.endsWith("»"));
		_logger.info("---------------------------------------------");
	}

	@org.junit.Test
	public void testIsDouble() {
		_logger.info("---------------Test isDouble()----------------");
		_logger.info("Test null string");
		assertFalse(Util.isDouble(null));
		_logger.info("Test empty string");
		assertFalse(Util.isDouble(""));
		_logger.info("Test invalid double value");
		assertFalse(Util.isDouble("1252.a"));
		_logger.info("Test valid double value");
		assertTrue(Util.isDouble("12.12"));
		_logger.info("---------------------------------------------");
	}

	@org.junit.Test
	public void testIsInteger() {
		_logger.info("---------------Test isInteger()----------------");
		_logger.info("Test null string");
		assertFalse(Util.isInteger(null));
		_logger.info("Test empty string");
		assertFalse(Util.isInteger(""));
		_logger.info("Test invalid integer value");
		assertFalse(Util.isInteger("1a"));
		_logger.info("Test double value");
		assertFalse(Util.isInteger("1.1"));
		_logger.info("Test valid integer value");
		assertTrue(Util.isInteger("12"));
		_logger.info("---------------------------------------------");
	}

	@org.junit.Test
	public void testIsNullOrEmpty() {
		_logger.info("---------------Test isNullOrEmpty()----------------");
		_logger.info("Test null string");
		assertTrue(Util.isNullOrEmpty(null));
		_logger.info("Test empty string");
		assertTrue(Util.isNullOrEmpty(""));
		_logger.info("Test spaced string");
		assertFalse(Util.isNullOrEmpty("   "));
		_logger.info("---------------------------------------------");
	}

	@org.junit.Test
	public void testConverStringtoDate() {
		_logger.info("---------------Test converStringtoDate()----------------");
		_logger.info("Test null date value");
		assertNull(Util.convertStringToDate(null, "template"));
		_logger.info("Test empty date value");
		assertNull(Util.convertStringToDate("", "template"));
		_logger.info("Test null template value");
		assertNull(Util.convertStringToDate("date", null));
		_logger.info("Test empty template value");
		assertNull(Util.convertStringToDate("date", ""));
		_logger.info("Test valid date");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		assertEquals(sdf.format(date), sdf.format(Util.convertStringToDate(
				sdf.format(date), "dd-mm-yyyy")));
		_logger.info("---------------------------------------------");
	}

	@org.junit.Test
	public void testConvertDateToString() {
		_logger.info("---------------Test convertDateToString()----------------");
		_logger.info("Test null date value");
		assertNull(Util.convertDateToString(null, "template"));
		_logger.info("Test null template value");
		assertNull(Util.convertDateToString(new Date(), null));
		_logger.info("Test empty template value");
		assertNull(Util.convertDateToString(new Date(), ""));
		_logger.info("Test valid date");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		assertEquals(sdf.format(date),
				Util.convertDateToString(date, "dd-mm-yyyy"));
		_logger.info("---------------------------------------------");
	}
}
