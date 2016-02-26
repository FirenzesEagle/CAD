package com.siims.szb.vipcard.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 密码工具类
 * 
 * @author lihongchuan
 * 
 */
public class PasswordUtil {

	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des.toUpperCase();
	}

	/**
	 * 以指定的算法进行密码加密
	 * 
	 * @param password
	 *            String 原始密码
	 * @param algorithm
	 *            String 密码算法 MD5、SHA、SHA1等
	 * @return String 加密后的密码信息
	 */
	public static String encodePassword(String password, String algorithm) {

		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try {
			// first create an instance, given the provider
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			return password;
		}

		md.reset();

		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);

		// now calculate the hash
		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}
	
	public static String encrypt(String str, String enc) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = str.getBytes();
		try {
			if (enc == null || enc.equals("")) {
				enc = "MD5";
			}
			md = MessageDigest.getInstance(enc);
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	public static void main(String[] args) {
		System.out.println("base64-md5:" + new String(Base64.encodeBase64(DigestUtils.md5("px0909"), false)));
		System.out.println("rone[1]:" + PasswordUtil.encodePassword("123456", "MD5"));
		// System.out.println("encrypt[MD5]:"+PasswordUtil.encrypt("!nju^bhy@1(blog!@#$+~",
		// "MD5"));
		System.out.println("encrypt[MD5]:" + PasswordUtil.encrypt("123456", "MD5"));
		System.out.println("encrypt[MD5] Hex:" + PasswordUtil.encrypt("123456", "MD5").substring(8,24));
		System.out.println("encryptPassword[wei123456]:" + PasswordUtil.encrypt("wei123456", "SHA1"));
		System.out.println("encryptPassword[wei123456]:" + PasswordUtil.encrypt("wei123456", "SHA"));
	}
}
