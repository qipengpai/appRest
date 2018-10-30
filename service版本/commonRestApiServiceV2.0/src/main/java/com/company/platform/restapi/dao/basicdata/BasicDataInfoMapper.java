package com.company.platform.restapi.dao.basicdata;

import java.util.List;
import java.util.Map;

import com.company.platform.restapi.model.basicdata.CityInfo;
import com.company.platform.restapi.model.basicdata.ConfigInfo;
import com.company.platform.restapi.model.basicdata.DicInfo;
import com.company.platform.restapi.model.basicdata.DicRangeInfo;
import com.company.platform.restapi.model.basicdata.JurisdictionInfo;
import com.company.platform.restapi.model.basicdata.ModelBusCodeAndVersion;
import com.company.platform.restapi.model.basicdata.ModelBusColumnInfo;
import com.company.platform.restapi.model.basicdata.ModelBusInfo;
import com.company.platform.restapi.model.basicdata.ModelBusTitleInfo;
import com.company.platform.restapi.model.basicdata.ModelImgInfo;
import com.company.platform.restapi.model.basicdata.OrgDeptInfo;
import com.company.platform.restapi.model.basicdata.OrgInfo;
import com.company.platform.restapi.model.basicdata.PosterInfo;
import com.company.platform.restapi.model.basicdata.ProcdefInfo;
import com.company.platform.restapi.model.basicdata.ProductInfo;
import com.company.platform.restapi.model.basicdata.RoleInfo;

/**
 * @ClassName: BasicDataInfoMapper
 * @Description: TODO(基础数据信息)
 * @author liang
 * @date 2018年1月25日 下午2:50:03
 * 
 */
public interface BasicDataInfoMapper {

	/**
	 * 
	 * @Title: getRoleInfoByUserId @Description:
	 * TODO(通过用户id获取角色信息) @param @param userId @param @return 设定文件 @return
	 * List<RoleInfo> 返回类型 @throws
	 */
	List<RoleInfo> getRoleInfoByUserId(String userId);

	/**
	 * 
	 * @Title: getPosterInfoByUserId @Description:
	 * TODO(通过用户id获取岗位信息) @param @param userId @param @return 设定文件 @return
	 * List<PosterInfo> 返回类型 @throws
	 */
	List<PosterInfo> getPosterInfoByUserId(String userId);

	/**
	 * 
	 * @Title: getOrgInfoByUserId @Description:
	 * TODO(通过用户id获取组织机构信息) @param @param id @param @return 设定文件 @return OrgInfo
	 * 返回类型 @throws
	 */
	OrgInfo getOrgInfoByUserId(String id);

	/**
	 * 
	 * @Title: getDicRangeInfo @Description: TODO(获取数据字典父节点信息) @param @return
	 * 设定文件 @return List<DicRangeInfo> 返回类型 @throws
	 */
	List<DicRangeInfo> getDicRangeInfo();

	/**
	 * 
	 * @Title: getDicInfo @Description: TODO(获取数据字典子节点信息) @param @return
	 * 设定文件 @return List<DicInfo> 返回类型 @throws
	 */
	List<DicInfo> getDicInfo();

	/**
	 * 
	 * @Title: getConfigInfo @Description: TODO(获取全局变量信息) @param @return
	 * 设定文件 @return List<ConfigInfo> 返回类型 @throws
	 */
	List<ConfigInfo> getConfigInfo();

	/**
	 * 
	 * @Title: getCityInfo @Description: TODO(获取行政区划信息) @param @return
	 * 设定文件 @return List<CityInfo> 返回类型 @throws
	 */
	List<CityInfo> getCityInfo();

	/**
	 * 
	 * @Title: getProductInfo @Description: TODO(获取当前组织机构有权限的产品信息) @param @param
	 * orgId @param @return 设定文件 @return List<ProductInfo> 返回类型 @throws
	 */
	List<ProductInfo> getProductInfo(String orgId);

	/**
	 * @return
	 * 
	 * @Title: getBusModelCodeAndVersion @Description:
	 * TODO(通过产品id和业务模型类型（BUM）获取业务模型版本号) @param @param id @param @param string
	 * 设定文件 @return void 返回类型 @throws
	 */
	ModelBusCodeAndVersion getBusModelCodeAndVersion(Map<String, String> modelVersionInfo);

	/**
	 * 
	 * @Title: getModelBusInfo @Description:
	 * TODO(通过code和version获取模型信息) @param @param code @param @param
	 * version @param @return 设定文件 @return ModelBusInfo 返回类型 @throws
	 */
	ModelBusInfo getModelBusInfo(Map<String, String> modelInfo);

	/**
	 * 
	 * @Title: getModelBusTitleInfo @Description:
	 * TODO(通过模型id获取模型分类信息) @param @param id @param @return 设定文件 @return
	 * List<ModelBusTitleInfo> 返回类型 @throws
	 */
	List<ModelBusTitleInfo> getModelBusTitleInfo(String id);

	/**
	 * 
	 * @Title: getModelBusColumnInfo @Description:
	 * TODO(通过模型分类id获取模型字段信息) @param @param id @param @return 设定文件 @return
	 * List<ModelBusColumnInfo> 返回类型 @throws
	 */
	List<ModelBusColumnInfo> getModelBusColumnInfo(String id);

	/**
	 * 
	 * @Title: getModelImgInfo @Description:
	 * TODO(通过code和version获取模型id) @param @param code @param @param
	 * version @param @return 设定文件 @return String 返回类型 @throws
	 */
	String getModelImgId(Map<String, String> modelClassInfo);

	/**
	 * 
	 * @Title: getModelImgInfo @Description:
	 * TODO(通过影像模型id获取影像模型分类信息) @param @param modelImgId @param @return
	 * 设定文件 @return List<ModelImgInfo> 返回类型 @throws
	 */
	List<ModelImgInfo> getModelImgInfo(String modelImgId);
	
	/** 
	* @Title: getModelBusInfoProp 
	* @Description: TODO(获取所有业务模型信息) 
	* @param @return    设定文件 
	* @return List<ModelBusInfoProp>    返回类型 
	* @throws 
	*/
	List<ModelBusInfo> getModelBusInfoProp();
	
	/** 
	* @Title: getModelBusTitleInfoProp 
	* @Description: TODO(获取所有业务模型分类信息) 
	* @param @return    设定文件 
	* @return List<ModelBusTitleInfoProp>    返回类型 
	* @throws 
	*/
	List<ModelBusTitleInfo> getModelBusTitleInfoProp();
	
	/** 
	* @Title: getModelBusColumnInfoProp 
	* @Description: TODO(获取所有业务模型字段信息) 
	* @param @return    设定文件 
	* @return List<ModelBusColumnInfoProp>    返回类型 
	* @throws 
	*/
	List<ModelBusColumnInfo> getModelBusColumnInfoProp();
	
	/** 
	* @Title: getModelImgInfoProp 
	* @Description: TODO(获取所有影像模型信息) 
	* @param @return    设定文件 
	* @return List<ModelImgInfoProp>    返回类型 
	* @throws 
	*/
	List<ModelImgInfo> getModelImgInfoProp();

	/**
	 * 
	 * @Title: getOrgDeptInfo @Description:
	 * TODO(通过当前登录人组织机构获取产品关联信息) @param @param orgId @param @return 设定文件 @return
	 * List<OrgDeptInfo> 返回类型 @throws
	 */
	List<OrgDeptInfo> getOrgDeptInfo(String orgId);

	/**
	 * 
	 * @Title: getProcdefInfo @Description:
	 * TODO(通过组织机构id获取产品信息中的流程key信息) @param @param orgId @param @return
	 * 设定文件 @return List<ProcdefInfo> 返回类型 @throws
	 */
	List<ProcdefInfo> getProcdefInfo(String orgId);

	/**
	 * @Title: getJurisdictionInfoPro @Description:
	 * TODO(获取当前权限信息) @param @return 设定文件 @return JurisdictionInfo 返回类型 @throws
	 */
	List<JurisdictionInfo> getJurisdictionInfoPro(Map<String, String> jurisdictionInfoReq);

	/**
	 * 
	 * @Title: getUserUpdateTimeInfoByUserId @Description:
	 * TODO(通过用户id获取用户更新时间) @param @param userId @param @return 设定文件 @return
	 * String 返回类型 @throws
	 */
	String getUserUpdateTimeInfoByUserId(String userId);

}
