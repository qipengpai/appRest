package com.company.platform.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;



/**
 * 人保工具信息
 * @author Administrator
 *
 */
public class RenBaoUtil {
	/**
	 * AES加密
	 * @param content
	 * @param key
	 * @return
	 */
	public static String enCode(String content,String key){
		//key校验
		if(!StringUtils.isBlank(key)&&key.length()!=16)return null;
		try {
			byte[] raw = key.getBytes();
			//生成秘钥
			SecretKeySpec skey = new SecretKeySpec(raw, "AES");
			Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			byte[] byte_content = content.getBytes("UTF-8");
			byte[] encode_content = cipher.doFinal(byte_content);
			return  Base64.encodeBase64String(encode_content);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	/**
	 * 32位大写MD5转换
	 * @param plainText
	 * @return
	 */
    public static String enCodeMD5(String plainText) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = plainText.getBytes("UTF-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
   }
    
    /**
     * Map 转换为URl格式参数
     * @param map
     * @return
     */
 	public static String getUrlParamsByMap(Map<String, String> map) {
 		if (map == null) {
 			return "";
 		}
 		StringBuffer sb = new StringBuffer();
 		for (Map.Entry<String, String> entry : map.entrySet()) {
 			sb.append(entry.getKey() + "=" + entry.getValue());
 			sb.append("&");
 		}
 		String s = sb.toString();
 		if (s.endsWith("&")) {
 			s = StringUtils.substringBeforeLast(s, "&");
 		}
 		return s;
 	}

 	/**
 	 * 请求参数转换
 	 * @param param
 	 * @param paccount
 	 * @param scode
 	 */
 	public static void coverKeyValue(LinkedHashMap<String,String> param,String paccount,String scode){
 		StringBuffer  sb = new StringBuffer("").append("paccount").append(paccount).append(scode);
 		 ListIterator<Map.Entry<String,String>> i=new ArrayList<Map.Entry<String,String>>(param.entrySet()).listIterator(param.size());  
         while(i.hasPrevious()) {  
             Map.Entry<String, String> entry=i.previous();  
             sb.insert(0, entry.getValue());
             sb.insert(0,entry.getKey());  
       }
       param.put("paccount",paccount);
       param.put("psign", enCodeMD5(sb.toString()));
 	}
}
