/******************************************************************
 *
 *    Package:     com.company.platform.base.util.cipher
 *
 *    Filename:    ISecret.java
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
package com.company.platform.base.util.cipher;

/** 
* @ClassName: ISecret 
* @Description: TODO(加密、解密接口) 
* @author zhengjn 
* @date 2017年10月24日 上午9:01:44 
*  
*/
public interface ISecret {

	/** 
	* @Title: ancrypt 
	* @Description: TODO(加密) 
	* @param @param key
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String ancrypt(SecretContext context);
	
	/** 
	* @Title: decrypt 
	* @Description: TODO(解密) 
	* @param @param key
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String decrypt(SecretContext context);
}
