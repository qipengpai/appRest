package com.company.platform.restapi.model.collateral;

import java.util.List;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: CollateralTemplateResp 
* @Description: TODO(押品模板信息返回参数) 
* @author 王雪 
* @date 2018年5月23日 下午2:49:54 
*  
*/
@SuppressWarnings("all")
public class CollateralTemplateResp extends BaseModel {

	/** 
	* @Fields title : TODO(标题) 
	*/
	private String title;

	/** 
	* @Fields customerWarrant : TODO(押品模型元素列表) 
	*/
	private List<TemplateElement> templateElement;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TemplateElement> getTemplateElement() {
		return templateElement;
	}

	public void setTemplateElement(List<TemplateElement> templateElement) {
		this.templateElement = templateElement;
	}

}
