package com.company.platform.base.model.activiti;


import java.io.Serializable;
import java.util.Date;

/**
 * 产品工作流程配置.
 *
 * @version V1.0
 * @ClassName:  ActTaskset
 * @Description:TODO(这里用一句话描述这个类的作用) 
 * @author: ou
 * @date:   2016年8月1日 上午11:35:02
 */
public class ActTaskset implements Serializable {
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = -278893915379400113L;
	/**id.*/
    private String id;
    /**工作流ID.*/
    private String wfid;
    /**工作流KEY.*/
    private String wfkey;
    /**产品ID.*/
    private String productid;
    /**用户IDS.*/
    private String userids;
    /**组织IDS.*/
    private String organizationids;
    /**功能IDS.*/
    private String functionids;
    /**权限IDS.*/
    private String authids;
    /**用户ID.*/
    private String userid;
    /**操作时间.*/
    private Date operdate;
    /**节点名称.*/
    private String taskName;
    /**节点key.*/
    private String taskkey;
    /**节点排序.*/
    private Integer taskSort;
    /**角色ID.*/
    private String roleids;
    /**岗位IDS*/
    private String posterids;
    /**节点类型.*/
    private String taskType;

    /**
     * 获取 id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 工作流ID.
     *
     * @return the 工作流ID
     */
    public String getWfid() {
        return wfid;
    }

    /**
     * 设置 工作流ID.
     *
     * @param wfid the new 工作流ID
     */
    public void setWfid(String wfid) {
        this.wfid = wfid;
    }

    /**
     * 获取 工作流KEY.
     *
     * @return the 工作流KEY
     */
    public String getWfkey() {
        return wfkey;
    }

    /**
     * 设置 工作流KEY.
     *
     * @param wfkey the new 工作流KEY
     */
    public void setWfkey(String wfkey) {
        this.wfkey = wfkey;
    }

    /**
     * 获取 产品ID.
     *
     * @return the 产品ID
     */
    public String getProductid() {
        return productid;
    }

    /**
     * 设置 产品ID.
     *
     * @param productid the new 产品ID
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * 获取 用户IDS.
     *
     * @return the 用户IDS
     */
    public String getUserids() {
        return userids;
    }

    /**
     * 设置 用户IDS.
     *
     * @param userids the new 用户IDS
     */
    public void setUserids(String userids) {
        this.userids = userids;
    }

    /**
     * 获取 组织IDS.
     *
     * @return the 组织IDS
     */
    public String getOrganizationids() {
        return organizationids;
    }

    /**
     * 设置 组织IDS.
     *
     * @param organizationids the new 组织IDS
     */
    public void setOrganizationids(String organizationids) {
        this.organizationids = organizationids;
    }

    /**
     * 获取 功能IDS.
     *
     * @return the 功能IDS
     */
    public String getFunctionids() {
        return functionids;
    }

    /**
     * 设置 功能IDS.
     *
     * @param functionids the new 功能IDS
     */
    public void setFunctionids(String functionids) {
        this.functionids = functionids;
    }

    /**
     * 获取 权限IDS.
     *
     * @return the 权限IDS
     */
    public String getAuthids() {
        return authids;
    }

    /**
     * 设置 权限IDS.
     *
     * @param authids the new 权限IDS
     */
    public void setAuthids(String authids) {
        this.authids = authids;
    }

    /**
     * 获取 用户ID.
     *
     * @return the 用户ID
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置 用户ID.
     *
     * @param userid the new 用户ID
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取 操作时间.
     *
     * @return the 操作时间
     */
    public Date getOperdate() {
        return operdate;
    }

    /**
     * 设置 操作时间.
     *
     * @param operdate the new 操作时间
     */
    public void setOperdate(Date operdate) {
        this.operdate = operdate;
    }

	/**
	 * 获取 节点名称.
	 *
	 * @return the 节点名称
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * 设置 节点名称.
	 *
	 * @param taskName the new 节点名称
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	
	/**
	 * 获取 节点key.
	 *
	 * @return the 节点key
	 */
	public String getTaskkey() {
		return taskkey;
	}

	/**
	 * 设置 节点key.
	 *
	 * @param taskkey the new 节点key
	 */
	public void setTaskkey(String taskkey) {
		this.taskkey = taskkey;
	}

	/**
	 * 获取 节点排序.
	 *
	 * @return the 节点排序
	 */
	public Integer getTaskSort() {
		return taskSort;
	}

	/**
	 * 设置 节点排序.
	 *
	 * @param taskSort the new 节点排序
	 */
	public void setTaskSort(Integer taskSort) {
		this.taskSort = taskSort;
	}

    /**
     * @return the roleids
     */
    public String getRoleids() {
        return roleids;
    }

    /**
     * @param roleids the roleids to set
     */
    public void setRoleids(String roleids) {
        this.roleids = roleids;
    }

	public String getPosterids() {
		return posterids;
	}

	public void setPosterids(String posterids) {
		this.posterids = posterids;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
}