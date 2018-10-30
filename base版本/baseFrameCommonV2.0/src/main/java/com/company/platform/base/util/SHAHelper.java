/******************************************************************
 *
 *    Package:     com.zkbr.platform.utils
 *
 *    Filename:    SHAHelper.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2016-2017
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     Administrator
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月29日 上午10:55:34
 *
 *    Revision:
 *
 *    2017年3月29日 上午10:55:34
 *        - first revision
 *
 *****************************************************************/
package com.company.platform.base.util;

import java.security.MessageDigest;

/**
 * @ClassName SHAHelper
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年3月29日 上午10:55:34
 * @version 1.0.0
 */
public class SHAHelper {

    public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes("utf-8"));
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String SHA(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA");
            digest.update(decript.getBytes("utf-8"));
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(SHA1("zhengjn"));
    }

}
