package com.company.platform.restapi.validated.loan;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.security.model.SecurityOrg;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: ProductApplyInfoOrgValidate 
* @Description: TODO(校验数据信息同步) 
* @author yangxu 
* @date 2018年2月1日 上午10:34:04 
*  
*/
@Service
public class ProductApplyInfoOrgValidate {
	/**
	 * 
	* @Title: productApplyInfoOrgValidate 
	* @Description: TODO(验证数据是否同步（组织机构）) 
	* @param @param customerOrgId 当前登录用户id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public void productApplyInfoOrgValidate(String orgId) throws BusinessException {
		List<SecurityOrg> orgList = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal()).getSecurityOrg();
		boolean flag = true;
		if (orgList != null && orgList.size() > 0) {
			for (SecurityOrg org : orgList) {
				if (org.getId().equals(orgId)) {
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			throw new BusinessException(ResponseConstants.ORGID_NOT_SYN.getRetCode(),
					ResponseConstants.ORGID_NOT_SYN.getRetMsg());
		}
	}
}
