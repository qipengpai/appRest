package com.company.platform.restapi.model.workbench;

import com.company.platform.base.model.base.BaseModel;

/**
 * 
 * @ClassName: WorkBenchResp
 * @Description: TODO(工作台数量信息结果集)
 * @author liang
 * @date 2018年1月24日 下午8:28:18
 *
 */
@SuppressWarnings("all")
public class WorkBenchResp extends BaseModel {

	/**
	 * @Fields temporaryCount : TODO(暂存进件数量)
	 */
	private String temporaryCount;

	/**
	 * @Fields todealCount : TODO(待办进件数量)
	 */
	private String todealCount;

	/**
	 * @Fields unclaimedCount : TODO(待领取进件数量)
	 */
	private String unclaimedCount;

	/**
	 * @Fields workedCount : TODO(已办进件数量)
	 */
	private String workedCount;

	public String getTemporaryCount() {
		return temporaryCount;
	}

	public void setTemporaryCount(String temporaryCount) {
		this.temporaryCount = temporaryCount;
	}

	public String getTodealCount() {
		return todealCount;
	}

	public void setTodealCount(String todealCount) {
		this.todealCount = todealCount;
	}

	public String getUnclaimedCount() {
		return unclaimedCount;
	}

	public void setUnclaimedCount(String unclaimedCount) {
		this.unclaimedCount = unclaimedCount;
	}

	public String getWorkedCount() {
		return workedCount;
	}

	public void setWorkedCount(String workedCount) {
		this.workedCount = workedCount;
	}

}
