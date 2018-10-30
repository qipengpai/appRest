package com.company.platform.restapi.service.loan.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.platform.restapi.dao.custom.CorporationInfoMapper;
import com.company.platform.restapi.dao.custom.CustomerLocationInfoMapper;
import com.company.platform.restapi.dao.custom.CustomerPublicInfoMapper;
import com.company.platform.restapi.model.custom.CorporationCustomerForm;
import com.company.platform.restapi.model.custom.CorporationInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.service.loan.ICorporationCustomerService;

/**
 * @ClassName: CorporationCustomerServiceImpl
 * @Description: TODO(增加企业用户信息)
 * @author yangxu
 * @date 2018年1月30日 上午8:41:36
 * 
 */
@Service
public class CorporationCustomerServiceImpl implements ICorporationCustomerService {

	@Autowired
	CustomerPublicInfoMapper customerPublicInfoMapper;

	@Autowired
	CustomerLocationInfoMapper customerLocationInfoMapper;

	@Autowired
	CorporationInfoMapper corporationInfoMapper;

	@Transactional
	@Override
	public Boolean merge(CorporationCustomerForm form, String userId) {
		Boolean edit = false;
		CustomerPublicInfo customerPublic = customerPublicInfoMapper.selectByPrimaryKey(form.getCustomerId());
		if (null != customerPublic) {
			edit = true;
			// 清除一些纵表数据 重新加载
			customerLocationInfoMapper.deleteByCustomerId(form.getCustomerId());
		} else {
			customerPublic = new CustomerPublicInfo();
			customerPublic.setId(form.getCustomerId());
			customerPublic.setCreateUserId(userId);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(Calendar.getInstance().getTime());
			customerPublic.setCreateTime(dateString);
			customerPublic.setUpdateTime(dateString);
			customerPublic.setDeleteStatus("0");
			// 客户可见度控制 默认只有机构内可见
			customerPublic.setAuthority("1");
		}
		// 生成最新的公共基础信息
		customerPublic = mergeCustomerPublic(customerPublic, form);
		// 更新customer_suspected_risk表的customerId（根据客户类型，证件类型，证件号码）
		customerPublicInfoMapper.updateCustomerIdRisk(customerPublic.getId(), customerPublic.geteType(),
				customerPublic.getCredentialType(), customerPublic.getCredentialNo());

		// 生成最新的企业用户信息表
		CorporationInfo corporationInfo = generateCorporationInfo(form, edit);

		if (edit) {
			// 更新公共基础表信息
			customerPublicInfoMapper.updateByPrimaryKey(customerPublic);
			corporationInfoMapper.updateByPrimaryKey(corporationInfo);

		} else {
			// 新增的情况
			customerPublicInfoMapper.insertSelective(customerPublic);
			corporationInfoMapper.insertSelective(corporationInfo);
		}
		return edit;
	}

	private CorporationInfo generateCorporationInfo(CorporationCustomerForm form, Boolean edit) {
		CorporationInfo info;
		String customerId = form.getCustomerId();
		if (!edit) {
			info = new CorporationInfo();
			info.setId(customerId);
		} else {
			info = corporationInfoMapper.selectByPrimaryKey(customerId);
		}

		if (StringUtils.isNotBlank(form.getCustomerName())) {
			info.setCustomerName(form.getCustomerName());
		}
		if (StringUtils.isNotBlank(form.getCorporationName())) {
			info.setCorporationName(form.getCorporationName());
		}
		if (StringUtils.isNotBlank(form.getTaxId())) {
			info.setTaxId(form.getTaxId());
		}
		if (StringUtils.isNotBlank(form.getIndustryType())) {
			info.setIndustryType(form.getIndustryType());
		}
		info.setStaffCount(null != form.getStaffCount() ? form.getStaffCount().toString() : null);
		if (form.getRegisterTime() != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(form.getRegisterTime());
			info.setRegisterTime(dateString);
		}
		if (form.getRegisterCapital() != null) {
			info.setRegisterCapital(form.getRegisterCapital().toString());
		}
		if (StringUtils.isNotBlank(form.getLrName())) {
			info.setLrName(form.getLrName());
		}
		if (StringUtils.isNotBlank(form.getLrSex())) {
			info.setLrSex(form.getLrSex());
		}
		if (StringUtils.isNotBlank(form.getLrCertType())) {
			info.setLrCertType(form.getLrCertType());
		}
		if (StringUtils.isNotBlank(form.getLrCertNo())) {
			info.setLrCertNo(form.getLrCertNo());
		}
		info.setLrAge(null != form.getLrAge() ? form.getLrAge().toString() : null);
		if (StringUtils.isNotBlank(form.getLrMobile())) {
			info.setLrMobile(form.getLrMobile());
		}

		info.setLicense(form.getLicense());

		String beginRunTime = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (form.getBeginRunTime() != null) {
			beginRunTime = formatter.format(form.getBeginRunTime());
			info.setBeginRunTime(beginRunTime);
		}

		String turnover = "0";
		if (form.getTurnover() != null) {
			turnover = form.getTurnover().toString();
		}
		info.setTurnover(turnover);
		info.setSiteOwnership(form.getSiteOwnership());
		info.setApplicantType(form.getApplicantType());
		info.setTelephone(form.getTelephone());
		info.setManageRange(form.getManageRange());
		return info;
	}

	private CustomerPublicInfo mergeCustomerPublic(CustomerPublicInfo customerPublic, CorporationCustomerForm form) {
		customerPublic.setWorkType("");
		if (StringUtils.isNotBlank(form.getCustomerNo())) {
			customerPublic.setCustomerNo(form.getCustomerNo());
		}
		if (StringUtils.isNotBlank(form.getCustomerName())) {
			customerPublic.setCustomerName(form.getCustomerName());
		}
		if (form.getIfRestrict() != null) {
			String isRestricted = "0";
			if (form.getIfBlack().equals("1")) {
				isRestricted = "1";
			}
			customerPublic.setIsRestricted(isRestricted);
		}
		if (form.getIfBlack() != null) {
			String isBlackList = "0";
			if (form.getIfBlack().equals("1")) {
				isBlackList = "1";
			}
			customerPublic.setIsBlackList(isBlackList);
		}
		if (StringUtils.isNotBlank(form.getOrgId())) {
			customerPublic.setOrgId(form.getOrgId());
		}
		if (StringUtils.isNotBlank(form.getCustomerStatus())) {
			customerPublic.seteStatus(form.getCustomerStatus());
		}
		if (StringUtils.isNotBlank(form.getCredentialType())) {
			customerPublic.setCredentialType(form.getCredentialType());
		}
		if (StringUtils.isNotBlank(form.getCredentialNo())) {
			customerPublic.setCredentialNo(form.getCredentialNo());
		}
		customerPublic.seteType("1");// 企业客户
		return customerPublic;
	}
}
