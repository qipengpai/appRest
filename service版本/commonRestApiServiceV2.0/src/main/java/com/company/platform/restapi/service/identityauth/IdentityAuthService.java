package com.company.platform.restapi.service.identityauth;

import java.util.Map;

import com.company.platform.restapi.model.basicdata.ThirdResult;


/** 
* @ClassName: IdentityAuthService 
* @Description: TODO(姓名身份证大数据校验) 
* @author dongjian 
* @date 2018年5月17日 下午2:12:31 
*  
*/
public interface IdentityAuthService {
	/**
	 * 校验身份证姓名是否一致
	 * 
	 * @param params
	 * @return
	 */
	public ThirdResult checkNameAndCard(Map<String, Object> params) throws Exception;
}
