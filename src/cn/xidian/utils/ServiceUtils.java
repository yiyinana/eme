package cn.xidian.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class ServiceUtils {

	public static String md5(String message) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("md5");
		byte[] md5 = md.digest(message.getBytes());
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(md5);
	}
}
