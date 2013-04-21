package ru.spbstu.qsp.codereview;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.math.BigInteger;
import java.nio.channels.FileChannel;

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
import java.util.StringTokenizer;
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
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("^-?[0-9]+(\\.[0-9]+)?$");
    private static final Pattern INTEGER_PATTERN = Pattern.compile("^-?[0-9]+([0-9]+)?$");

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
        try {
            return byteToHexString(MessageDigest.getInstance("MD5").digest(bytes));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error making MD5 hash: " + e.getMessage(), e);
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
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(src.getBytes(), 0, src.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error making MD5 hash: " + e.getMessage(), e);
        }
    }

    /**
     * Builds string presentation of bytes
     * 
     * @param hash
     *            source bytes
     * @return hex string
     */
    public static String byteToHexString(byte[] hash) {
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
    public static <T> Collection<String> strings(Collection<T> items, Collection<String> result) {
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
    public static <E extends Comparable<?>> SortedSet<E> sort(Collection<E> items) {
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
        } catch (RuntimeException e) {
            return null;
        }
        return year;
    }

    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large(more tnan 2.1 gb)" + file.getName());
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[is.available()];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public static String trimMy(String str) {
        StringTokenizer tokenizer = new StringTokenizer(str, "\n");
        StringBuilder builder = new StringBuilder();
        while (tokenizer.hasMoreTokens()) {
            builder.append(tokenizer.nextToken().trim()).append('\n');
        }
        return builder.toString();
    }

    public static String trimUrl(String str) {
        str = str.trim().toLowerCase();
        int length = str.length();
        int i = 0;
        while (i < length && BAD_URL_CHARS.contains(str.charAt(i))) {
            ++i;
        }

        int j = length - 1;
        while (j >= 0 && BAD_URL_CHARS.contains(str.charAt(j))) {
            --j;
        }

        if (i < j && (i > 0 || j < length - 1)) {
            return str.substring(i, j + 1);
        } else {
            return str;
        }
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

    public static void saveTextToFile(String path, String text) {
        try {
            FileWriter fw = new FileWriter(new File(path), true);
            fw.write(text);
            fw.write("\r" + "\n");
            fw.close();
        } catch (IOException e) {
            _logger.error(e);
        }
    }

    public static void deleteFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            _logger.warn("Could not delete, because not exists: " + path);
            return;
        }
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteFolder(child.getPath());
            }
        }
        if (!file.delete()) {
            _logger.warn("Could not delete: " + path);
            return;
        }
    }

    public static String cleanHRef(String query) {
        String trimQuery = query.replace(" ", "").toLowerCase();
        if (trimQuery.contains("<ahref=") && trimQuery.contains("</a>")) {
            StringBuilder builder = new StringBuilder();
            boolean isAdd = true;
            for (int i = 0; i < query.length(); ++i) {
                char c = query.charAt(i);
                if (c == '<') {
                    isAdd = false;
                    continue;
                }
                if (c == '>') {
                    isAdd = true;
                    continue;
                }
                if (isAdd) {
                    builder.append(c);
                }
            }
            return builder.toString();
        }
        return query;
    }

    /**
     * method for coping files
     * 
     * @param in
     *            - file from which to copy
     * @param out
     *            - file in which copies
     * @throws IOException
     */
    public static void copyFile(File in, File out) throws IOException {
        FileChannel inChannel = new FileInputStream(in).getChannel();
        FileChannel outChannel = new FileOutputStream(out).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            throw e;
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }

    public static void copyDir(File in, File out) throws IOException {
        out.mkdir();
        for (File child : in.listFiles()) {
            if (child.isFile()) {
                copyFile(child, new File(out.getAbsolutePath() + "/" + child.getName()));
            }
            if (child.isDirectory()) {
                copyDir(child, new File(out.getAbsolutePath() + "/" + child.getName()));
            }
        }
    }

    public static void runProcess(String[] args, File folder) {
        try {
            Process process = Runtime.getRuntime().exec(args, null, folder);
            Thread thread = readProcessOutput(process.getInputStream());
            Thread threadError = readProcessErrorOutput(process.getErrorStream());
            thread.join();
            threadError.join();
            int status = process.waitFor();
            if (status == 0) {
                _logger.info("PROCESS STATUS=" + status);
            } else {
                _logger.error("Process exit with status " + status + " !!!");
            }
        } catch (IOException e) {
            _logger.error(e);
        } catch (InterruptedException e) {
            _logger.error(e);
        }
    }

    public static Thread readProcessOutput(final InputStream is) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                try {
                    while ((line = br.readLine()) != null) {
                        _logger.info(line);
                    }
                } catch (IOException e) {
                    _logger.error("IOException while reading process output", e);
                } finally {
                    try {
                        br.close();
                        isr.close();
                        is.close();
                    } catch (IOException e) {
                        _logger.error("IOException while closing reading process output", e);
                    }
                }
            }
        });
        thread.start();
        return thread;
    }

    public static Thread readProcessErrorOutput(final InputStream is) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                try {
                    while ((line = br.readLine()) != null) {
                        if (line.contains("BouncyCastleProvider")) {
                            continue;
                        }
                        if (line.startsWith("Error") || line.startsWith("\"Error")) {
                            // _logger.info(line);
                        } else {
                            _logger.error(line);
                        }
                    }
                } catch (IOException e) {
                    _logger.error("IOException while reading error process output", e);
                } finally {
                    try {
                        br.close();
                        isr.close();
                        is.close();
                    } catch (IOException e) {
                        _logger.error("IOException while closing reading error process output", e);
                    }
                }
            }
        });
        thread.start();
        return thread;
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
        if ((text == null) || (text.equals("")))
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
        if ((date == null))
            return null;
        Locale locale = Locale.ENGLISH;
        DateFormat dateFormat = new SimpleDateFormat(template, locale);
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String strOrEmpty(String str) {
        if (str == null)
            return "";
        return str.equals("null") ? "" : str;
    }

    public static String strOrNull(String str) {
        if (str == null)
            return null;
        return str.equals("null") ? null : str;
    }

    public static boolean strToBoolean(String str) {
        if (str != null && str.equals("true"))
            return true;
        return false;

    }

    public static String readTextFromInputStream(InputStream in) {
        StringBuilder text = new StringBuilder();
        InputStreamReader inReader = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inReader);
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                text.append(line).append('\n');
            }
            reader.close();
            inReader.close();
        } catch (IOException e) {
            _logger.error(e);
        }
        return text.toString();
    }

    public static String loadTextFromFileWithResourseAsStream(String path) {
        try {
            InputStream input = Util.class.getResourceAsStream(path);
            String text = readTextFromInputStream(input);
            input.close();
            return text;
        } catch (FileNotFoundException e) {
            _logger.error(e);
        } catch (IOException e) {
            _logger.error(e);
        }
        return null;
    }
}
