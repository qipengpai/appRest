package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: OrganizationInfo
 * @Description: TODO(组织机构信息)
 * @author yangxu
 * @date 2018年2月2日 下午1:37:33
 * 
 */
@SuppressWarnings("all")
public class OrganizationInfo extends BaseModel {

	/**
	 * @Fields id : TODO(主键)
	 */
	private String id;
	/**
	 * @Fields orgName : TODO(机构名称)
	 */
	private String orgName;
	/**
	 * @Fields orgCode : TODO(机构编码)
	 */
	private String orgCode;
	/**
	 * @Fields pid : TODO(上级机构ID)
	 */
	private String pid;
	/**
	 * @Fields eLevel : TODO(级别)
	 */
	private String eLevel;
	/**
	 * @Fields isLeaf : TODO(是否为叶节点)
	 */
	private String isLeaf;
	/**
	 * @Fields isValid : TODO(是否可用)
	 */
	private String isValid;
	/**
	 * @Fields eSort : TODO(排序)
	 */
	private String eSort;
	/**
	 * @Fields eType : TODO(机构类型 1.部门 2.公司)
	 */
	private String eType;
	/**
	 * @Fields updateTime : TODO(更新时间)
	 */
	private String updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String geteLevel() {
		return eLevel;
	}

	public void seteLevel(String eLevel) {
		this.eLevel = eLevel;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String geteSort() {
		return eSort;
	}

	public void seteSort(String eSort) {
		this.eSort = eSort;
	}

	public String geteType() {
		return eType;
	}

	public void seteType(String eType) {
		this.eType = eType;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
