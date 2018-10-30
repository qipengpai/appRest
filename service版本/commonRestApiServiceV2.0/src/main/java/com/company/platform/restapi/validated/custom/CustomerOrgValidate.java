package com.company.platform.restapi.validated.custom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.util.MatchUtil;
import com.company.platform.restapi.dao.custom.CustomerInfoMapper;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationCustomerContactinfo;
import com.company.platform.restapi.model.custom.enterprise.EnterpriseCustomerReq;
import com.company.platform.restapi.model.custom.personal.CustomerAssetInfo;
import com.company.platform.restapi.model.custom.personal.CustomerContactInfo;
import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;
import com.company.platform.restapi.model.custom.personal.CustomerRelationshipInfo;
import com.company.platform.restapi.model.custom.personal.PersonalCustomerBaseInfo;
import com.company.platform.restapi.model.custom.personal.PersonalCustomerReq;
import com.company.platform.security.model.SecurityOrg;
import com.company.platform.security.model.SecurityUser;

@Service
public class CustomerOrgValidate {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(CustomerOrgValidate.class);

	@Autowired
	CustomerInfoMapper customerInfoMapper;

	/**
	 * 
	* @Title: customerOrgValidate 
	* @Description: TODO(验证数据是否同步（组织机构）) 
	* @param @param customerOrgId 当前登录用户id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public void customerOrgValidate(String customerOrgId) throws BusinessException {
		List<SecurityOrg> orgList = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal()).getSecurityOrg();
		boolean flag = true;
		if (orgList != null && orgList.size() > 0) {
			for (SecurityOrg org : orgList) {
				if (org.getId().equals(customerOrgId)) {
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			logger.error("数据需要同步（机构）");
			throw new BusinessException(ResponseConstants.ORGID_NOT_SYN.getRetCode(),
					ResponseConstants.ORGID_NOT_SYN.getRetMsg());
		}
	}

	/** 
	* @Title: persionCustomerValidate 
	* @Description: TODO(个人客户信息校验) 
	* @param @param personalCustomerReq
	* @param @param type
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public PersonalCustomerReq persionCustomerValidate(PersonalCustomerReq personalCustomerReq, String type)
			throws BusinessException {
		List<CustomerContactInfo> contactList = personalCustomerReq.getCustomerContactInfo();
		List<CustomerLocationInfo> locationList = personalCustomerReq.getCustomerLocationInfo();
		PersonalCustomerBaseInfo personal = personalCustomerReq.getPersonalCustomerBaseInfo();
		if ("online".equals(type)) {
			// 校验客户个人信息
			if (null == personal) {
				throw new BusinessException(ResponseConstants.PERSONAL_CUSTOMER_NOT_EMPTY.getRetCode(),
						ResponseConstants.PERSONAL_CUSTOMER_NOT_EMPTY.getRetMsg());
			}
		}
		// 配偶信息
		if (personal != null && StringUtils.isNotEmpty(personal.getMartialStatus())
				&& ("2".equals(personal.getMartialStatus()) || "3".equals(personal.getMartialStatus()))) {
			// 客户为已婚，需完善配偶信息
			if (null == personalCustomerReq.getCustomerMateInfo()) {
				if ("online".equals(type)) {
					throw new BusinessException(ResponseConstants.CUSTOMER_MATE_NOT_EMPTY.getRetCode(),
							ResponseConstants.CUSTOMER_MATE_NOT_EMPTY.getRetMsg());
				}
			}
		} else {
			personalCustomerReq.setCustomerMateInfo(null);
		}
		// 资产信息
		CustomerAssetInfo asset = personalCustomerReq.getCustomerAssetInfo();
		if (asset != null) {
			if (StringUtils.isNotEmpty(asset.getHasHouse())) {
				if (!"0".equals(asset.getHasHouse()) && !"1".equals(asset.getHasHouse())) {
					throw new BusinessException(ResponseConstants.HAS_HOUSE_VALUE_ERROR.getRetCode(),
							ResponseConstants.HAS_HOUSE_VALUE_ERROR.getRetMsg());
				}
			}
			if (StringUtils.isNotEmpty(asset.getHasVehicle())) {
				if (!"0".equals(asset.getHasVehicle()) && !"1".equals(asset.getHasVehicle())) {
					throw new BusinessException(ResponseConstants.HAS_VEHICLE_VALUE_ERROR.getRetCode(),
							ResponseConstants.HAS_VEHICLE_VALUE_ERROR.getRetMsg());
				}
			}
			if (StringUtils.isNotEmpty(asset.getCurrentHouseArea())) {
				String NUMBER_5_2 = "^\\d{1,5}(.\\d{1,2}){0,1}$";
				if (!MatchUtil.match(asset.getCurrentHouseArea(), NUMBER_5_2)) {
					throw new BusinessException(ResponseConstants.CURRENT_HOUSE_AREA_ERROR.getRetCode(),
							ResponseConstants.CURRENT_HOUSE_AREA_ERROR.getRetMsg());
				}
			}
			if (StringUtils.isNotEmpty(asset.getVehicleNo())) {
				String INTEGER_2 = "^\\d{1,2}$";
				if (!MatchUtil.match(asset.getVehicleNo(), INTEGER_2)) {
					throw new BusinessException(ResponseConstants.VEHICLE_NO_ERROR.getRetCode(),
							ResponseConstants.VEHICLE_NO_ERROR.getRetMsg());
				}
			}
		}
		if (contactList != null && contactList.size() > 0) {
			for (CustomerContactInfo contact : contactList) {
				if (!"pMobile".equals(contact.getEtype()) && !"wPhone".equals(contact.getEtype())) {// 联系电话
					throw new BusinessException(ResponseConstants.CONTACT_TYPE_NOT_EMPTY.getRetCode(),
							ResponseConstants.CONTACT_TYPE_NOT_EMPTY.getRetMsg());
				}
			}
		}
		if (locationList != null && locationList.size() > 0) {
			for (CustomerLocationInfo location : locationList) {
				if (!"registry".equals(location.getEtype()) && !"live".equals(location.getEtype())
						&& !"work".equals(location.getEtype())) {
					throw new BusinessException(ResponseConstants.LOCATION_TYPE_ERROR.getRetCode(),
							ResponseConstants.LOCATION_TYPE_ERROR.getRetMsg());
				}
			}
		}
		List<CustomerRelationshipInfo> relationshipList = personalCustomerReq.getCustomerRelationshipInfo();
		if (relationshipList != null && relationshipList.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (CustomerRelationshipInfo rel : relationshipList) {
				StringBuffer str = new StringBuffer(rel.getCredentialType()).append(",").append(rel.getCredentialNo()).append(",");
				if (sb.toString().contains(str.toString())) {
					throw new BusinessException(
							type.equals("online") ? ResponseConstants.CUSTOMER_RELATION_SHIP_REPEAT.getRetCode()
									: ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
							ResponseConstants.CUSTOMER_RELATION_SHIP_REPEAT.getRetMsg());
				}
				sb.append(str);
			}
		}
		// 校验客户存在性和权限
		if (StringUtils.isNotEmpty(personalCustomerReq.getId())) {// 离线转在线，客户新建时id为null
			this.customerPublicValidate(personalCustomerReq.getId(), personalCustomerReq.getCustomerOrgId());
		}
		return personalCustomerReq;
	}

	/** 
	* @Title: enterpriseCustomerValidate 
	* @Description: TODO(企业客户信息校验) 
	* @param @param enterpriseCustomerReq
	* @param @param type
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void enterpriseCustomerValidate(EnterpriseCustomerReq enterpriseCustomerReq, String type)
			throws BusinessException {
		List<CustomerLocationInfo> locationList = enterpriseCustomerReq.getCustomerLocationInfo();
		if (locationList != null && locationList.size() > 0) {
			for (CustomerLocationInfo location : locationList) {
				if (!"business".equals(location.getEtype()) && !"register".equals(location.getEtype())) {
					throw new BusinessException(ResponseConstants.LOCATION_TYPE_ERROR.getRetCode(),
							ResponseConstants.LOCATION_TYPE_ERROR.getRetMsg());
				}
			}
		}
		if (enterpriseCustomerReq.getCorporationBaseAndLrInfo() != null) {
			String registerCapital = enterpriseCustomerReq.getCorporationBaseAndLrInfo().getRegisterCapital();
			if (StringUtils.isNotEmpty(registerCapital)) {
				String NUMBER_6_2 = "^\\d{1,6}(.\\d{1,2}){0,1}$";
				if (!MatchUtil.match(registerCapital, NUMBER_6_2)) {
					throw new BusinessException(ResponseConstants.REGISTER_CAPITAL_ERROR.getRetCode(),
							ResponseConstants.REGISTER_CAPITAL_ERROR.getRetMsg());
				}
			}
		}
		List<CorporationCustomerContactinfo> contactList = enterpriseCustomerReq.getCorporationCustomerContactinfo();
		if (contactList != null && contactList.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (CorporationCustomerContactinfo con : contactList) {
				StringBuffer str = new StringBuffer(con.getTypeOfCertificate()).append(",").append(con.getCertificateNnumber()).append(",");
				if (sb.toString().contains(str.toString())) {
					throw new BusinessException(
							type.equals("online") ? ResponseConstants.CUSTOMER_RELATION_SHIP_REPEAT.getRetCode()
									: ResponseConstants.LOAN_APPLY_OFFLINE_STATUS.getRetCode(),
							ResponseConstants.CUSTOMER_RELATION_SHIP_REPEAT.getRetMsg());
				}
				sb.append(str);
			}
		}
		// 校验客户存在性和权限
		if (StringUtils.isNotEmpty(enterpriseCustomerReq.getId())) {// 离线转在线，客户新建时id为null
			this.customerPublicValidate(enterpriseCustomerReq.getId(), enterpriseCustomerReq.getCustomerOrgId());
		}
	}

	public void customerPublicValidate(String customerId, String customerOrgId) throws BusinessException {
		CustomerPublicInfo customerPublicInfo = customerInfoMapper.getCustomerPublicInfoById(customerId);
		if (customerPublicInfo == null) {
			logger.error("当前客户不存在");
			throw new BusinessException(ResponseConstants.NOT_EXIT_CUSTOMER_ERROR.getRetCode(),
					ResponseConstants.NOT_EXIT_CUSTOMER_ERROR.getRetMsg());
		} else if (customerPublicInfo.geteStatus() != null && !"0".equals(customerPublicInfo.geteStatus())) {
			logger.error("未生效客户");
			throw new BusinessException(ResponseConstants.NOT_EFFECTIVE_CUSTOMER_ERROR.getRetCode(),
					ResponseConstants.NOT_EFFECTIVE_CUSTOMER_ERROR.getRetMsg());
		} else if (customerPublicInfo.getDeleteStatus() != null && ("true".equals(customerPublicInfo.getDeleteStatus())
				|| "1".equals(customerPublicInfo.getDeleteStatus()))) {
			logger.error("注销客户");
			throw new BusinessException(ResponseConstants.WRITE_OFF_CUSTOMER_ERROR.getRetCode(),
					ResponseConstants.WRITE_OFF_CUSTOMER_ERROR.getRetMsg());
		} else if (customerPublicInfo.getIsBlackList() != null && ("true".equals(customerPublicInfo.getIsBlackList())
				|| "1".equals(customerPublicInfo.getIsBlackList()))) {
			logger.error("黑名单客户");
			throw new BusinessException(ResponseConstants.BLACKLIST_CUSTOMER_ERROR.getRetCode(),
					ResponseConstants.BLACKLIST_CUSTOMER_ERROR.getRetMsg());
		} else if (customerPublicInfo.getIsRestricted() != null && ("true".equals(customerPublicInfo.getIsRestricted())
				|| "1".equals(customerPublicInfo.getIsRestricted()))) {
			logger.error("受限客户");
			throw new BusinessException(ResponseConstants.LIMITED_CUSTOMER_ERROR.getRetCode(),
					ResponseConstants.LIMITED_CUSTOMER_ERROR.getRetMsg());
		} else if (customerPublicInfo.getIsRisk() != null
				&& ("true".equals(customerPublicInfo.getIsRisk()) || "1".equals(customerPublicInfo.getIsRisk()))) {
			logger.error("风险客户");
			throw new BusinessException(ResponseConstants.RISK_CUSTOMER_ERROR.getRetCode(),
					ResponseConstants.RISK_CUSTOMER_ERROR.getRetMsg());
		} else if (customerPublicInfo.getOrgId() != null && !customerOrgId.equals(customerPublicInfo.getOrgId())) {
			logger.error("请前往pc端进行客户迁移");
			throw new BusinessException(ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetCode(),
					ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetMsg());
		}
	}

	/** 
	* @Title: customerInfoValidate 
	* @Description: TODO(校验用户基本信息，返回客户ID和客户编码) 
	* @param @param credentialType
	* @param @param credentialNo
	* @param @param name
	* @param @param customerOrgId
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return Map<String,String>    返回类型 
	* @throws 
	*/
	public Map<String, String> customerInfoValidate(String credentialType, String credentialNo, String name)
			throws BusinessException {
		String ID_NUMBER = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
		if ("0".equals(credentialType)) {// 校验身份证号
			if (!Pattern.matches(ID_NUMBER, credentialNo)) {
				throw new BusinessException(ResponseConstants.CREDENTIAL_NO_ERROR.getRetCode(),
						ResponseConstants.CREDENTIAL_NO_ERROR.getRetMsg());
			}
		}
		Map<String, String> info = new HashMap<String, String>();
		info.put("credentialType", credentialType);
		info.put("credentialNo", credentialNo);
		// 客户基本信息
		Map<String, String> map = new HashMap<String, String>();
		CustomerPublicInfo customerPublicInfo = customerInfoMapper.getCustomerPublicInfo(info);
		if (customerPublicInfo != null && customerPublicInfo.getId() != null) {// 老客户
			map.put("customerId", customerPublicInfo.getId());
			map.put("customerNo", customerPublicInfo.getCustomerNo());
		}
		return map;
	}
}
