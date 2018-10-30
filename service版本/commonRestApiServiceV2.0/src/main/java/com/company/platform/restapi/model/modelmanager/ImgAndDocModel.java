package com.company.platform.restapi.model.modelmanager;

import java.util.Date;

/** 
* @ClassName: ImgAndDocModel 
* @Description: TODO(影像模型类) 
* @author luyuchi
* @date 2018年1月29日 上午11:42:16 
*  
*/
public class ImgAndDocModel {
	
	/** 
	* @Fields id : TODO(主键) 
	*/ 
	private String id;
	
	/** 
	* @Fields code : TODO(编码，对外接口的模型标识) 
	*/ 
	private String code;
	
	/** 
	* @Fields name : TODO(模型名称) 
	*/ 
	private String name;
	
	/** 
	* @Fields expl : TODO(模型描述) 
	*/ 
	private String expl;
	
	/** 
	* @Fields registrant : TODO(创建人) 
	*/ 
	private String registrant;
	
	/** 
	* @Fields createTime : TODO(创建时间) 
	*/ 
	private Date createTime;
	
	/** 
	* @Fields useStatus : TODO(使用状态，true已使用，false未使用) 
	*/ 
	private Integer useStatus;
	
	/** 
	* @Fields version : TODO(版本号，版本标识，对外接口的版本标识) 
	*/ 
	private String version;
	
	/** 
	* @Fields publishStatus : TODO(发布状态，true已发布，false未发布) 
	*/ 
	private Integer publishStatus;
	
	/** 
	* @Fields publishTime : TODO(发布时间) 
	*/ 
	private Date publishTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getExpl() {
		return expl;
	}

	public void setExpl(String expl) {
		this.expl = expl;
	}

	public String getRegistrant() {
		return registrant;
	}

	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
}
