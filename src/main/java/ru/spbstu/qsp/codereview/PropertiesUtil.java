package ru.spbstu.qsp.codereview;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Class for loading snapshot's properties from file
 * 
 * @author amoroz
 * 
 */
public class PropertiesUtil {
	private final static Logger LOGGER = Logger.getLogger(PropertiesUtil.class);

	private static PropertiesUtil instance;

	/**
	 * Creates instance of singleton {@link PropertiesUtil} object instance or returns existing one.
	 * 
	 * @return Singleton {@link PropertiesUtil} object instance.
	 */
	public static PropertiesUtil getInstance() {
		if (instance == null) {
			synchronized (PropertiesUtil.class) {
				instance = new PropertiesUtil();
			}
		}
		return instance;
	}

	public Properties getProp(String fileName) {
		Properties props = new Properties();
		try {
			InputStream is = PropertiesUtil.class.getResourceAsStream("/" + fileName);
			props.load(is);
			is.close();
		} catch (IOException e) {
			LOGGER.error(e);
			return null;
		}
		return props;
	}
}