package com.company.platform.restapi.dao.loan;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.loan.FullLoanProductApplyInfo;
import com.company.platform.restapi.model.loan.LoanProductApplyModelV;
import com.company.platform.restapi.model.loan.LoanProductModelV;
import com.company.platform.restapi.model.loan.ProductApplyInfoResp;
import com.company.platform.restapi.model.loan.offToOnline.LoanProductApplyEntity;
import com.company.platform.restapi.model.loan.onlineTemp.LoanProductApplyInfo;

/** 
* @ClassName: ProductApplyInfoMapper 
* @Description: TODO(借贷申请信息) 
* @author yangxu 
* @date 2018年1月25日 下午4:22:13 
*  
*/
public interface ProductApplyInfoMapper {
	/**
	 * 
	* @Title: delProductApplyInfo 
	* @Description: TODO(根据借贷申请Id删除暂存的借贷信息) 
	* @param @param id (借贷申请Id)
	* @return void    返回类型 
	* @throws
	 */
	int delProductApplyInfo(String id);

	/** 
	* @Title: modifyLoanProductApply 
	* @Description: TODO(修改借贷申请信息) 
	* @param @param loanProductApplyInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void modifyLoanProductApply(LoanProductApplyInfo loanProductApplyInfo);

	/** 
	* @Title: selectLoanProductApplyById 
	* @Description: TODO(获取借贷申请信息) 
	* @param @param id(借贷申请id)
	* @param @return    设定文件 
	* @return ProductApplyInfoResp    返回类型 
	* @throws 
	*/
	ProductApplyInfoResp selectLoanProductApplyById(String id);

	/** 
	* @Title: getLoanProductApplyById 
	* @Description: TODO(根据id获得接待申请信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return LoanProductApplyInfo    返回类型 
	* @throws 
	*/
	FullLoanProductApplyInfo getLoanProductApplyById(String id);

	/** 
	* @Title: updateLoanProductApplyInfo 
	* @Description: TODO(保存第二部借贷申请信息) 
	* @param @param loanProductApplyInfo    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void updateLoanProductApplyInfo(FullLoanProductApplyInfo loanProductApplyInfo);

	/** 
	* @Title: getLoanProductModelVInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param loanProductId (借贷申请Id)
	* @param @param modelType (模型类型)
	* @param @return    设定文件 
	* @return LoanProductModelV    返回类型 
	* @throws 
	*/
	LoanProductModelV getLoanProductModelVInfo(@Param("loanProductId") String loanProductId,
			@Param("modelType") String modelType);

	/** 
	* @Title: getLoanProductApplyModelVInfo 
	* @Description: TODO(获取数据模型信息) 
	* @param @param loanProductId (借贷申请Id)
	* @param @param modelType (模型类型)
	* @param @return    设定文件 
	* @return List<LoanProductApplyModelV>    返回类型 
	* @throws 
	*/
	List<LoanProductApplyModelV> getLoanProductApplyModelVInfo(@Param("loanProductId") String loanProductId,
			@Param("modelType") String modelType);

	/** 
	* @Title: insertOffLineLoanProductApply 
	* @Description: TODO(新建离线转在线借贷申请信息) 
	* @param @param loanProductApplyInfo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int insertOffLineLoanProductApply(LoanProductApplyEntity loanProductApplyInfo);

	/** 
	* @Title: selectBusModelRecord 
	* @Description: TODO(获取业务模型记录信息) 
	* @param @param businessId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> selectBusModelRecord(String businessId);

	/** 
	* @Title: getImgModelById 
	* @Description: TODO(通过影像模型id获得影像模型信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getImgModelById(String id);

	/** 
	* @Title: saveLoanApplyModelS 
	* @Description: TODO(保存模型数据与借贷信息关系) 
	* @param @param loanProductApplyId
	* @param @param modelCode
	* @param @param modelVersion
	* @param @param modelType
	* @param @param modelInstanceId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int saveLoanApplyModelS(@Param("loanProductApplyId") String loanProductApplyId,
			@Param("modelCode") String modelCode, @Param("modelVersion") String modelVersion,
			@Param("modelType") String modelType, @Param("modelInstanceId") String modelInstanceId);

	/** 
	* @Title: saveImgModelRecord 
	* @Description: TODO(创建影像模型记录) 
	* @param @param id
	* @param @param loanApplyId
	* @param @param imgModelId
	* @param @param createDateString
	* @param @param functionExplain
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int saveImgModelRecord(@Param("id") String id, @Param("loanApplyId") String loanApplyId,
			@Param("imgModelId") String imgModelId, @Param("createDate") String createDate,
			@Param("functionExplain") String functionExplain);

	/** 
	* @Title: modifyUpdateTime 
	* @Description: TODO(更改更新 时间) 
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int modifyUpdateTime(@Param("id") String id);

	/** 
	* @Title: updateLoanProductApplyTimeById 
	* @Description: TODO(更新借贷申请时间) 
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int updateLoanProductApplyTimeById(String id);

	/** 
	* @Title: updateLoanApplyStatus 
	* @Description: TODO(更新借贷申请状态、更新时间) 
	* @param @param status
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int updateLoanApplyStatus(@Param("status") String status, @Param("id") String id);
}
