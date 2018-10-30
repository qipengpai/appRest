package com.company.platform.restapi.model.loan.deal;

import java.util.List;

import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.collateral.CollateralInfo;
import com.company.platform.restapi.model.custom.ContactsInfo;
import com.company.platform.restapi.model.custom.CustomerInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.EntCustomerInfo;
import com.company.platform.restapi.model.custom.SelContactsInfo;
import com.company.platform.restapi.model.custom.personal.CustomerAssetInfo;
import com.company.platform.restapi.model.custom.personal.CustomerMateInfo;
import com.company.platform.restapi.model.guarantor.GuarantorInfo;
import com.company.platform.restapi.model.loan.LoanApplyInfoResp;
import com.company.platform.restapi.model.loan.handled.LoanProductApplyInfo;
import com.company.platform.restapi.model.modelmanager.BusColumnInfoModel;
import com.company.platform.restapi.model.modelmanager.BusInfoModel;
import com.company.platform.restapi.model.modelmanager.ImgDocModelDataInfo;
import com.company.platform.restapi.model.modelmanager.ImgDocModelInfo;
import com.company.platform.restapi.model.modelmanager.ModelBusDataInfo;
import com.company.platform.restapi.model.modelmanager.ModelBusTitleInfo;

/** 
* @ClassName: LoanApplyAuditingResp 
* @Description: TODO(借贷申请待办任务返回参数) 
* @author wangxue 
* @date 2018年7月21日 上午11:00:57 
*  
*/
@SuppressWarnings("all")
public class LoanApplyAuditingResp extends LoanApplyInfoResp {

	/** 
	* @Fields htmlUrl : TODO(html5页面url地址) 
	*/
	private String htmlUrl;

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

}
