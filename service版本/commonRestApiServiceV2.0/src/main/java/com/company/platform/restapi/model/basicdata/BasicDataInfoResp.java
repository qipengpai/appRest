package com.company.platform.restapi.model.basicdata;

import java.util.List;

import com.company.platform.base.model.base.BaseModel;

/**
 * 
 * @ClassName: BasicDataRoleInfoResp
 * @Description: TODO(更新基础数据用户信息结果集)
 * @author liang
 * @date 2018年1月24日 下午8:28:18
 *
 */
@SuppressWarnings("all")
public class BasicDataInfoResp extends BaseModel {

	/**
	 * @Fields userInfo : TODO(用户信息)
	 */
	private UserInfo userInfo;

	/**
	 * @Fields roleInfo : TODO(用户角色信息)
	 */
	private List<RoleInfo> roleInfo;

	/**
	 * @Fields posterInfo : TODO(用户岗位信息)
	 */
	private List<PosterInfo> posterInfo;

	/**
	 * @Fields orgInfo : TODO(用户组织机构信息)
	 */
	private OrgInfo orgInfo;

	/**
	 * @Fields dicRangeInfo : TODO(数据字典父节点信息)
	 */
	private List<DicRangeInfo> dicRangeInfo;

	/**
	 * @Fields dicInfo : TODO(数据字典子节点信息)
	 */
	private List<DicInfo> dicInfo;

	/**
	 * @Fields configInfo : TODO(全局变量信息)
	 */
	private List<ConfigInfo> configInfo;

	/**
	 * @Fields cityInfo : TODO(行政区划信息)
	 */
	private List<CityInfo> cityInfo;

	/**
	 * @Fields productInfo : TODO(产品基本信息)
	 */
	private List<ProductInfo> productInfo;

	/**
	 * @Fields modelBusInfo : TODO(获取所有业务模型信息)
	 */
	private List<ModelBusInfo> modelBusInfo;

	/**
	 * @Fields modelBusTitleInfo : TODO(获取所有业务模型分类信息)
	 */
	private List<ModelBusTitleInfo> modelBusTitleInfo;

	/**
	 * @Fields modelBusColumnInfo : TODO(获取所有业务模型字段信息)
	 */
	private List<ModelBusColumnInfo> modelBusColumnInfo;

	/**
	 * @Fields modelImgInfo : TODO(获取所有影像模型信息)
	 */
	private List<ModelImgInfo> modelImgInfo;
	
	/**
	 * @Fields orgDeptInfo : TODO(产品对应当前用户组织机构关联信息)
	 */
	private List<OrgDeptInfo> orgDeptInfo;

	/**
	 * @Fields procdefInfo : TODO(产品流程信息)
	 */
	private List<ProcdefInfo> procdefInfo;

	/**
	 * @Fields jurisdictionInfo : TODO(权限信息)
	 */
	private List<JurisdictionInfo> jurisdictionInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<RoleInfo> getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(List<RoleInfo> roleInfo) {
		this.roleInfo = roleInfo;
	}

	public List<PosterInfo> getPosterInfo() {
		return posterInfo;
	}

	public void setPosterInfo(List<PosterInfo> posterInfo) {
		this.posterInfo = posterInfo;
	}

	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public List<DicRangeInfo> getDicRangeInfo() {
		return dicRangeInfo;
	}

	public void setDicRangeInfo(List<DicRangeInfo> dicRangeInfo) {
		this.dicRangeInfo = dicRangeInfo;
	}

	public List<DicInfo> getDicInfo() {
		return dicInfo;
	}

	public void setDicInfo(List<DicInfo> dicInfo) {
		this.dicInfo = dicInfo;
	}

	public List<ConfigInfo> getConfigInfo() {
		return configInfo;
	}

	public void setConfigInfo(List<ConfigInfo> configInfo) {
		this.configInfo = configInfo;
	}

	public List<CityInfo> getCityInfo() {
		return cityInfo;
	}

	public void setCityInfo(List<CityInfo> cityInfo) {
		this.cityInfo = cityInfo;
	}

	public List<ProductInfo> getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(List<ProductInfo> productInfo) {
		this.productInfo = productInfo;
	}

	public List<ModelBusInfo> getModelBusInfo() {
		return modelBusInfo;
	}

	public void setModelBusInfo(List<ModelBusInfo> modelBusInfo) {
		this.modelBusInfo = modelBusInfo;
	}

	public List<ModelBusTitleInfo> getModelBusTitleInfo() {
		return modelBusTitleInfo;
	}

	public void setModelBusTitleInfo(List<ModelBusTitleInfo> modelBusTitleInfo) {
		this.modelBusTitleInfo = modelBusTitleInfo;
	}

	public List<ModelBusColumnInfo> getModelBusColumnInfo() {
		return modelBusColumnInfo;
	}

	public void setModelBusColumnInfo(List<ModelBusColumnInfo> modelBusColumnInfo) {
		this.modelBusColumnInfo = modelBusColumnInfo;
	}

	public List<ModelImgInfo> getModelImgInfo() {
		return modelImgInfo;
	}

	public void setModelImgInfo(List<ModelImgInfo> modelImgInfo) {
		this.modelImgInfo = modelImgInfo;
	}

	public List<OrgDeptInfo> getOrgDeptInfo() {
		return orgDeptInfo;
	}

	public void setOrgDeptInfo(List<OrgDeptInfo> orgDeptInfo) {
		this.orgDeptInfo = orgDeptInfo;
	}

	public List<ProcdefInfo> getProcdefInfo() {
		return procdefInfo;
	}

	public void setProcdefInfo(List<ProcdefInfo> procdefInfo) {
		this.procdefInfo = procdefInfo;
	}

	public List<JurisdictionInfo> getJurisdictionInfo() {
		return jurisdictionInfo;
	}

	public void setJurisdictionInfo(List<JurisdictionInfo> jurisdictionInfo) {
		this.jurisdictionInfo = jurisdictionInfo;
	}

}
