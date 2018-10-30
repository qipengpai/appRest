/**
 * 
 */
package com.company.platform.base.exception;

/** 
* @ClassName: BusinessException 
* @Description: TODO(自定义异常) 
* @author zhengjn 
* @date 2017年9月24日 下午1:08:40 
*  
*/
@SuppressWarnings("serial")
public class BusinessException extends Exception {

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected String code;
	
	public BusinessException(String code, String msgFormat){
		this.code = code;
		this.msg = msgFormat;
	}

	public BusinessException(String code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public String getMsg() {
		return msg;
	}

	public String getCode() {
		return code;
	}
}
