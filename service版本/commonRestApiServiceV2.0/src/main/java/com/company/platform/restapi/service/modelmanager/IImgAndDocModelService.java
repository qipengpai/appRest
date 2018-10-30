package com.company.platform.restapi.service.modelmanager;

import java.util.List;
import java.util.Map;

import com.company.platform.restapi.model.loan.handled.ImageDataInfo;
import com.company.platform.restapi.model.modelmanager.ImgAndDocModel;
import com.company.platform.restapi.model.modelmanager.ImgAndDocTitle;
import com.company.platform.restapi.model.modelmanager.ImgInfoModel;

/** 
* @ClassName: IImgAndDocModelService 
* @Description: TODO(操作影像模型service) 
* @author luyuchi
* @date 2018年1月24日 下午5:49:24 
*  
*/
public interface IImgAndDocModelService {

	/** 
	* @Title: getImgInfoModelsByProductId 
	* @Description: TODO(通过产品ID获得影像模型分类信息) 
	* @param @param productId
	* @param @return    设定文件 
	* @return List<ImgInfoModel>    返回类型 
	* @throws 
	*/
	List<ImgInfoModel> getImgInfoModelsByProductId(String productId);

	/** 
	* @Title: getImgAndDocModelById 
	* @Description: TODO(通过影像模型ID获得影像模型) 
	* @param @param id 影像模型ID
	* @param @return    设定文件 
	* @return ImgAndDocModel    返回类型 
	* @throws 
	*/
	ImgAndDocModel getImgAndDocModelById(String id);

	/** 
	* @Title: getImgAndDocTitleById 
	* @Description: TODO(通过影像分类ID获得影像分类) 
	* @param @param id 影像分类ID
	* @param @return    设定文件 
	* @return ImgAndDocTitle    返回类型 
	* @throws 
	*/
	ImgAndDocTitle getImgAndDocTitleById(String id);

	/** 
	* @Title: getMaterialDataId 
	* @Description: TODO(通过影像模型ID和业务ID获得影像记录ID) 
	* @param @param businessId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getMaterialDataId(String businessId);

	/** 
	* @Title: saveMaterialData 
	* @Description: TODO(保存上传影像信息，并创建影像信息记录) 
	* @param @param id
	* @param @param imgDocId
	* @param @param loanProductApplyId
	* @param @param extension
	* @param @param fileName
	* @param @param modelCode
	* @param @param classCode
	* @param @param path
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String saveMaterialData(String id, String imgDocId, String loanProductApplyId, String extension, String fileName,
			String modelCode, String classCode, String path, String orgId, String share);

	/** 
	* @Title: addMaterialData 
	* @Description: TODO(在影像信息记录上保存上传影像信息) 
	* @param @param id
	* @param @param dataId
	* @param @param extension
	* @param @param fileName
	* @param @param modelCode
	* @param @param classCode
	* @param @param path    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void addMaterialData(String id, String dataId, String extension, String fileName, String modelCode,
			String classCode, String path, String orgId, String share);

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
	Map<String, Object> getFileInfoByModelInfo(String loanProductApplyId, String classId, String id);

	/** 
	* @Title: deleteFileInfo 
	* @Description: TODO(通过影像文件信息id删除对应的数据) 
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int deleteFileInfo(String id);

	/** 
	* @Title: getImageInfoList 
	* @Description: TODO(获取影像资料数据信息) 
	* @param @param loanApplyId
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<ImageDataInfo> getImageInfoList(String loanApplyId);
	
	/** 
	* @Title: getImgAndDocTitleInfo 
	* @Description: TODO(获取当前借贷申请关联的影像模型信息) 
	* @param @param loanApplyId
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<ImgAndDocTitle> getImgAndDocTitleInfo(String loanApplyId);
	
	/** 
	* @Title: getMaterialDataId 
	* @Description: TODO(获取影像模型信息) 
	* @param @param loanApplyId
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	String getMaterialDataIdPro(String modelId, String businessId);
	/**
	 * 查询模型分类信息
	 * @param classId 分类id
	 * @param recordId 关联id
	 * @return
	 */
	public int imageClassNum(String classId,String recordId);
}
