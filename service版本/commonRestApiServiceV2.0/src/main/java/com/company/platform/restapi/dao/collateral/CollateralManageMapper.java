package com.company.platform.restapi.dao.collateral;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.collateral.CollateralDetailResp;
import com.company.platform.restapi.model.collateral.CollateralInfo;
import com.company.platform.restapi.model.collateral.CollateralWarrantInfo;
import com.company.platform.restapi.model.collateral.CustomerWarrant;
import com.company.platform.restapi.model.collateral.TemplateElement;
import com.company.platform.restapi.model.collateral.TemplateValue;

/** 
* @ClassName: CollateralManageMapper 
* @Description: TODO(押品管理mapper) 
* @author 王雪 
* @date 2018年5月22日 上午11:47:52 
*  
*/
public interface CollateralManageMapper {
	/** 
	* @Title: getCollateralList 
	* @Description: TODO(获取押品信息列表) 
	* @param @param orgIds
	* @param @param userId
	* @param @return    设定文件 
	* @return List<CollateralInfo>    返回类型 
	* @throws 
	*/
	List<CollateralInfo> getCollateralList(@Param("list") List<String> list, @Param("userId") String userId);

	/** 
	* @Title: getCollateralWarrantInfoById 
	* @Description: TODO(根据押品id获取押品权证信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getCollateralWarrantInfoById(String id);

	/** 
	* @Title: getLoanCollateralRelByColId 
	* @Description: TODO(根据押品id获取贷款押品关联信息) 
	* @param @param collateralId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getLoanCollateralRelByColId(String collateralId);

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
	* @param @param list
	* @param @return    设定文件 
	* @return List<CustomerWarrant>    返回类型 
	* @throws 
	*/
	List<CustomerWarrant> getCustomerWarrantList(@Param("list") List<String> list);

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
	* @Title: getCollateralTemplateElementById 
	* @Description: TODO(通过押品类型获取押品模板元素信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return List<TemplateElement>    返回类型 
	* @throws 
	*/
	List<TemplateElement> getCollateralTemplateElementById(String id);

	/** 
	* @Title: getCollateralInfoById 
	* @Description: TODO(根据押品id获取押品详细信息) 
	* @param @param id
	* @param @return    设定文件 
	* @return CollateralDetailResp    返回类型 
	* @throws 
	*/
	CollateralDetailResp getCollateralInfoById(String id);

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
	* @Title: deleteTemplateValueByCollateralId 
	* @Description: TODO(删除押品模板原实际值) 
	* @param @param collateralId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int deleteTemplateValueByCollateralId(String collateralId);

	/** 
	* @Title: insertTemplateValue 
	* @Description: TODO(新建押品模板实际值) 
	* @param @param key
	* @param @param value
	* @param @param collateralId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int insertTemplateValue(@Param("key") String key, @Param("value") String value,
			@Param("collateralId") String collateralId);
}
