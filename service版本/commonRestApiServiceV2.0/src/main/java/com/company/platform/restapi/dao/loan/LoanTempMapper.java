package com.company.platform.restapi.dao.loan;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.loan.LoanProductApplySyno;
import com.company.platform.restapi.model.loan.handled.ImageInfo;
import com.company.platform.restapi.model.loan.handled.LoanProductApplyInfo;
import com.company.platform.restapi.model.modelmanager.ImgAndDocTitle;

/** 
* @ClassName: LoanTempMapper 
* @Description: TODO(暂存任务myBatis Mapper) 
* @author luyuchi
* @date 2018年1月26日 下午5:25:33 
*  
*/
public interface LoanTempMapper {

	/** 
	* @Title: getLoanProductApplySynosByUserId 
	* @Description: TODO(通过登录用户ID获得其暂存任务) 
	* @param @param userId
	* @param @return    设定文件 
	* @return List<LoanProductApplySyno>    返回类型 
	* @throws 
	*/
	List<LoanProductApplySyno> getLoanProductApplySynosByUserId(String userId);

	/** 
	* @Title: getLoanApplyinfo 
	* @Description: TODO(通过借款产品申请ID获取借款申请相关信息详情) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return LoanProductApplyInfo    返回类型 
	* @throws 
	*/
	LoanProductApplyInfo getLoanApplyinfo(String loanProductApplyId);

	/** 
	* @Title: selectImageInfoByBusinessId 
	* @Description: TODO(根据业务id获取影像资料信息) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return List<ImageInfo>    返回类型 
	* @throws 
	*/
	List<ImageInfo> selectImageInfoByBusinessId(String loanProductApplyId);

	/** 
	* @Title: getImgRecordByBusId 
	* @Description: TODO(获取影像模型记录) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getImgRecordByBusId(String loanProductApplyId);

	/** 
	* @Title: getLoanProcessTaskInfo 
	* @Description: TODO(获取流程实例id和任务信息) 
	* @param @param user
	* @param @param applyId
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @throws 
	*/
	Map<String, String> getLoanProcessTaskInfo(@Param("user") String user, @Param("applyId") String applyId);

	/** 
	* @Title: getOrgName 
	* @Description: TODO(获取组织机构名称) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getOrgName(String loanProductApplyId);

	/** 
	* @Title: getImgAndDocTitleInfoByModelId 
	* @Description: TODO(获取文件分类) 
	* @param @param imgDocId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	List<ImgAndDocTitle> getImgAndDocTitleInfoByModelId(String imgDocId);
	
	/** 
	* @Title: findMaterialDataId 
	* @Description: TODO(获取业务id) 
	* @param @param businessId,modelId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String findMaterialDataId(@Param("modelId") String modelId, @Param("businessId") String businessId);

	/** 
	* @Title: queryModelClass 
	* @Description: TODO(获取影像分类信息) 
	* @param @param classId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	ImgAndDocTitle queryModelClass(String classId);

	/** 
	* @Title: queryFilesData 
	* @Description: TODO(获取影像数据信息) 
	* @param @param recordId,code
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> queryFilesData(@Param("recordId") String recordId, @Param("code") String code);
}
