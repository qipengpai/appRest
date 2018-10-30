package com.company.platform.base.model.sys;

public class Organization {
    /**
     *主键
     */
    private String id;

    /**
     *机构名称
     */
    private String orgName;
    /**
     * 机构编码
     */
    private String orgCode;
    /**
     *上级机构ID
     */
    private String pid;

    /**
     *级别
     */
    private Integer eLevel;

    /**
     *是否为叶节点
     */
    private Boolean isLeaf;

    /**
     *是否可用
     */
    private Boolean isValid;

    /**
     *排序
     */
    private Integer eSort;

    /**
     *机构类型 1.部门 2.公司
     */
    private Integer eType;

    /**
     *主键
     */
    public String getId() {
        return id;
    }

    /**
     *主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *机构名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     *机构名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
	 * get[机构编码]
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * set[机构编码]
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
     *上级机构ID
     */
    public String getPid() {
        return pid;
    }

    /**
     *上级机构ID
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     *级别
     */
    public Integer geteLevel() {
        return eLevel;
    }

    /**
     *级别
     */
    public void seteLevel(Integer eLevel) {
        this.eLevel = eLevel;
    }

    /**
     *是否为叶节点
     */
    public Boolean getIsLeaf() {
        return isLeaf;
    }

    /**
     *是否为叶节点
     */
    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     *是否可用
     */
    public Boolean getIsValid() {
        return isValid;
    }

    /**
     *是否可用
     */
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    /**
     *排序
     */
    public Integer geteSort() {
        return eSort;
    }

    /**
     *排序
     */
    public void seteSort(Integer eSort) {
        this.eSort = eSort;
    }

    /**
     *机构类型 1.部门 2.公司
     */
    public Integer geteType() {
        return eType;
    }

    /**
     *机构类型 1.部门 2.公司
     */
    public void seteType(Integer eType) {
        this.eType = eType;
    }
}