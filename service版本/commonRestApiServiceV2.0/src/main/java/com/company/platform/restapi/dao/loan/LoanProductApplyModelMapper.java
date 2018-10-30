package com.company.platform.restapi.dao.loan;

import com.company.platform.restapi.model.loan.LoanProductApplyModelV;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/** 
* @ClassName: LoanProductApplyModelMapper 
* @Description: TODO(业务与模型数据关联Mapper) 
* @author luyuchi
* @date 2018年1月30日 下午2:54:14 
*  
*/
public interface LoanProductApplyModelMapper {

	/** 
	* @Title: saveLoanProductApplyModelV 
	* @Description: TODO(保存业务与模型数据关联Model) 
	* @param @param loanProductApplyModelV
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int saveLoanProductApplyModelV(LoanProductApplyModelV loanProductApplyModelV);
	
	/** 
	* @Title: getLoanProductApplyModelVInfo 
	* @Description: TODO(获取借贷申请模型信息) 
	* @param @param id(模型Id)
	* @param @return    设定文件 
	* @return LoanProductApplyModelV    返回类型 
	* @throws 
	*/
	LoanProductApplyModelV getLoanProductApplyModelVInfo(String id);

	/**
	 * @Description: TODO 准入征信模型和风控征信模型存在多条数据，其他模型只有一条数据
	 * @param loanProductApplyId
	 * @param modelType
	 * @return
	 */
	@Select("select amv.* from loan_product_apply_model_v amv, loan_product_model_v pmv where amv.modelCode = pmv.modelCode and amv.loanProductApplyId=#{loanProductApplyId} and amv.modelType=#{modelType}")
	List<LoanProductApplyModelV> selectByLoanProductApplyIdAndModelType(@Param("loanProductApplyId") String loanProductApplyId,
																		@Param("modelType") String modelType);}
