package com.company.platform.restapi.dao.guarantor;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.guarantor.GuarantorInfo;

/** 
* @ClassName: GuarantorInfoMapper 
* @Description: TODO(担保人mapper) 
* @author 王雪
* @date 2018年5月29日 下午5:34:52 
*  
*/
public interface GuarantorInfoMapper {
	/** 
	* @Title: showGuarantorInByApplyId 
	* @Description: TODO(显示担保人信息) 
	* @param @param applyId
	* @param @return    设定文件 
	* @return List<GuarantorInfo>    返回类型 
	* @throws 
	*/
	List<GuarantorInfo> showGuarantorInByApplyId(String applyId);

	/** 
	* @Title: deleteLoanProductApplyGuarantor 
	* @Description: TODO(删除借贷关联担保人信息) 
	* @param @param applyId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int deleteLoanProductApplyGuarantor(String applyId);

	/** 
	* @Title: selectGuarantorInfoIds 
	* @Description: TODO(获取当前借贷申请担保人ids) 
	* @param @param applyId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String selectGuarantorInfoIds(String applyId);

	/** 
	* @Title: deleteGuarantorInfo 
	* @Description: TODO(删除担保人信息) 
	* @param @param applyId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int deleteGuarantorInfo(String applyId);

	/** 
	* @Title: insertGuarantorInfo 
	* @Description: TODO(新建担保人信息) 
	* @param @param guarantor
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int insertGuarantorInfo(GuarantorInfo guarantor);

	/** 
	* @Title: insertLoanProductApplyGuarantor 
	* @Description: TODO(新建借贷担保人信息) 
	* @param @param guarantor
	* @param @param applyId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int insertLoanProductApplyGuarantor(@Param("guarantor") GuarantorInfo guarantor, @Param("applyId") String applyId);
}
