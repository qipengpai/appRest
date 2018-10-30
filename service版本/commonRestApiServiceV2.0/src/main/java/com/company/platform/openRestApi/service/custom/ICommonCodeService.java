/******************************************************************
 *
 *    Package:     com.company.platform.openRestApi.service
 *
 *    Filename:    ICommonCodeService.java
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
package com.company.platform.openRestApi.service.custom;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: ICommonCodeService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月20日 上午9:52:56
 */
public interface ICommonCodeService {

	/**
	 * @Title: checkCode @Description: TODO(这里用一句话描述这个方法的作用) @param @param session @param @param code @param @return
	 * 设定文件 @return boolean 返回类型 @throws
	 */
	String checkCode(HttpSession session, String code, String key, long time);
    /**
     * 
    * @Title: checkSmsCode 
    * @Description: TODO(校验手机短信验证码) 
    * @param @param session
    * @param @param code
    * @param @param key
    * @param @param time
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    String checkSmsCode(HttpSession session, String code, String key, String mobile, long time);

	/** 
	* @Title: getCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param session
	* @param @param key session内的key
	* @param @param time 有效时间
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getCode(HttpSession session, String key, long time);

	/** 
	* @Title: getCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param session
	* @param @param key
	* @param @param keyMessage
	* @param @param time
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getCode(HttpSession session, String key, String keyMessage, long time);
}
