package com.company.platform.restapi.model.loan.code;

import java.util.List;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: WorkflowFunInfosResp 
* @Description: TODO(获得流程配置信息响应) 
* @author luyuchi
* @date 2018年4月27日 下午5:10:39 
*  
*/
public class WorkflowFunInfosResp extends BaseModel {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 2311034615931962813L;

	/** 
	* @Fields funInfos : TODO(配置信息) 
	*/
	private List<FunInfo> funInfos;

	/** 
	* @Fields esValueUp : TODO(人工估值上限) 
	*/
	private String esValueTop;

	/** 
	* @Fields esValueLow : TODO(人工估值下限) 
	*/
	private String esValueLow;

	public List<FunInfo> getFunInfos() {
		return funInfos;
	}

	public void setFunInfos(List<FunInfo> funInfos) {
		this.funInfos = funInfos;
	}

	public String getEsValueLow() {
		return esValueLow;
	}

	public void setEsValueLow(String esValueLow) {
		this.esValueLow = esValueLow;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEsValueTop() {
		return esValueTop;
	}

	public void setEsValueTop(String esValueTop) {
		this.esValueTop = esValueTop;
	}
}
