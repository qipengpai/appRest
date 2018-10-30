/******************************************************************
 *
 *    Package:     com.company.platform.base.util
 *
 *    Filename:    AesSecret.java
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

import com.company.platform.base.util.cipher.ISecret;
import com.company.platform.base.util.cipher.SecretContext;

/** 
* @ClassName: AesSecret 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月24日 上午9:07:51 
*  
*/
public class AesSecret implements ISecret {


	/* (非 Javadoc) 
	* <p>Title: ancrypt</p> 
	* <p>Description: </p> 
	* @param key
	* @return 
	* @see com.company.platform.base.util.cipher.ISecret#ancrypt(java.lang.String) 
	*/
	//@Override
	public String ancrypt(SecretContext context) {
		// TODO Auto-generated method stub
		return "123456";
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
		// TODO Auto-generated method stub
		return "123456";
	}
}
