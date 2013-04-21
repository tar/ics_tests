package utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

/**
 * Class provides functionality to encrypt and decrypt data with string key
 * Uses AES encryption mechanism
 * 
 * @author <a href="mailto:lukan@itcwin.com">Lukashin Anton</a>
 *
 */
public class AESCipher {
	private final static Logger LOGGER = Logger.getLogger(AESCipher.class);

	private Cipher ecipher;
 	private Cipher dcipher;
	
	public Cipher getEcipher() {
		return ecipher;
	}

	public Cipher getDcipher() {
		return dcipher;
	}
	// Iteration count
    int iterationCount = 16;
    public AESCipher(String pass){
    	SecretKeySpec secretKeySpec = new SecretKeySpec(Arrays.copyOf(pass.getBytes(), iterationCount), "AES");
    	try {
			ecipher=Cipher.getInstance("AES");
			dcipher=Cipher.getInstance("AES");
			ecipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			dcipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e);
		} catch (NoSuchPaddingException e) {
			LOGGER.error(e);
		} catch (InvalidKeyException e) {
			LOGGER.error(e);
		}
    }
    
    public void init(String pass){
    	SecretKeySpec secretKeySpec = new SecretKeySpec(Arrays.copyOf(pass.getBytes(), iterationCount), "AES");
    	try {
			ecipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			dcipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		} catch (InvalidKeyException e) {
			LOGGER.error(e);
		}
    }
    
    public byte[] encrypt(byte[] src) throws IllegalBlockSizeException, BadPaddingException{
    	return ecipher.doFinal(src);
    }
    public byte[] decrypt(byte[] src) throws IllegalBlockSizeException, BadPaddingException{
    	return dcipher.doFinal(src);
    }
}
