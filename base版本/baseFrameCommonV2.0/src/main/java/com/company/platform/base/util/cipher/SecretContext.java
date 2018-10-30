/******************************************************************
 *
 *    Package:     com.company.platform.base.util.cipher
 *
 *    Filename:    SecretContext.java
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
* @ClassName: SecretContext 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月24日 上午9:04:31 
*  
*/
public class SecretContext {

	ISecret secret;

	String keyMessage;
	
	public SecretContext(){
		super();
	}

	public SecretContext(ISecret secret, String keyMessage) {
		this.secret = secret;
		this.keyMessage = keyMessage;
	}

	/** 
	* @return secret 
	*/
	public ISecret getSecret() {
		return secret;
	}

	/** 
	* @param secret 要设置的 secret 
	*/
	public void setSecret(ISecret secret) {
		this.secret = secret;
	}

	/** 
	* @return keyMessage 
	*/
	public String getKeyMessage() {
		return keyMessage;
	}

	/** 
	* @param keyMessage 要设置的 keyMessage 
	*/
	public void setKeyMessage(String keyMessage) {
		this.keyMessage = keyMessage;
	}

}
