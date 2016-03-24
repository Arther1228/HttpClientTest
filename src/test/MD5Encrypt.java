package test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Encrypt {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static MD5Encrypt instance = new MD5Encrypt();

	private MD5Encrypt() {
	}

	public static MD5Encrypt getInstance() {
		return instance;
	}

	public String encrypt(String message) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(message.getBytes());
			byte[] result = messageDigest.digest();
			return toHex(result);
		} catch (NoSuchAlgorithmException e) {
			logger.debug("不支持此编码类型", e);
		}
		return null;
	}

	/**
	 * 16位编码
	 */
	public String encrypt16(String message) {
		return encrypt(message).substring(8, 24);
	}

	/**
	 * 将16位byte[] 转换为32位String
	 * */
	private String toHex(byte buffer[]) {
		StringBuffer stringBuffer = new StringBuffer(buffer.length * 2);

		for (int i = 0; i < buffer.length; i++) {
			stringBuffer.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			stringBuffer.append(Character.forDigit(buffer[i] & 15, 16));
		}
		return stringBuffer.toString();
	}
}
