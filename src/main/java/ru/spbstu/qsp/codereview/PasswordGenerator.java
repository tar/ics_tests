package ru.spbstu.qsp.codereview;


import java.security.SecureRandom;

/**
 * Util class to generate random passwords
 * 
 * @author <a href="mailto:lukan@itcwin.com">Lukashin Antons</a>
 */

public class PasswordGenerator {

	private static volatile PasswordGenerator instance;
	private SecureRandom random;
	private char[] acceptableChars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'I', 'H', 'J', 'K', 'M',
			'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'0', '_', '@', '+', '-', '#' };

	private PasswordGenerator() {
		random = new SecureRandom();
	}

	public String getNewPass(int pswLen) {
		random = new SecureRandom();
		StringBuilder pass = new StringBuilder();
		for (int i = 0; i < pswLen; i++) {
			pass.append(acceptableChars[random.nextInt(acceptableChars.length)]);
		}
		return pass.toString();
	}

	public static PasswordGenerator getInstance() {

		if (instance == null) {
			synchronized (PasswordGenerator.class) {
				if (instance == null) {
					instance = new PasswordGenerator();
				}
			}
		}

		return instance;
	}

}
