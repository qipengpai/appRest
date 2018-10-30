package com.company.platform.restapi.dao.loan;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.loan.MerchantApplyInfo;

/** 
* @ClassName: MerchantApplyMapper 
* @Description: TODO(商户申请信息保存) 
* @author yangxu 
* @date 2018年2月2日 下午3:26:15 
*  
*/
public interface MerchantApplyMapper {
	
	/** 
	* @Title: insertMerchantApplyInfo 
	* @Description: TODO(商户申请信息保存) 
	* @param @param merchantApplyInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void insertMerchantApplyInfo(MerchantApplyInfo merchantApplyInfo);
	
	/** 
	* @Title: getMerchantApplyByBusiness 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param bussinessType (业务类型)
	* @param @param id (借贷申请id)
	* @param @return    设定文件 
	* @return MerchantApplyInfo    返回类型 
	* @throws 
	*/
	MerchantApplyInfo getMerchantApplyByBusiness(@Param("bussinessType") String bussinessType, @Param("bussinessId") String bussinessId);
	
	/** 
	* @Title: updateMerchantApplyInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param merchantApply    设定文件 (商户申请信息)
	* @return void    返回类型 
	* @throws 
	*/
	void updateMerchantApplyInfo(MerchantApplyInfo merchantApply);
}
