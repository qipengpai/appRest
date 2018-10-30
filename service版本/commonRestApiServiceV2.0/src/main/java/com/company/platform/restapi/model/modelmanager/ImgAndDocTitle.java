package com.company.platform.restapi.model.modelmanager;

/** 
* @ClassName: ImgAndDocTitle 
* @Description: TODO(影像模型分类) 
* @author luyuchi
* @date 2018年1月29日 上午11:42:45 
*  
*/
public class ImgAndDocTitle {
	
	/** 
	* @Fields id : TODO(主键) 
	*/ 
	private String id;
	
	/** 
	* @Fields code : TODO(标题编码) 
	*/ 
	private String code;
	
	/** 
	* @Fields name : TODO(标题名称，在表单中显示) 
	*/ 
	private String name;
	
	/** 
	* @Fields expl : TODO(标题描述) 
	*/ 
	private String expl;
	
	/** 
	* @Fields imgDocId : TODO(关联影像模型ID) 
	*/ 
	private String imgDocId;
	
	/** 
	* @Fields sort : TODO(排序) 
	*/ 
	private int sort;
	/** 
	* @Fields share : TODO(是否共享 0不共享 1共享) 
	*/ 
	private  String share;
	/** 
	* @Fields size : TODO(文件最大上传数量) 
	*/ 
	private int size;
	/** 
	* @Fields floor : TODO(文件数上传下限) 
	*/ 
	private int floor;

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

	public String getImgDocId() {
		return imgDocId;
	}

	public void setImgDocId(String imgDocId) {
		this.imgDocId = imgDocId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
}
