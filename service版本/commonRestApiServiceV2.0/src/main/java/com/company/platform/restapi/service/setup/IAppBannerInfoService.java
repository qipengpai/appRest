package com.company.platform.restapi.service.setup;

import java.util.List;

import com.company.platform.restapi.model.setup.AppBannerInfoResp;


/** 
* @ClassName: IAppBannerInfoService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年1月3日 下午2:59:03 
*  
*/
public interface IAppBannerInfoService {
	/** 
	* @Title: getAppBannerInfo 
	* @Description: TODO(获取banner) 
	* @param @return    设定文件 
	* @return List<AppBannerInfoResp>    返回类型 
	* @throws 
	*/
	List<AppBannerInfoResp> getAppBannerInfo(String appName);
}
