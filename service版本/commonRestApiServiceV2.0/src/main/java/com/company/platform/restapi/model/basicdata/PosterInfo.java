package com.company.platform.restapi.model.basicdata;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: PosterInfo
 * @Description: TODO(岗位信息)
 * @author liang
 * @date 2018年1月25日 下午5:27:33
 * 
 */
@SuppressWarnings("all")
public class PosterInfo extends BaseModel{

	/**
	 * @Fields posterId : TODO(岗位id)
	 */
	private String posterId;

	/**
	 * @Fields posterId : TODO(岗位id)
	 */
	private String posterName;
	/**
	 * @Fields posterCode : TODO(岗位编码)
	 */
	private String posterCode;

	/**
	 * @Fields posterInfoUpdateTime : TODO(岗位信息更新时间，时间格式：yyyy-MM-dd hh:mm:ss)
	 */
	private String posterInfoUpdateTime;

	public String getPosterId() {
		return posterId;
	}

	public void setPosterId(String posterId) {
		this.posterId = posterId;
	}

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public String getPosterCode() {
		return posterCode;
	}

	public void setPosterCode(String posterCode) {
		this.posterCode = posterCode;
	}

	public String getPosterInfoUpdateTime() {
		return posterInfoUpdateTime;
	}

	public void setPosterInfoUpdateTime(String posterInfoUpdateTime) {
		this.posterInfoUpdateTime = posterInfoUpdateTime;
	}

}
