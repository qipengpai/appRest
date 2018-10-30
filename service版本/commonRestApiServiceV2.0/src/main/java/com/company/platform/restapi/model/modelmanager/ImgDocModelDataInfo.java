package com.company.platform.restapi.model.modelmanager;

import com.company.platform.base.model.base.BaseModel;

/** 
* @ClassName: ImgDocModelDataInfo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lifeng 
* @date 2018年1月31日 上午11:00:16 
*  
*/
@SuppressWarnings("all")
public class ImgDocModelDataInfo extends BaseModel{

	/** 
	* @Fields id : TODO(主键id) 
	*/ 
	private String id;
	/** 
	* @Fields recordId : TODO(相关联记录ID) 
	*/ 
	private String recordId;
	
	/** 
	* @Fields extension : TODO(文件扩展名) 
	*/ 
	private String extension;
	
	/** 
	* @Fields fileName : TODO(文件名称) 
	*/ 
	private String fileName;
	
	/** 
	* @Fields filePath : TODO(文件路径) 
	*/ 
	
	private String filePath;
	
	/** 
	* @Fields updateTime : TODO(更新时间) 
	*/ 
	private String updateTime;

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	
}
