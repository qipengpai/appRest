package com.company.platform.restapi.dao.loan;

import java.util.List;
import java.util.Map;

/** 
* @ClassName: InformationMapper 
* @Description: TODO(商户信息) 
* @author yangxu 
* @date 2018年2月2日 下午1:54:58 
*  
*/
public interface InformationMapper {

	/** 
	* @Title: getMerchantInformationByOrganizationCode 
	* @Description: TODO(获取商户信息) 
	* @param @param orgId
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> getMerchantInformationByOrganizationCode(String orgId);

}
