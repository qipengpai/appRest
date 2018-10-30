package com.company.platform.restapi.service.loan;

import java.util.Map;

import com.company.platform.base.exception.BusinessException;

/** 
* @ClassName: ILoanCodeService 
* @Description: TODO(借贷编码) 
* @author 王雪 
* @date 2018年1月29日 下午5:50:07 
*  
*/
public interface ILoanCodeService {
	
	/** 
	* @Title: getOrgInfoByUserId 
	* @Description: TODO(通过当前登录用户id获取组织机构信息) 
	* @param @param userId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	public Map<String, String> getOrgInfoByUserId(String userId) throws BusinessException;
	
	/** 
	* @Title: getSysCode 
	* @Description: TODO(获取编码(借贷申请或押品)) 
	* @param @param type
	* @param @param orgCode
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String getSysCode(String type, String orgCode) throws BusinessException;
}
