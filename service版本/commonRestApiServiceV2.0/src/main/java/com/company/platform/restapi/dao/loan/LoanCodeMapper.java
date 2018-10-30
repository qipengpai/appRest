package com.company.platform.restapi.dao.loan;

import java.util.List;
import java.util.Map;

import com.company.platform.restapi.model.loan.LoanProductValidateInfo;

/** 
* @ClassName: LoanCodeMapper 
* @Description: TODO(借贷编码) 
* @author 王雪 
* @date 2018年1月29日 下午5:54:22 
*  
*/
public interface LoanCodeMapper {

	/** 
	* @Title: getLoanProductValidateById 
	* @Description: TODO(通过借贷产品id获取借贷产品验证信息) 
	* @param @param productId 借贷产品id
	* @param @return    设定文件 
	* @return LoanProductValidateInfo    返回类型 
	* @throws 
	*/
	LoanProductValidateInfo getLoanProductValidateById(String productId);

	/** 
	* @Title: getLoanProductOrgRel 
	* @Description: TODO(获取借贷产品与组织结构权限信息) 
	* @param @param info
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getLoanProductOrgRel(Map<String, String> info);

	/** 
	* @Title: getOrgIdByUserId 
	* @Description: TODO(通过用户id获取关联组织机构id) 
	* @param @param userId 用户id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	Map<String, String> getOrgIdByUserId(String userId);

	/** 
	* @Title: selectSysCodeByType 
	* @Description: TODO(通过类型生成借贷编码) 
	* @param @param type
	* @param @return    设定文件 
	* @return List<Map<String,String>>    返回类型 
	* @throws 
	*/
	List<Map<String, String>> selectSysCodeByType(String type);
	
	/** 
	* @Title: save 
	* @Description: TODO(保存系统编码生成数据) 
	* @param @param info
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	void save(Map<String, String> info);
	
	/** 
	* @Title: updateVal 
	* @Description: TODO(更新系统编码生成数据) 
	* @param @param info    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void updateVal(Map<String, String> info);
}
