package com.company.platform.restapi.model.loan.handled;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: ImageDataInfo 
* @Description: TODO(影像资料数据信息) 
* @author wangxue 
* @date 2018年7月19日 下午5:04:00 
*  
*/
@SuppressWarnings("all")
public class ImageDataInfo extends BaseModel {

	/** 
	* @Fields id : TODO(图片id) 
	*/
	private String id;
	/** 
	* @Fields classId : TODO(影像模型分类id) 
	*/
	private String classId;
	/** 
	* @Fields url : TODO(文件全路径) 
	*/
	private String url;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
