package com.company.platform.restapi.dao.modelmanager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.company.platform.restapi.model.loan.LoanProductModelV;
import com.company.platform.restapi.model.modelmanager.ImgAndDocModel;
import com.company.platform.restapi.model.modelmanager.ImgAndDocTitle;
import com.company.platform.restapi.model.modelmanager.ImgDocModelDataInfo;
import com.company.platform.restapi.model.modelmanager.ImgDocModelInfo;
import com.company.platform.restapi.model.modelmanager.ImgInfoModel;


/** 
* @ClassName: ImgAndDocModelMapper 
* @Description: TODO(影像资料数据库操作Mapper) 
* @author luyuchi
* @date 2018年1月24日 下午5:09:25 
*  
*/
public interface ImgAndDocModelMapper {

	/** 
	* @Title: getImgInfoModelsByProductId 
	* @Description: TODO(通过产品ID获得影像分类信息，App应用格式) 
	* @param @param productId
	* @param @return    设定文件 
	* @return List<ImgInfoModel>    返回类型 
	* @throws 
	*/
	List<ImgInfoModel> getImgInfoModelsByProductId(@Param("productId") String productId);

	/** 
	* @Title: getImgAndDocModelById 
	* @Description: TODO(通过ID获得影像模型) 
	* @param @param id
	* @param @return    设定文件 
	* @return ImgAndDocModel    返回类型 
	* @throws 
	*/
	ImgAndDocModel getImgAndDocModelById(@Param("id") String id);
	
	/** 
	* @Title: getImgAndDocModelByCodeAndVersion 
	* @Description: TODO(通过code和version获得影像模型) 
	* @param @param id
	* @param @return    设定文件 
	* @return ImgAndDocModel    返回类型 
	* @throws 
	*/
	ImgAndDocModel getImgAndDocModelByCodeAndVersion(@Param("code") String code,@Param("version") String version);

	/** 
	* @Title: getImgAndDocTitleById 
	* @Description: TODO(通过ID获得影像分类) 
	* @param @param id
	* @param @return    设定文件 
	* @return ImgAndDocTitle    返回类型 
	* @throws 
	*/
	ImgAndDocTitle getImgAndDocTitleById(@Param("id") String id);

	/** 
	* @Title: getMaterialDataId 
	* @Description: TODO(通过业务ID获得影像记录ID) 
	* @param @param businessId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getMaterialDataId(@Param("businessId") String businessId);

	/** 
	* @Title: saveMaterialDataForRecord 
	* @Description: TODO(保存一条影像记录) 
	* @param @param id
	* @param @param modelId
	* @param @param businessId
	* @param @param functionExplain
	* @param @param createDate
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int saveMaterialDataForRecord(@Param("id") String id, @Param("modelId") String modelId, @Param("businessId") String businessId, @Param("functionExplain") String functionExplain, @Param("createDate") Date createDate);

	/** 
	* @Title: saveMaterialData 
	* @Description: TODO(保存一条影像数据——文件信息) 
	* @param @param id
	* @param @param recordId
	* @param @param extension
	* @param @param modelCode
	* @param @param classCode
	* @param @param fileName
	* @param @param filePath
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int saveMaterialData(@Param("id") String id, @Param("recordId") String recordId, @Param("extension") String extension, @Param("modelCode") String modelCode, @Param("classCode") String classCode, @Param("fileName") String fileName, @Param("filePath") String filePath, @Param("orgId") String orgId, @Param("share") String share);
	
	/** 
	* @Title: getFileInfoByModelInfo 
	* @Description: TODO(获得上传文件信息) 
	* @param @param loanProductApplyId
	* @param @param classId
	* @param @param id
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getFileInfoByModelInfo(@Param("loanProductApplyId") String loanProductApplyId, @Param("classId") String classId, @Param("id") String id);

	/** 
	* @Title: deleteFileInfoById 
	* @Description: TODO(通过影像文件信息id删除对应的数据) 
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int deleteFileInfoById(@Param("id") String id);
	
	/** 
	* @Title: getImgDocModelInfo 
	* @Description: TODO(通过产品ID获得影像分类信息，App应用格式) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return List<ImgDocModelInfo>    返回类型 
	* @throws 
	*/
	List<ImgDocModelInfo> getImgDocModelInfo(String productId);
	
	/** 
	* @Title: getImgDocModelDataInfo 
	* @Description: TODO(通过产品ID获得影像数据信息，App应用格式) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return List<ImgDocModelDataInfo>    返回类型 
	* @throws 
	*/
	List<ImgDocModelDataInfo> getImgDocModelDataInfo(String loanProductApplyId);
	
	/** 
	* @Title: selectLoanProductModelVByLoanProductIdAndModelType 
	* @Description: TODO(获取模型信息) 
	* @param @param loanProductId，modelType
	* @param @return    设定文件 
	* @return LoanProductModelV    返回类型 
	* @throws 
	*/
	LoanProductModelV selectLoanProductModelVByLoanProductIdAndModelType(@Param("loanProductId") String loanProductId, @Param("modelType") String modelType);

}
