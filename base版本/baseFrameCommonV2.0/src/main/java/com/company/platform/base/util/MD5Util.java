/******************************************************************
 *
 *    Package:     com.company.platform.base.util
 *
 *    Filename:    MD5Util.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2017
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     zhengjn
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月27日 下午5:33:58
 *
 *    Revision:
 *
 *    2017年3月27日 下午5:33:58
 *        - first revision
 *
 *****************************************************************/
package com.company.platform.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: MD5Util
 * @Description: TODO(MD5加密工具)
 * @author zhengjn
 * @date 2017年10月1日 下午5:06:52
 * 
 */
public class MD5Util {

	private static final String SALT = "HXWcjvQWVG1wI4FQBLZpQ3pWj48AV63d";

	public static String encode(String password) {
		return processEncode(password);
	}

	public static String processEncode(String password) {
		String saltedPass = mergePasswordAndSalt(password, SALT);
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("md5");
			byte[] digest = messageDigest.digest(UTF8Util.encode(saltedPass));
			return new String(HexUtil.encode(digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt32(String password) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("md5");
			byte[] digest = messageDigest.digest(UTF8Util.encode(password));
			return new String(HexUtil.encode(digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt16(String password) {
		String mw = encrypt32(password);
		if (mw != null && mw.length() > 24) {
			return mw.substring(8, 24);
		} else {
			return mw;
		}
	}

	public static String mergePasswordAndSalt(String password, Object salt) {
		if (password == null) {
			password = "";
		}
		if ((salt == null) || "".equals(salt)) {
			return password;
		} else {
			return password + "{" + salt.toString() + "}";
		}
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.encode("yszyzh"));
	}
}
