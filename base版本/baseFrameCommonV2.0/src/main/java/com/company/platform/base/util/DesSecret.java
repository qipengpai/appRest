/******************************************************************
 *
 *    Package:     com.company.platform.base.util
 *
 *    Filename:    DesSecret.java
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

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.platform.base.baseenum.GLOBALCONFIG;
import com.company.platform.base.util.cipher.ISecret;
import com.company.platform.base.util.cipher.SecretContext;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
* @ClassName: DesSecret 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月24日 上午9:08:35 
*  
*/
@SuppressWarnings("all")
public class DesSecret implements ISecret {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(DesSecret.class);

	private byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

	private final String DES = "DES";

	/* (非 Javadoc) 
	* <p>Title: ancrypt</p> 
	* <p>Description: </p> 
	* @param key
	* @return 
	* @see com.company.platform.base.util.cipher.ISecret#ancrypt(java.lang.String) 
	*/
    @Override
	public String ancrypt(SecretContext context) {
		byte[] bt = null;
		;
		try {
			bt = encrypt(context.getKeyMessage().getBytes(), GLOBALCONFIG.APPKEY.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strs = new BASE64Encoder().encode(bt);
		return strs;
	}

	/* (非 Javadoc) 
	* <p>Title: decrypt</p> 
	* <p>Description: </p> 
	* @param key
	* @return 
	* @see com.company.platform.base.util.cipher.ISecret#decrypt(java.lang.String) 
	*/
	@Override
	public String decrypt(SecretContext context) {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] buf = null;
		byte[] bt = null;
		try {
			buf = decoder.decodeBuffer(context.getKeyMessage());
			bt = decrypt(buf, GLOBALCONFIG.APPKEY.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(bt);
	}

	/** 
	 * Description 根据键值进行加密 
	 * @param data 
	 * @param key  加密键byte数组 
	 * @return 
	 * @throws Exception 
	 */
	private byte[] encrypt(byte[] data, byte[] key) throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec keyS = new SecretKeySpec(key, DES);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, keyS, zeroIv);
		byte[] encryptedData = cipher.doFinal(data);
		return encryptedData;
	}

	/** 
	 * Description 根据键值进行解密 
	 * @param data 
	 * @param key  加密键byte数组 
	 * @return 
	 * @throws Exception 
	 */
	private byte[] decrypt(byte[] data, byte[] key) throws Exception {
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		SecretKeySpec keyS = new SecretKeySpec(key, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, keyS, zeroIv);
		byte[] descryptedData = cipher.doFinal(data);
		return descryptedData;
	}
	
	public static void main(String []s) throws Exception{
	    DesSecret desSecret = new DesSecret();
	    SecretContext context = new SecretContext();
	    context.setKeyMessage("gNabvKievL8=");
	    System.out.println(desSecret.decrypt(context));
	}
}