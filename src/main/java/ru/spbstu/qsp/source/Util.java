package ru.spbstu.qsp.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Supplementary class containing various non application specific utility
 * methods
 */
public final class Util {
	private static final Logger _logger = Logger.getLogger(Util.class);

	private static final Set<Character> BAD_URL_CHARS = new HashSet<Character>();
	private static final char OPEN_QUOTE = '«';
	private static final char CLOSE_QOUTE = '»';
	private static final Pattern DOUBLE_PATTERN = Pattern
			.compile("^-?[0-9]+(\\.[0-9]+)?$");
	private static final Pattern INTEGER_PATTERN = Pattern
			.compile("^-?[0-9]+([0-9]+)?$");

	static {
		BAD_URL_CHARS.add('.');
		BAD_URL_CHARS.add(',');
		BAD_URL_CHARS.add('(');
		BAD_URL_CHARS.add(')');
		BAD_URL_CHARS.add('{');
		BAD_URL_CHARS.add('}');
		BAD_URL_CHARS.add('[');
		BAD_URL_CHARS.add(']');
		BAD_URL_CHARS.add(':');
		BAD_URL_CHARS.add(';');
	}

	/** private constructor to prevent instantiation */
	private Util() {
	}

	/**
	 * Indicates if string is empty
	 * 
	 * @param string
	 *            String
	 * @return <code>false</code> if specified string is not <code>null</code>
	 *         and contains at least one non space character, <code>true</code>
	 *         otherwise
	 */
	public static boolean isEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}

	/**
	 * Method trims string and if trimmed string is empty then returns null
	 * 
	 * @param string
	 *            string to trim
	 * @return trimmed string if trimmed string is empty then returns null
	 */
	public static String trimOrNull(String string) {
		if (string != null) {
			string = string.trim();
			if (string.length() == 0) {
				string = null;
			}
		}
		return string;
	}

	/**
	 * MD5 sum counting
	 * 
	 * @param bytes
	 *            source bytes
	 * @return md5 sum
	 */
	public static String MD5(byte[] bytes) {
		if (bytes == null)
			return null;
		if (bytes.length == 0)
			return null;
		try {
			return byteToHexString(MessageDigest.getInstance("MD5").digest(
					bytes));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error making MD5 hash: "
					+ e.getMessage(), e);
		}
	}

	/**
	 * MD5 sum counting
	 * 
	 * @param String
	 *            source String
	 * @return md5 sum
	 */
	public static String getMD5(String src) {
		if (isEmpty(src))
			return null;
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(src.getBytes(), 0, src.length());
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error making MD5 hash: "
					+ e.getMessage(), e);
		}
	}

	/**
	 * Builds string presentation of bytes
	 * 
	 * @param hash
	 *            source bytes
	 * @return hex string
	 */
	private static String byteToHexString(byte[] hash) {
		StringBuilder buf = new StringBuilder(hash.length * 2);

		for (byte hashByte : hash) {
			if ((hashByte & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(hashByte & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * Builds collection of string presentations from collection of objects
	 * 
	 * @param <T>
	 *            type of source collection
	 * @param items
	 *            source collection
	 * @param result
	 *            result collection to fill
	 * @return filled result collection
	 */
	public static <T> Collection<String> strings(Collection<T> items,
			Collection<String> result) {
		if (items == null || result == null)
			return null;
		for (T string : items) {
			result.add(string.toString());
		}
		return result;
	}

	/**
	 * Builds Sorted set of collection with comparable object
	 * 
	 * @param <E>
	 *            type of object collection consists of
	 * @param items
	 *            source collection
	 * @return {@link SortedSet} with objects from given collection
	 */
	public static <E extends Comparable<?>> SortedSet<E> sort(
			Collection<E> items) {
		if (items == null)
			return null;
		TreeSet<E> result = new TreeSet<E>();
		for (E item : items) {
			result.add(item);
		}
		return result;
	}

	/**
	 * Convert string to an integer, in case of error returns null.
	 * 
	 * @param str
	 *            String
	 * 
	 * @return Integer
	 */
	public static Integer strToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}

	}

	/**
	 * Parse string contains year
	 * 
	 * @param str
	 *            String
	 * 
	 * @return Integer
	 */
	public static Integer parseYear(String str) {
		int year;
		try {
			year = Integer.parseInt(str);
			year = year < 0 ? -1 : year;
		} catch (NumberFormatException e) {
			return null;
		}
		return year;
	}

	public static byte[] getBytesFromFile(File file) throws IOException {
		if (file == null)
			return null;
		if (!file.isFile())
			return null;

		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			// File is too large
			throw new IOException("File is too large(more tnan 2.1 gb)"
					+ file.getName());
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[is.available()];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

	public static String replaceQuotes(String title) {
		if (title == null || !title.contains("\"")) {
			return title;
		}

		StringBuilder builder = new StringBuilder();
		int startIndex = 0;
		int quoteIndex = title.indexOf("\"", startIndex);
		int counter = 0;
		int lastQuoteIndex = quoteIndex;
		while (quoteIndex >= 0) {
			++counter;
			builder.append(title.substring(startIndex, quoteIndex));
			char quote = counter % 2 == 0 ? CLOSE_QOUTE : OPEN_QUOTE;
			builder.append(quote);
			startIndex = quoteIndex + 1;
			lastQuoteIndex = quoteIndex;
			quoteIndex = title.indexOf("\"", startIndex);
		}
		builder.append(title.substring(lastQuoteIndex + 1));
		return builder.toString();
	}

	public static boolean isDouble(String str) {
		if (isNullOrEmpty(str)) {
			return false;
		}
		return DOUBLE_PATTERN.matcher(str).matches();
	}

	public static boolean isInteger(String str) {
		if (isNullOrEmpty(str)) {
			return false;
		}
		return INTEGER_PATTERN.matcher(str).matches();
	}

	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		}
		if (str.isEmpty()) {
			return true;
		}
		return false;
	}

	public static Date convertStringToDate(String text, String template) {
		if (isNullOrEmpty(text) || isNullOrEmpty(template))
			return null;
		Locale locale = Locale.ENGLISH;
		DateFormat dateFormat = new SimpleDateFormat(template, locale);
		try {
			return dateFormat.parse(text);
		} catch (Exception e) {
			return null;
		}
	}

	public static String convertDateToString(Date date, String template) {
		if (date == null || isNullOrEmpty(template))
			return null;
		Locale locale = Locale.ENGLISH;
		DateFormat dateFormat = new SimpleDateFormat(template, locale);
		try {
			return dateFormat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

}
