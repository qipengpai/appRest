package com.company.platform.restapi.service.loan.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.util.DateUtil;
import com.company.platform.restapi.dao.loan.LoanCodeMapper;
import com.company.platform.restapi.service.loan.ILoanCodeService;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: CustomerInfoServiceImp 
* @Description: TODO(获取贷款申请人信息实现类) 
* @author 王雪 
* @date 2018年1月24日 上午10:14:33 
*  
*/
@Service
public class LoanCodeServiceImpl implements ILoanCodeService {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanCodeServiceImpl.class);

	@Autowired
	private LoanCodeMapper loanCodeMapper;

	/*
	 * (非 Javadoc) <p>Title: getOrgInfoByUserId</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.company.platform.restapi.service.loan.ILoanCodeService#
	 * getOrgInfoByUserId()
	 */
	@Override
	public Map<String, String> getOrgInfoByUserId(String userId) throws BusinessException {
		//String userId = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		logger.info("通过用户id获取关联组织机构信息");
		Map<String, String> org = loanCodeMapper.getOrgIdByUserId(userId);
		if (org == null || "".equals(org.get("orgId").toString())) {
			logger.error("无获取贷款申请码权限");
			throw new BusinessException(ResponseConstants.LOAN_PRODUCT_USER_ORG_ERROR.getRetCode(),
					ResponseConstants.LOAN_PRODUCT_USER_ORG_ERROR.getRetMsg());
		}
		return org;
	}

	/*
	 * (非 Javadoc) <p>Title: getSysCode</p> <p>Description: </p>
	 * 
	 * @param type
	 * 
	 * @param orgCode
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see
	 * com.company.platform.restapi.service.loan.ILoanCodeService#getSysCode(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String getSysCode(String type, String orgCode) throws BusinessException {

		StringBuffer code = new StringBuffer();
		// 获取sys_code_generation中的借贷申请编码生成数据
		List<Map<String, String>> list = loanCodeMapper.selectSysCodeByType(type);
		if (list != null && list.size() > 0) {
			String PREFIX = "";
			String CURRENT_CODE = "";
			String VERSION = "";
			String DATE = "";
			for (Map<String, String> map : list) {
				if (StringUtils.equals("PREFIX", map.get("code"))) {
					PREFIX = map.get("name");
				} else if (StringUtils.equals("CURRENT_CODE", map.get("code"))) {
					CURRENT_CODE = map.get("name");
				} else if (StringUtils.equals("VERSION", map.get("code"))) {
					// VERSION = map.get("name");
				} else if (StringUtils.equals("DATE", map.get("code"))) {
					DATE = map.get("name");
				}
			}
			Map<String, String> info = new HashMap<String, String>();
			info.put("code", "DATE");
			info.put("type", type);
			if (StringUtils.isBlank(DATE)) {
				DATE = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
				info.put("name", DATE);
				loanCodeMapper.save(info);
				CURRENT_CODE = "001";
			} else {
				String today = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
				if (!today.equals(DATE)) {
					DATE = today;
					info.put("name", today);
					loanCodeMapper.updateVal(info);
					CURRENT_CODE = "001";
				}
			}
			code.append(PREFIX).append(StringUtils.defaultString(orgCode)).append(DATE).append(CURRENT_CODE)
					.append(VERSION);
			/* CURRENT_CODE字段数值+1（超过999加1，不足3位数前端补0），更新到数据库 */
			Integer CURRENT_CODE_INT = Integer.parseInt(CURRENT_CODE);
			/*
			 * if(++CURRENT_CODE_INT > 999) { CURRENT_CODE_INT = 1; }
			 */
			CURRENT_CODE_INT++;
			String CURRENT_CODE_Str = Integer.toString(CURRENT_CODE_INT);
			StringBuffer zeroTemp = new StringBuffer();
			if (CURRENT_CODE_Str.length() <= 3) {
				int len = 3 - CURRENT_CODE_Str.length();
				for (int i = 0; i < len; i++) {
					zeroTemp.append("0");
				}
				zeroTemp.append(CURRENT_CODE_Str);
			} else {
				zeroTemp.append(CURRENT_CODE_Str);
			}
			info.put("code", "CURRENT_CODE");
			info.put("name", zeroTemp.toString());
			loanCodeMapper.updateVal(info);
		}
		return "app" + code.toString();
	}

}
