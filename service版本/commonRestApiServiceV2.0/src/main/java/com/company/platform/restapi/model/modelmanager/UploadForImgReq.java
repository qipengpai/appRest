package com.company.platform.restapi.model.modelmanager;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.validated.First;

public class UploadForImgReq extends BaseHttpParamsAppReq {

	/** 
	* @Fields serialVersionUID : TODO(对象序列化版本号) 
	*/
	private static final long serialVersionUID = -6643966968019603535L;

	/** 
	* @Fields loanProductId : TODO(产品Id) 
	*/
	@JsonProperty("loanProductId")
	//@NotEmpty(message = "产品ID不能为空", groups = { First.class })
	private String loanProductId;

	/** 
	* @Fields loanProductApplyId : TODO(借贷申请id) 
	*/
	@JsonProperty("loanProductApplyId")
	//@NotEmpty(message = "借贷申请ID不能为空", groups = { First.class })
	private String loanProductApplyId;

	/** 
	* @Fields name : TODO(文件名称) 
	*/
	@JsonProperty("name")
	//@NotEmpty(message = "文件名称不能为空", groups = { First.class })
	private String name;

	/** 
	* @Fields modelImgId : TODO(影像分类id) 
	*/
	@JsonProperty("imgClassId")
	//@NotEmpty(message = "影像分类ID不能为空", groups = { First.class })
	private String imgClassId;

	/** 
	* @Fields share : TODO(文件是否 共享) 
	*/
	@JsonProperty("share")
	//@NotEmpty(message = "文件是否 共享不能为空", groups = { First.class })
	private String share;

	/** 
	* @Fields orgId : TODO(机构ID) 
	*/
	@JsonProperty("orgId")
	private String orgId;

	/** 
	* @Fields id : TODO(图片id) 
	*/
	@JsonProperty("id")
	//@NotEmpty(message = "图片id不能为空", groups = { First.class })
	private String id;

	public String getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}

	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}

	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgClassId() {
		return imgClassId;
	}

	public void setImgClassId(String imgClassId) {
		this.imgClassId = imgClassId;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
