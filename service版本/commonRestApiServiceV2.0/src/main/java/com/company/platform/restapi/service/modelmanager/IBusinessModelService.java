package com.company.platform.restapi.service.modelmanager;

import java.util.List;

import com.company.platform.restapi.model.modelmanager.BusColumnInfoModel;
import com.company.platform.restapi.model.modelmanager.BusInfoModel;
import com.company.platform.restapi.model.modelmanager.BusTitleInfoModel;

/** 
* @ClassName: IBusinessModelService 
* @Description: TODO(业务模型操作service) 
* @author luyuchi
* @date 2018年1月24日 下午5:28:21 
*  
*/
public interface IBusinessModelService {

	/** 
	* @Title: getBusinessModelsByProductId 
	* @Description: TODO(根据产品ID获得业务模型) 
	* @param @param productId
	* @param @return    设定文件 
	* @return List<BusInfoModel>    返回类型 
	* @throws 
	*/
	List<BusInfoModel> getBusinessModelsByProductId(String productId);
	
	/** 
	* @Title: getBusinessTitlesByProductId 
	* @Description: TODO(根据产品ID获得业务模型标题) 
	* @param @param productId
	* @param @return    设定文件 
	* @return List<BusTitleInfoModel>    返回类型 
	* @throws 
	*/
	List<BusTitleInfoModel> getBusinessTitlesByProductId(String productId);
	
	
	/** 
	* @Title: getBusinessColumnsByProductId 
	* @Description: TODO(根据产品ID获得业务模型字段) 
	* @param @param productId
	* @param @return    设定文件 
	* @return List<BusColumnInfoModel>    返回类型 
	* @throws 
	*/
	List<BusColumnInfoModel> getBusinessColumnsByProductId(String productId);
}
