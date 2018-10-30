package com.company.platform.restapi.dao.modelmanager;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.basicdata.DicInfo;
import com.company.platform.restapi.model.modelmanager.BusColumnInfoModel;
import com.company.platform.restapi.model.modelmanager.BusInfoModel;
import com.company.platform.restapi.model.modelmanager.BusTitleInfoModel;
import com.company.platform.restapi.model.modelmanager.BusTitleModelInfo;
import com.company.platform.restapi.model.modelmanager.ModelBusDataInfo;
import com.company.platform.restapi.model.modelmanager.ModelBusTitleInfo;

/** 
* @ClassName: BusinessModelMapper 
* @Description: TODO(业务模型数据库操作Mapper) 
* @author luyuchi
* @date 2018年1月24日 下午5:10:29 
*  
*/
public interface BusinessModelMapper {

	/** 
	* @Title: getBusinessModelsByProductId 
	* @Description: TODO(通过产品ID获得业务模型信息，App应用格式) 
	* @param @param productId
	* @param @return    设定文件 
	* @return List<BusInfoModel>    返回类型 
	* @throws 
	*/
	List<BusInfoModel> getBusinessModelsByProductId(String productId);

	/** 
	* @Title: getBusinessTitlesByProductId 
	* @Description: TODO(通过产品ID获得业务模型标题信息，App应用格式) 
	* @param @param productId
	* @param @return    设定文件 
	* @return List<BusTitleInfoModel>    返回类型 
	* @throws 
	*/
	List<BusTitleInfoModel> getBusinessTitlesByProductId(String productId);

	/** 
	* @Title: getBusinessColumnsByProductId 
	* @Description: TODO(通过产品ID获得业务模型字段信息，App应用格式) 
	* @param @param productId
	* @param @return    设定文件 
	* @return List<BusColumnInfoModel>    返回类型 
	* @throws 
	*/
	List<BusColumnInfoModel> getBusinessColumnsByProductId(String productId);

	/** 
	* @Title: getModelBusTitleInfo 
	* @Description: TODO(通过产品ID获得业务模型字段信息，App应用格式) 
	* @param @param productId
	* @param @return    设定文件 
	* @return List<ModelBusTitleInfo>    返回类型 
	* @throws 
	*/
	List<ModelBusTitleInfo> getModelBusTitleInfo(String productId);

	/** 
	* @Title: getModelBusDataInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return List<ModelBusTitleInfo>    返回类型 
	* @throws 
	*/
	List<ModelBusDataInfo> getModelBusDataInfo(String loanProductApplyId);

	/** 
	* @Title: getBusRecordByBusId 
	* @Description: TODO(通过借贷申请id获得业务模型数据记录) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getBusRecordByBusId(String loanProductApplyId);

	/** 
	* @Title: getBusModelById 
	* @Description: TODO(通过业务模型id获得业务模型信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getBusModelById(String id);

	/** 
	* @Title: emptyModelData 
	* @Description: TODO(清空业务模型数据) 
	* @param @param recordId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int emptyModelData(String recordId);

	/** 
	* @Title: getProductModelByProductId 
	* @Description: TODO(查询产品模型) 
	* @param @param loanProductId
	* @param @param modelType
	* @param @return    设定文件 
	* @return Map<String, Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getProductModelByProductId(@Param("loanProductId") String loanProductId, @Param("modelType") String modelType);

	/** 
	* @Title: getProductModelV 
	* @Description: TODO(查询业务模型表) 
	* @param @param modelCode
	* @param @param modelVersion
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getProductModelV(@Param("modelCode") String modelCode, @Param("modelVersion") String modelVersion);
	
	/** 
	* @Title: getProductModelByModelId 
	* @Description: TODO(查询业务模型分类表) 
	* @param @param productModelId
	* @param @return    设定文件 
	* @return List<ModelBusTitleInfo>    返回类型 
	* @throws 
	*/
	List<BusTitleModelInfo> getProductModelByModelId(String productModelId);
	
	/** 
	* @Title: getProductModelBytitleId 
	* @Description: TODO(查询分类字段信息) 
	* @param @param titleId
	* @param @return    设定文件 
	* @return List<BusColumnInfoModel>    返回类型 
	* @throws 
	*/
	List<BusColumnInfoModel> getProductModelBytitleId(String titleId);

	/** 
	* @Title: getDataRangItemByRangeCode 
	* @Description: TODO(查询分类字段信息) 
	* @param @param rangeCode
	* @param @return    设定文件 
	* @return List<DicInfo>    返回类型 
	* @throws 
	*/
	List<DicInfo> getDataRangItemByRangeCode(String rangeCode);

}
