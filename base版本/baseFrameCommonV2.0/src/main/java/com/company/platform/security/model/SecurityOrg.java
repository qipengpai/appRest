/******************************************************************
 *
 *    Package:     com.company.platform.security.model
 *
 *    Filename:    SecurityOrg.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2017
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     zhengjn
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月27日 下午5:33:58
 *
 *    Revision:
 *
 *    2017年3月27日 下午5:33:58
 *        - first revision
 *
 *****************************************************************/
package com.company.platform.security.model;

import java.io.Serializable;

/** 
* @ClassName: SecurityOrg 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月3日 下午2:20:35 
*  
*/
@SuppressWarnings("serial")
public class SecurityOrg implements Serializable{

	 private String id;//'主键'
	 
	 private String orgName;//'机构名称',
	 
	 private String orgCode;//'机构编码',
	 
	 private String pid;//'上级机构ID',
	 
	 private String eLevel;// '级别',
	 
	 private String isLeaf;//'是否为叶节点 0 是 1 否',
	 
	 private String isValid;//'是否可用 0 可用 1 不可用',
	 
	 private String eSort;//'排序',
	 
	 private String eType;//'机构类型 1.部门 2.公司',

	public String getId() {
		return id;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public String getPid() {
		return pid;
	}

	public String geteLevel() {
		return eLevel;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public String getIsValid() {
		return isValid;
	}

	public String geteSort() {
		return eSort;
	}

	public String geteType() {
		return eType;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void seteLevel(String eLevel) {
		this.eLevel = eLevel;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public void seteSort(String eSort) {
		this.eSort = eSort;
	}

	public void seteType(String eType) {
		this.eType = eType;
	}
}
