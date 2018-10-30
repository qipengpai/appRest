package com.company.platform.restapi.model.loan;

import com.company.platform.base.model.base.BaseModel;

/**
 * @ClassName: MerchantCustomerRelation
 * @Description: TODO(商户信息)
 * @author yangxu
 * @date 2018年2月2日 下午2:09:07
 * 
 */
@SuppressWarnings("all")
public class MerchantCustomerRelation extends BaseModel {

	/**
	 * @Fields id : TODO(主键id)
	 */
	private String id;

	/**
	 * @Fields cid : TODO(客户id)
	 */
	private String cid;

	/**
	 * @Fields mid : TODO(商户id)
	 */
	private String mid;

	/**
	 * @Fields mOrgid : TODO(机构id)
	 */
	private String mOrgid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getmOrgid() {
		return mOrgid;
	}

	public void setmOrgid(String mOrgid) {
		this.mOrgid = mOrgid;
	}
}
