package com.company.platform.restapi.model.loan.handled;

import com.company.platform.base.model.base.BaseModel;

@SuppressWarnings("all")
public class ImageInfo extends BaseModel {

	/** 
	* @Fields id : TODO(图片id) 
	*/
	private String id;
	/** 
	* @Fields classId : TODO(影像模型分类id) 
	*/
	private String classId;
	/** 
	* @Fields filePath : TODO(文件路径) 
	*/
	private String filePath;
	/** 
	* @Fields fileName : TODO(文件名称) 
	*/
	private String fileName;

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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
