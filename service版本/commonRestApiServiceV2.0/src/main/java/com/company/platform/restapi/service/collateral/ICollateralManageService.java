package com.company.platform.restapi.service.collateral;

import java.util.List;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.collateral.CollateralDetailResp;
import com.company.platform.restapi.model.collateral.CollateralInfo;
import com.company.platform.restapi.model.collateral.CollateralWarrantInfo;
import com.company.platform.restapi.model.collateral.CustomerWarrant;
import com.company.platform.restapi.model.collateral.TemplateElement;
import com.company.platform.restapi.model.collateral.TemplateValue;

/** 
* @ClassName: ICollateralManageService 
* @Description: TODO(押品管理servive) 
* @author 王雪 
* @date 2018年5月24日 下午5:25:58 
*  
*/
public interface ICollateralManageService {

	/** 
	* @Title: getCollateralList 
	* @Description: TODO(获取押品信息列表) 
	* @param @param orgList
	* @param @param userId
	* @param @return    设定文件 
	* @return List<CollateralInfo>    返回类型 
	* @throws 
	*/
	List<CollateralInfo> getCollateralList(List<String> orgList, String userId);

	/** 
	* @Title: deleteCollateralInfoById 
	* @Description: TODO(删除押品信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int deleteCollateralInfoById(String id);

	/** 
	* @Title: getCustomerWarrantList 
	* @Description: TODO(获取权属人列表信息) 
	* @param @param orgList
	* @param @return    设定文件 
	* @return List<CustomerWarrant>    返回类型 
	* @throws 
	*/
	List<CustomerWarrant> getCustomerWarrantList(List<String> orgList);

	/** 
	* @Title: getCollateralTemplateElementByType 
	* @Description: TODO(通过押品类型获取押品模板元素信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return List<TemplateElement>    返回类型 
	* @throws 
	*/
	List<TemplateElement> getCollateralTemplateElementById(String id);

	/** 
	* @Title: getTitleByTemplateId 
	* @Description: TODO(根据模板id获取标题) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getTitleByTemplateId(String id);

	/** 
	* @Title: getCollateralInfoById 
	* @Description: TODO(根据押品id获取押品详细信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return CollateralDetailResp    返回类型 
	* @throws 
	*/
	CollateralDetailResp getCollateralInfoById(String id) throws BusinessException;

	/** 
	* @Title: getTemplateValueByCollateralId 
	* @Description: TODO(通过押品id获取押品模板实际值列表) 
	* @param @param collateralId
	* @param @return    设定文件 
	* @return List<TemplateValue>    返回类型 
	* @throws 
	*/
	List<TemplateValue> getTemplateValueByCollateralId(String collateralId);

	/** 
	* @Title: insertCollateralWarrantInfo 
	* @Description: TODO(新建押品权证信息) 
	* @param @param info
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int insertCollateralWarrantInfo(CollateralWarrantInfo info);

	/** 
	* @Title: updateCollateralWarrantInfo 
	* @Description: TODO(更新押品权证信息) 
	* @param @param info
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int updateCollateralWarrantInfo(CollateralWarrantInfo info);

	/** 
	* @Title: saveTemplateValue 
	* @Description: TODO(保存押品模板实际值) 
	* @param @param collateralId
	* @param @param list
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int saveTemplateValue(String collateralId, List<TemplateValue> list);

}
