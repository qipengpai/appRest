package com.company.platform.restapi.service.loan;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.guarantor.GuarantorInfo;
import com.company.platform.restapi.model.loan.FullLoanProductApplyInfo;
import com.company.platform.restapi.model.loan.LoanProductApply;
import com.company.platform.restapi.model.loan.offToOnline.LoanProductApplyEntity;

/** 
* @ClassName: ILoanProductApplyService 
* @Description: TODO(更新或插入借贷申请信息) 
* @author yangxu 
* @date 2018年2月2日 上午10:23:27 
*  
*/
public interface ILoanProductApplyService {

	/** 
	* @Title: saveOrUpdateLoanProductApply 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param LoanProductApplyInfo
	* @param @param beforeApplyAmount
	* @param @param isStart
	* @param @param userId
	* @param @param nextTaskKey
	* @param @param auditUserId
	* @param @param audit
	* @param @param jsonData
	* @param @param guarantor
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void saveOrUpdateLoanProductApply(FullLoanProductApplyInfo LoanProductApplyInfo,
			BigDecimal beforeApplyAmount, String isStart, String userId, String nextTaskKey, String auditUserId,
			String audit, String jsonData, List<GuarantorInfo> guarantor) throws BusinessException;

	/** 
	* @Title: updateLoanProductApply 
	* @Description: TODO(第二步插入借贷申请信息) 
	* @param @param loanProductApply(借贷申请信息)
	* @param @param modelData(模型数据)
	* @param @param authsMap(权限Map)
	* @param @param beforeApplyAmount(之前的借贷金额)
	* @param @param guarantor
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void updateLoanProductApply(FullLoanProductApplyInfo loanProductApply, String modelData,
			Map<String, String> authsMap, BigDecimal beforeApplyAmount, List<GuarantorInfo> guarantor) throws BusinessException;

	/** 
	* @Title: saveModelData 
	* @Description: TODO(保存业务模型) 
	* @param @param businessId(借贷申请id)
	* @param @param jsonData(业务模型数据)
	* @param @param explain(解释说明)
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String saveModelData(String businessId, JSONObject jsonData, String explain);

	/** 
	* @Title: insertOffLineLoanProductApply 
	* @Description: TODO(新建连线转在线借贷产品信息) 
	* @param @param loanProductApplyInfo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public int insertOffLineLoanProductApply(LoanProductApplyEntity loanProductApplyInfo);

	/** 
	* @Title: selectBusModelRecord 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param businessId
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	public Map<String, Object> selectBusModelRecord(String businessId) throws BusinessException;

	/** 
	* @Title: modifyModelData 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param data
	* @param @param recordId
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public boolean modifyModelData(JSONObject data, String recordId);

	/** 
	* @Title: saveLoanApplyModelS 
	* @Description: TODO(保存业务模型数据与借贷申请关系) 
	* @param @param loanProductApplyId
	* @param @param modelId
	* @param @param modelType
	* @param @param recordId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public int saveLoanApplyModelS(String loanProductApplyId, String modelId, String modelType, String recordId);

	/** 
	* @Title: saveLoanApplyImgModelS 
	* @Description: TODO(保存影像模型数据与借贷申请关系) 
	* @param @param loanProductApplyId
	* @param @param modelId
	* @param @param modelType
	* @param @param recordId
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public int saveLoanApplyImgModelS(String loanProductApplyId, String modelId, String modelType, String recordId)
			throws BusinessException;

	/** 
	* @Title: saveImgModelRecord 
	* @Description: TODO(创建借贷申请影像模型记录) 
	* @param @param loanApplyId
	* @param @param imgModelId
	* @param @param functionExplain
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String saveImgModelRecord(String loanApplyId, String imgModelId, String functionExplain)
			throws BusinessException;

	/** 
	* @Title: modifyUpdateTime 
	* @Description: TODO(修改更新时间) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public int modifyUpdateTime(String loanProductApplyId);

	/** 
	* @Title: emptyModelData 
	* @Description: TODO(清空业务模型数据) 
	* @param @param recordId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	public int emptyModelData(String recordId);

	/**
	 * @Author qipengpai
	 * @Description //TODO 获取拒绝原因（人工审核原因，该原因只针对于核验结果）
	 * @Date 21:22 2018/9/13
	 * @Param [loanProductApplyId]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	Map<String,Object> getApprovalLog(String loanProductApplyId);

	/**
	 * @Author qipengpai
	 * @Description //TODO 通过借贷申请id查询借贷申请信息
	 * @Date \ 2018/9/14
	 * @Param [loanProductApplyId]
	 * @return com.company.platform.restapi.model.loan.LoanProductApply
	 **/
	LoanProductApply selectLoanProductApplyById(String loanProductApplyId);

	/**
	 * @Author qipengpai
	 * @Description //TODO 删除未提交进件信息
	 * @Date 11:05 2018/9/14
	 * @Param [loanProductApplyId]
	 * @return int
	 **/
	boolean deleteApply(String loanProductApplyId);

}
