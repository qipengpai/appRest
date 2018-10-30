package com.company.platform.restapi.dao.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.platform.restapi.model.setup.AppBannerInfo;
import com.company.platform.restapi.model.setup.AppVersion;

/**
 * 
* @ClassName: MobileSetUpMapper 
* @Description: TODO(移动端设置) 
* @author zhengjn 
* @date 2017年10月30日 下午6:16:08 
*
 */
public interface MobileSetUpMapper {
	
	/**
	 * 
	* @Title: getUserByMobile 
	* @Description: TODO(找到用户) 
	* @param @param mobile
	* @param @return    设定文件 
	* @return HashMap<String,Object>    返回类型 
	* @throws
	 */
	HashMap<String, Object> getUserById(String id);
	/**
	 * 
	* @Title: modifyPwd 
	* @Description: TODO(修改密码) 
	* @param @param info    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void modifyPwd(Map<String, Object> info);
	/**
	 * 
	* @Title: getAppVserionByType 
	* @Description: TODO(获取banner信息) 
	* @param @param appType
	* @param @return    设定文件 
	* @return HashMap<String,Object>    返回类型 
	* @throws
	 */
	List<AppBannerInfo> getBannerInfoList(String appName);
	/**
	 * 
	* @Title: getAppVserionByType 
	* @Description: TODO(获取版本信息) 
	* @param @param appType
	* @param @return    设定文件 
	* @return HashMap<String,Object>    返回类型 
	* @throws
	 */
	@S
	AppVersion getAppVserionByType(Map<String, String> map);
}
