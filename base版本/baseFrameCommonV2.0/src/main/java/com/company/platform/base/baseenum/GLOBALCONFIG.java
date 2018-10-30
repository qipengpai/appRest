/******************************************************************
 *
 *    Package:     com.company.platform.base.baseenum
 *
 *    Filename:    GLOBALCONFIG.java
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
package com.company.platform.base.baseenum;

/**
 * @ClassName: GLOBALCONFIG
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月12日 上午9:03:52
 */
public interface GLOBALCONFIG {

	/**
	 * @Fields LOCKTIMES : TODO(被锁定限定次数)
	 */
	public static final String LOCKTIMES = "LOCKTIMES";

	/**
	 * @Fields APPKEY : TODO(用一句话描述这个变量表示什么)
	 */
	public static String APPKEY = "12345678";

	/**
	 * @Fields APPKEY : TODO(新注册用户默认角色)
	 */
	public static String DEFAULT_ROLE = "COMMON_ROLE";

	/**
	 * @Fields APPKEY : TODO(蚂蚁金盾app 默认角色id)
	 */
	public static String DEFAULT_MANAGER_ROLE = "2";

	/** 
	* @Fields GZIP_URI : TODO(指定gzip压缩过滤器的URI) 
	*/
	public static String GZIP_URI_FILTER1 = "/appApi/*";

	/** 
	* @Fields GZIP_URI : TODO(用一句话描述这个变量表示什么) 
	*/
	public static String GZIP_URI1 = "/appApi/";
	/** 
	 * @Fields GZIP_URI : TODO(指定gzip压缩过滤器的URI) 
	 */
	public static String GZIP_URI_FILTER2 = "/appCommonApi/*";

	/** 
	* @Fields GZIP_URI : TODO(用一句话描述这个变量表示什么) 
	*/
	public static String GZIP_URI2 = "/appCommonApi/";
	/** 
	 * @Fields GZIP_URI : TODO(指定gzip压缩过滤器的URI) 
	 */
	public static String GZIP_URI_FILTER3 = "/openCommonApi/*";

	/** 
	* @Fields GZIP_URI : TODO(用一句话描述这个变量表示什么) 
	*/
	public static String GZIP_URI3 = "/openCommonApi/";
}
