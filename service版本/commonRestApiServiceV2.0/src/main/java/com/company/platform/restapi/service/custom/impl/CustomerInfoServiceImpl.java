package com.company.platform.restapi.service.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.util.DateUtil;
import com.company.platform.restapi.dao.custom.CustomerAlterRecordMapper;
import com.company.platform.restapi.dao.custom.CustomerHouseInfoMapper;
import com.company.platform.restapi.dao.custom.CustomerInfoMapper;
import com.company.platform.restapi.dao.custom.CustomerJobDetailMapper;
import com.company.platform.restapi.dao.custom.CustomerVehicleInfoMapper;
import com.company.platform.restapi.dao.custom.SelfEmployeeInfoMapper;
import com.company.platform.restapi.dao.loan.LoanCodeMapper;
import com.company.platform.restapi.dao.loan.OneSetpApplyInfoMapper;
import com.company.platform.restapi.dao.loan.OrgProductCreditMapper;
import com.company.platform.restapi.dao.loan.OrgProductRelationMapper;
import com.company.platform.restapi.dao.loan.ProductInfoMapper;
import com.company.platform.restapi.model.custom.CorporationCustomerForm;
import com.company.platform.restapi.model.custom.CustomerAlterRecord;
import com.company.platform.restapi.model.custom.CustomerHouseInfo;
import com.company.platform.restapi.model.custom.CustomerInfo;
import com.company.platform.restapi.model.custom.CustomerInfoReq;
import com.company.platform.restapi.model.custom.CustomerJobInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.CustomerVehicleInfo;
import com.company.platform.restapi.model.custom.PersonalCustomerInfo;
import com.company.platform.restapi.model.custom.SelfEmployeeInfo;
import com.company.platform.restapi.model.custom.enterprise.CorpCustomerInfoReq;
import com.company.platform.restapi.model.custom.enterprise.CorporationBaseAndLrInfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationCustomerContactinfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationCustomerShareholderInfo;
import com.company.platform.restapi.model.custom.enterprise.EnterpriseInfoResp;
import com.company.platform.restapi.model.custom.personal.CustomerAssetInfo;
import com.company.platform.restapi.model.custom.personal.CustomerContactInfo;
import com.company.platform.restapi.model.custom.personal.CustomerInfoResp;
import com.company.platform.restapi.model.custom.personal.CustomerJobDetailInfo;
import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;
import com.company.platform.restapi.model.custom.personal.CustomerMateInfo;
import com.company.platform.restapi.model.custom.personal.CustomerRelationshipInfo;
import com.company.platform.restapi.model.custom.personal.LocationOrContactBase;
import com.company.platform.restapi.model.custom.personal.PersonalCustomerBaseInfo;
import com.company.platform.restapi.model.loan.offToOnline.OfflineResp;
import com.company.platform.restapi.service.custom.ICustomerInfoService;
import com.company.platform.restapi.service.loan.ICorporationCustomerService;
import com.company.platform.security.model.SecurityUser;

/** 
* @ClassName: CustomerInfoServiceImp 
* @Description: TODO(获取贷款申请人信息实现类) 
* @author 王雪 
* @date 2018年1月24日 上午10:14:33 
*  
*/
@Service
public class CustomerInfoServiceImpl implements ICustomerInfoService {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(CustomerInfoServiceImpl.class);

	@Autowired
	CustomerInfoMapper customerInfoMapper;

	@Autowired
	OneSetpApplyInfoMapper oneSetpApplyInfoMapper;

	@Autowired
	OrgProductRelationMapper orgProductRelationMapper;

	@Autowired
	OrgProductCreditMapper orgProductCreditMapper;

	@Autowired
	ProductInfoMapper productInfoMapper;

	@Autowired
	CustomerJobDetailMapper customerJobDetailMapper;

	@Autowired
	CustomerHouseInfoMapper customerHouseInfoMapper;

	@Autowired
	SelfEmployeeInfoMapper selfEmployeeInfoMapper;

	@Autowired
	CustomerVehicleInfoMapper customerVehicleInfoMapper;

	@Autowired
	CustomerAlterRecordMapper customerAlterRecordMapper;

	@Resource
	private ICorporationCustomerService corporationCustomerService;

	@Autowired
	private LoanCodeMapper loanCodeMapper;

	/*
	 * (非 Javadoc) <p>Title: getCustomerInfo</p> <p>Description: </p>
	 * 
	 * @param customerInfoReq
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * getCustomerInfo(com.company.platform.restapi.model.custom.
	 * CustomerInfoReq)
	 */
	@Override
	public CustomerInfoResp getCustomerInfo(CustomerInfoReq customerInfoReq) throws BusinessException {
		Map<String, String> info = new HashMap<String, String>();
		info.put("credentialType", customerInfoReq.getCredentialType());
		info.put("credentialNo", customerInfoReq.getCredentialNo());
		CustomerInfoResp customer = new CustomerInfoResp();
		// 客户基本信息
		CustomerPublicInfo customerPublicInfo = customerInfoMapper.getCustomerPublicInfo(info);

		if (customerPublicInfo != null && customerPublicInfo.getId() != null) {// 老客户
			if (customerPublicInfo.geteStatus() != null && "1".equals(customerPublicInfo.geteStatus())) {
				logger.error("未生效客户");
				throw new BusinessException(ResponseConstants.NOT_EFFECTIVE_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.NOT_EFFECTIVE_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getDeleteStatus() != null
					&& ("true".equals(customerPublicInfo.getDeleteStatus())
							|| "1".equals(customerPublicInfo.getDeleteStatus()))) {
				logger.error("注销客户");
				throw new BusinessException(ResponseConstants.WRITE_OFF_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.WRITE_OFF_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getIsBlackList() != null
					&& ("true".equals(customerPublicInfo.getIsBlackList())
							|| "1".equals(customerPublicInfo.getIsBlackList()))) {
				logger.error("黑名单客户");
				throw new BusinessException(ResponseConstants.BLACKLIST_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.BLACKLIST_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getIsRestricted() != null
					&& ("true".equals(customerPublicInfo.getIsRestricted())
							|| "1".equals(customerPublicInfo.getIsRestricted()))) {
				logger.error("受限客户");
				throw new BusinessException(ResponseConstants.LIMITED_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.LIMITED_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getIsRisk() != null
					&& ("true".equals(customerPublicInfo.getIsRisk()) || "1".equals(customerPublicInfo.getIsRisk()))) {
				logger.error("风险客户");
				throw new BusinessException(ResponseConstants.RISK_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.RISK_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getOrgId() != null
					&& !customerInfoReq.getCustomerOrgId().equals(customerPublicInfo.getOrgId())) {
				logger.error("请前往pc端进行客户迁移");
				throw new BusinessException(ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetMsg());
			} else {
				// 返回客户基本信息
				customer.setCustomerPublicInfo(customerPublicInfo);
//				String customerId = customerPublicInfo.getId();
//				// 返回客户联系信息
//				customer.setCustomerContactInfo(customerInfoMapper.getCustomerContactInfo(customerId));
//				// 返回客户地址信息
//				customer.setCustomerLocationInfo(customerInfoMapper.getCustomerLocationInfo(customerId));
//				// 返回客户工作信息
//				customer.setCustomerJobDetailInfo(customerInfoMapper.getCustomerJobDetailInfo(customerId));
//				// 返回客户联系人信息
//				customer.setCustomerRelationshipInfo(customerInfoMapper.getCustomerRelationshipInfo(customerId));
//				// 返回客户个人基本信息
//				customer.setPersonalCustomerBaseInfo(customerInfoMapper.getPersonalCustomerInfo(customerId));
//				// 返回客户配偶信息
//				customer.setCustomerMateInfo(customerInfoMapper.getCustomerMateInfo(customerId));
//				// 返回客户资产信息
//				customer.setCustomerAssetInfo(customerInfoMapper.getCustomerAssetInfo(customerId));
			}
		} else {// 新客户,用户姓名可编辑
			logger.error("当前客户不存在");
			throw new BusinessException(ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetCode(),
					ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetMsg());
		}
		return customer;
	}

	/*
	 * (非 Javadoc) <p>Title: getEnterpriseInfo</p> <p>Description: </p>
	 * 
	 * @param customerInfoReq
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * getEnterpriseInfo(com.company.platform.restapi.model.custom.
	 * CustomerInfoReq)
	 */
	@Override
	public EnterpriseInfoResp getEnterpriseInfo(CorpCustomerInfoReq customerInfoReq) throws BusinessException {
		Map<String, String> info = new HashMap<String, String>();
		info.put("credentialType", customerInfoReq.getCredentialType());
		info.put("credentialNo", customerInfoReq.getCredentialNo());
		EnterpriseInfoResp customer = new EnterpriseInfoResp();
		// 客户基本信息
		CustomerPublicInfo customerPublicInfo = customerInfoMapper.getCustomerPublicInfo(info);

		if (customerPublicInfo != null && customerPublicInfo.getId() != null) {// 老客户
			if (customerPublicInfo.geteStatus() != null && "1".equals(customerPublicInfo.geteStatus())) {
				logger.error("未生效客户");
				throw new BusinessException(ResponseConstants.NOT_EFFECTIVE_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.NOT_EFFECTIVE_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getDeleteStatus() != null
					&& ("true".equals(customerPublicInfo.getDeleteStatus())
							|| "1".equals(customerPublicInfo.getDeleteStatus()))) {
				logger.error("注销客户");
				throw new BusinessException(ResponseConstants.WRITE_OFF_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.WRITE_OFF_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getIsBlackList() != null
					&& ("true".equals(customerPublicInfo.getIsBlackList())
							|| "1".equals(customerPublicInfo.getIsBlackList()))) {
				logger.error("黑名单客户");
				throw new BusinessException(ResponseConstants.BLACKLIST_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.BLACKLIST_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getIsRestricted() != null
					&& ("true".equals(customerPublicInfo.getIsRestricted())
							|| "1".equals(customerPublicInfo.getIsRestricted()))) {
				logger.error("受限客户");
				throw new BusinessException(ResponseConstants.LIMITED_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.LIMITED_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getIsRisk() != null
					&& ("true".equals(customerPublicInfo.getIsRisk()) || "1".equals(customerPublicInfo.getIsRisk()))) {
				logger.error("风险客户");
				throw new BusinessException(ResponseConstants.RISK_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.RISK_CUSTOMER_ERROR.getRetMsg());
			} else if (customerPublicInfo.getOrgId() != null
					&& !customerInfoReq.getCustomerOrgId().equals(customerPublicInfo.getOrgId())) {
				logger.error("请前往pc端进行客户迁移");
				throw new BusinessException(ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetCode(),
						ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetMsg());
			} else {
				// 返回客户基本信息
				customer.setCustomerPublicInfo(customerPublicInfo);
				String customerId = customerPublicInfo.getId();
				// 返回客户地址信息
				customer.setCustomerLocationInfo(customerInfoMapper.getCustomerLocationInfo(customerId));
				// 返回企业客户联系人信息
				customer.setCorporationCustomerContactinfo(
						customerInfoMapper.getCorporationCustomerContactinfo(customerId));
				// 返回企业客户股东信息
				customer.setCorporationCustomerShareholderInfo(
						customerInfoMapper.getCorporationCustomerShareholderInfo(customerId));
				// 返回企业客户法人信息
				customer.setCorporationBaseAndLrInfo(customerInfoMapper.getCorporationBaseAndLrInfo(customerId));
			}
		} else {// 新客户,用户姓名可编辑
			logger.error("当前客户不存在");
			throw new BusinessException(ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetCode(),
					ResponseConstants.TRANSFER_CUSTOMER_ERROR.getRetMsg());
		}
		return customer;
	}

	/*
	 * (非 Javadoc) <p>Title: savePersonalCustomerInfo</p> <p>Description: </p>
	 * 
	 * @param personalCustomerBaseInfo
	 * 
	 * @param type
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * savePersonalCustomerInfo(com.company.platform.restapi.model.custom.
	 * personal.PersonalCustomerBaseInfo, java.lang.String)
	 */
	@Override
	public void savePersonalCustomerInfo(PersonalCustomerBaseInfo personalCustomerBaseInfo, String type)
			throws BusinessException {
		logger.info("保存个人客户个人基本信息");
		if (personalCustomerBaseInfo.getBirthday() != null && "".equals(personalCustomerBaseInfo.getBirthday())) {
			personalCustomerBaseInfo.setBirthday(null);
		} else if (personalCustomerBaseInfo.getHasChild() != null
				&& "".equals(personalCustomerBaseInfo.getHasChild())) {
			personalCustomerBaseInfo.setHasChild(null);
		} else if (personalCustomerBaseInfo.getHasDrivingLicense() != null
				&& "".equals(personalCustomerBaseInfo.getHasDrivingLicense())) {
			personalCustomerBaseInfo.setHasDrivingLicense(null);
		}
		if ("online".equals(type)) {// 在线操作
			customerInfoMapper.savePersonalCustomerInfoOnline(personalCustomerBaseInfo);
		} else if ("offline".equals(type)) {// 离线转在线，动态sql
			customerInfoMapper.savePersonalCustomerInfo(personalCustomerBaseInfo);
		}
	}

	/*
	 * (非 Javadoc) <p>Title: saveCustomerLocationInfo</p> <p>Description: </p>
	 * 
	 * @param list
	 * 
	 * @param customerId
	 * 
	 * @param type
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * saveCustomerLocationInfo(java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Transactional
	@Override
	public List<LocationOrContactBase> saveCustomerLocationInfo(List<CustomerLocationInfo> list, String customerId,
			String type) throws BusinessException {
		logger.info("保存个人客户地址信息");
		List<LocationOrContactBase> locationList = new ArrayList<LocationOrContactBase>();
		if (list != null && list.size() > 0) {
			for (CustomerLocationInfo location : list) {
				LocationOrContactBase base = new LocationOrContactBase();
				if (StringUtils.isEmpty(location.getId())) {// 新建地址信息
					location.setCustomerId(customerId);
					location.setId(UUID.randomUUID().toString());
					customerInfoMapper.insertCustomerLocationInfo(location);
				} else {// 修改地址信息
					if ("online".equals(type)) {// 在线操作
						customerInfoMapper.updateCustomerLocationInfoOnline(location);
					} else if ("offline".equals(type)) {// 离线转在线，动态sql
						customerInfoMapper.updateCustomerLocationInfo(location);
					}
				}
				base.setId(location.getId());
				base.setEtype(location.getEtype());
				locationList.add(base);
			}
		}
		return locationList;
	}

	/*
	 * (非 Javadoc) <p>Title: saveCustomerContactInfo</p> <p>Description: </p>
	 * 
	 * @param list
	 * 
	 * @param customerId
	 * 
	 * @param type
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * saveCustomerContactInfo(java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Transactional
	@Override
	public List<LocationOrContactBase> saveCustomerContactInfo(List<CustomerContactInfo> list, String customerId,
			String type) throws BusinessException {
		logger.info("保存个人客户联系信息");
		List<LocationOrContactBase> contactList = new ArrayList<LocationOrContactBase>();
		if (list != null && list.size() > 0) {
			for (CustomerContactInfo contact : list) {
				LocationOrContactBase base = new LocationOrContactBase();
				if (StringUtils.isEmpty(contact.getId())) {// 新建联系信息
					contact.setId(UUID.randomUUID().toString());
					contact.setCustomerId(customerId);
					String c = contact.getContact();
					if (StringUtils.isNotBlank(c)) {
						customerInfoMapper.insertCustomerContactInfo(contact);
					} else {

					}
				} else {// 修改联系信息
					if ("online".equals(type)) {// 在线操作
						customerInfoMapper.updateCustomerContactInfoOnline(contact);
					} else if ("offline".equals(type)) {// 离线转在线，动态sql
						customerInfoMapper.updateCustomerContactInfo(contact);
					}
				}
				base.setId(contact.getId());
				base.setEtype(contact.getEtype());
				contactList.add(base);
			}
		}
		return contactList;
	}

	/*
	 * (非 Javadoc) <p>Title: saveCustomerJobDetailInfo</p> <p>Description: </p>
	 * 
	 * @param job
	 * 
	 * @param customerId
	 * 
	 * @param type
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * saveCustomerJobDetailInfo(com.company.platform.restapi.model.custom.
	 * personal.CustomerJobDetailInfo, java.lang.String, java.lang.String)
	 */
	@Override
	public void saveCustomerJobDetailInfo(CustomerJobDetailInfo job, String customerId, String type)
			throws BusinessException {
		logger.info("保存客户工作信息");
		if (null != job) {
			if (job.getCurrentWorkAge() != null && "".equals(job.getCurrentWorkAge())) {
				job.setCurrentWorkAge(null);
			}
			job.setId(customerId);
			if ("online".equals(type)) {// 在线操作
				customerInfoMapper.updateCustomerJobDetailInfoOnline(job);
			} else if ("offline".equals(type)) {// 离线转在线，动态sql
				customerInfoMapper.updateCustomerJobDetailInfo(job);
			}
		}
	}

	/*
	 * (非 Javadoc) <p>Title: saveCustomerRelationshipInfo</p> <p>Description:
	 * </p>
	 * 
	 * @param list
	 * 
	 * @param customerId
	 * 
	 * @param type
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * saveCustomerRelationshipInfo(java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Transactional
	@Override
	public void saveCustomerRelationshipInfo(List<CustomerRelationshipInfo> list, String customerId, String type)
			throws BusinessException {
		logger.info("保存客户联系人信息");
		if (list != null && list.size() > 0) {
			if ("online".equals(type)) {// 在线操作
				customerInfoMapper.deleteCustomerRelationshipInfo(customerId);
				for (CustomerRelationshipInfo ship : list) {
					ship.setCustomerId(customerId);
					customerInfoMapper.insertCustomerRelationshipInfo(ship);
				}
			} else if ("offline".equals(type)) {// 离线转在线
				for (CustomerRelationshipInfo ship : list) {
					CustomerRelationshipInfo cus = customerInfoMapper
							.selectCustomerRelationshipInfo(customerId, ship.getCredentialType(), ship.getCredentialNo());
					if (cus != null) {// 修改
						ship.setId(cus.getId());
						customerInfoMapper.updateCustomerRelationshipInfo(ship);
					} else {// 新建
						ship.setCustomerId(customerId);
						customerInfoMapper.insertCustomerRelationshipInfo(ship);
					}
				}
			}

		}
	}

	/*
	 * (非 Javadoc) <p>Title: saveCorporationCustomerContactinfo</p>
	 * <p>Description: </p>
	 * 
	 * @param list
	 * 
	 * @param customerId
	 * 
	 * @param type
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * saveCorporationCustomerContactinfo(java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void saveCorporationCustomerContactinfo(List<CorporationCustomerContactinfo> list, String customerId,
			String type) throws BusinessException {
		logger.info("保存企业客户联系人信息");
		if (null != list && list.size() > 0) {
			if ("online".equals(type)) {// 在线操作
				customerInfoMapper.deleteCorporationCustomerContactinfo(customerId);
				for (CorporationCustomerContactinfo contact : list) {
					contact.setCustomerId(customerId);
					customerInfoMapper.insertCorporationCustomerContactinfo(contact);
				}
			} else if ("offline".equals(type)) {// 离线转在线
				for (CorporationCustomerContactinfo contact : list) {
					CorporationCustomerContactinfo cus = customerInfoMapper.selectCorporationCustomerContactinfo(
							contact.getTypeOfCertificate(), contact.getCertificateNnumber(), customerId);
					if (cus != null) {// 修改
						contact.setId(cus.getId());
						customerInfoMapper.updateCorporationCustomerContactinfo(contact);
					} else {// 新建
						contact.setCustomerId(customerId);
						customerInfoMapper.insertCorporationCustomerContactinfo(contact);
					}
				}
			}
		}
	}

	/*
	 * (非 Javadoc) <p>Title: saveCorporationCustomerShareholderInfo</p>
	 * <p>Description: </p>
	 * 
	 * @param list
	 * 
	 * @param customerId
	 * 
	 * @param type
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * saveCorporationCustomerShareholderInfo(java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void saveCorporationCustomerShareholderInfo(List<CorporationCustomerShareholderInfo> list, String customerId,
			String type) throws BusinessException {
		logger.info("保存客户股东信息");
		if (null != list && list.size() > 0) {
			if ("online".equals(type)) {// 在线操作
				customerInfoMapper.deleteCorporationCustomerShareholderInfo(customerId);
				for (CorporationCustomerShareholderInfo share : list) {
					share.setCustomerId(customerId);
					customerInfoMapper.insertCorporationCustomerShareholderInfo(share);
				}
			} else if ("offline".equals(type)) {// 离线转在线
				for (CorporationCustomerShareholderInfo share : list) {
					CorporationCustomerShareholderInfo cus = customerInfoMapper
							.selectCorporationCustomerShareholderInfo(share.getShareHolderName(), customerId);
					if (cus != null) {// 修改
						share.setId(cus.getId());
						customerInfoMapper.updateCorporationCustomerShareholderInfo(share);
					} else {// 新建
						share.setCustomerId(customerId);
						customerInfoMapper.insertCorporationCustomerShareholderInfo(share);
					}
				}
			}
		}
	}

	/*
	 * (非 Javadoc) <p>Title: saveCorporationBaseAndLrInfo</p> <p>Description:
	 * </p>
	 * 
	 * @param info
	 * 
	 * @param type
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * saveCorporationBaseAndLrInfo(com.company.platform.restapi.model.custom.
	 * enterprise.CorporationBaseAndLrInfo, java.lang.String)
	 */
	@Override
	public void saveCorporationBaseAndLrInfo(CorporationBaseAndLrInfo base, String type) throws BusinessException {
		logger.info("保存企业基本信息和法人信息");
		if (null != base) {
			// 防止类型转换异常(并非所有数据都要加限定，数据库中非varchar类型)
			if (base.getLrAge() != null && "".equals(base.getLrAge())) {
				base.setLrAge(null);
			}
			if (base.getRegisterCapital() != null && "".equals(base.getRegisterCapital())) {
				base.setRegisterCapital(null);
			}
			if (base.getRegisterTime() != null && "".equals(base.getRegisterTime())) {
				base.setRegisterTime(null);
			}
			if (base.getStaffCount() != null && "".equals(base.getStaffCount())) {
				base.setStaffCount(null);
			}
			if ("online".equals(type)) {// 在线操作
				customerInfoMapper.updateCorporationBaseAndLrInfoOnLine(base);
			} else if ("offline".equals(type)) {// 离线转在线，动态sql
				customerInfoMapper.updateCorporationBaseAndLrInfo(base);
			}
		}
	}

	/*
	 * (非 Javadoc) <p>Title: insertCustomerBaseInfo</p> <p>Description: </p>
	 * 
	 * @param customerPublic
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * insertCustomerBaseInfo(com.company.platform.restapi.model.custom.
	 * CustomerPublicInfo)
	 */
	@Override
	public void insertCustomerBaseInfo(CustomerPublicInfo customerPublic) throws BusinessException {
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonalCustomerInfo personalCustomerInfo = new PersonalCustomerInfo();
		CorporationCustomerForm corporationCustomer = new CorporationCustomerForm();
		if ("0".equals(customerPublic.geteType())) {// 个人
			customerPublic.seteType("0");
			customerPublic.setWorkType("0");
			customerPublic.seteStatus("0");
			customerPublic.setDeleteStatus("0");
			customerPublic.setIsBlackList("0");
			customerPublic.setCreateUserId(user.getId());
			customerPublic.setIsRestricted("0");
			customerPublic.setAuthority("1");
			customerPublic.setUsedCredit("0");
			personalCustomerInfo.setId(customerPublic.getId());
			personalCustomerInfo.setCustomerName(customerPublic.getCustomerName());
			personalCustomerInfo.setCredentialType(customerPublic.getCredentialType());
			personalCustomerInfo.setCredentialNo(customerPublic.getCredentialNo());
			String gender = "1";
			if ("0".equals(customerPublic.getCredentialType())) {
				if (customerPublic.getCredentialNo().length() == 15) {
					gender = customerPublic.getCredentialNo().substring(14, 15);
					if ("x".equalsIgnoreCase(gender) || Integer.parseInt(gender) % 2 == 0) {
						gender = "2";
					} else {
						gender = "1";
					}
				} else {
					gender = customerPublic.getCredentialNo().substring(16, 17);
					if (Integer.parseInt(gender) % 2 == 0) {
						gender = "2";
					} else {
						gender = "1";
					}
				}
			}
			personalCustomerInfo.setGender(gender);
			if ("0".equals(customerPublic.getCredentialType())) {
				String birthdayStr = customerPublic.getCredentialNo().substring(6, 10)
						+ customerPublic.getCredentialNo().substring(10, 12)
						+ customerPublic.getCredentialNo().substring(12, 14);
				personalCustomerInfo.setBirthday(birthdayStr);
			}
		} else {// 企业
			corporationCustomer.setCustomerId(customerPublic.getId());// 客户ID
			corporationCustomer.setCustomerNo(customerPublic.getCustomerNo());// 客户编号
			corporationCustomer.setCustomerName(customerPublic.getCustomerName());// 客户名称
			corporationCustomer.setCredentialType(customerPublic.getCredentialType());// 证件类型
			corporationCustomer.setCredentialNo(customerPublic.getCredentialNo());// 证件号码
			corporationCustomer.setOrgId(customerPublic.getOrgId());// 组织ID
			corporationCustomer.setCustomerStatus("0");// 客户状态 默认为生效
			corporationCustomer.setIfBlack("0");// 是否黑名单 默认非黑名单
			corporationCustomer.setIfRestrict("0");// 是否受限 默认非受限
			corporationCustomer.setStaffCount("0");// 企业规模
		}
		if (personalCustomerInfo != null && personalCustomerInfo.getCredentialNo() != null) {// 增加个人客户
			oneSetpApplyInfoMapper.insertCustomerInfo(customerPublic);
			oneSetpApplyInfoMapper.insertPersonalCustomerInfo(personalCustomerInfo);
			CustomerJobInfo customerJobDetail = new CustomerJobInfo();
			customerJobDetail.setId(customerPublic.getId());
			customerJobDetail.setMonthIncome("0");
			customerJobDetail.seteType("0");
			customerJobDetail.setCurrentWorkInfo("");
			customerJobDetail.setCurrentDepInfo("");
			customerJobDetail.setCurrentWorkType("");
			customerJobDetail.setCurrentPosition("");
			customerJobDetail.setTotalWorkAge("0");
			customerJobDetail.setIndustryType("");
			customerJobDetailMapper.insertCustomerJobInfo(customerJobDetail);
			CustomerHouseInfo customerHouseInfo = new CustomerHouseInfo();
			customerHouseInfo.setId(customerPublic.getId());
			customerHouseInfo.setHasHouse("0");
			customerHouseInfo.setOwnHouseFlag("1");
			customerHouseInfo.setOwnHouseArea("0");
			customerHouseInfo.setCurrentHouseType("");
			customerHouseInfo.setCurrentHouseArea("0");
			customerHouseInfoMapper.insertCustomerHouseInfo(customerHouseInfo);
			SelfEmployeeInfo selfEmployeeInfo = new SelfEmployeeInfo();
			selfEmployeeInfo.setId(customerPublic.getId());
			selfEmployeeInfo.seteType("");
			selfEmployeeInfo.setMonthIncome("0");
			selfEmployeeInfo.setEnterpriseName("");
			selfEmployeeInfo.setTurnover("0");
			selfEmployeeInfo.setMainBusiness("");
			selfEmployeeInfo.setShareRadio("0");
			selfEmployeeInfoMapper.insertSelfEmployeeInfo(selfEmployeeInfo);
			CustomerVehicleInfo customerVehicleInfo = new CustomerVehicleInfo();
			customerVehicleInfo.setId(customerPublic.getId());
			customerVehicleInfo.setHasVehicle("0");
			customerVehicleInfo.setVehicleNo("0");
			customerVehicleInfoMapper.insertCustomerVehicleInfo(customerVehicleInfo);
		}
		if (corporationCustomer != null && corporationCustomer.getCustomerNo() != null) { // 增加企业客户信息
			Boolean flag = corporationCustomerService.merge(corporationCustomer, user.getId());
			if (!flag) {
				CustomerAlterRecord record = new CustomerAlterRecord();
				record.setId(UUID.randomUUID().toString());
				record.seteType("0");
				record.setCustomerId(corporationCustomer.getCustomerId());
				record.setAlterTime(DateUtil.dateTimeFormat(new Date()));
				record.seteDesc("");
				record.setOperaterId(user.getId());
				customerAlterRecordMapper.insertCustomerAlterRecord(record);
			}
		}
	}

	/*
	 * (非 Javadoc) <p>Title: modifyCustomerPublicUpdateTimeById</p>
	 * <p>Description: </p>
	 * 
	 * @param updateTime
	 * 
	 * @param id
	 * 
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * modifyCustomerPublicUpdateTimeById(java.lang.String, java.lang.String)
	 */
	@Override
	public void modifyCustomerPublicUpdateTimeById(String updateTime, String id) throws BusinessException {
		logger.info("修改客户更新时间");
		customerInfoMapper.modifyCustomerPublicUpdateTimeById(updateTime, id);
	}

	/*
	 * (非 Javadoc) <p>Title: getCustomerNo</p> <p>Description: </p>
	 * 
	 * @param type
	 * 
	 * @return
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * getCustomerNo(java.lang.String)
	 */
	@Override
	public String getCustomerNo(String type) {
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
			code.append(PREFIX).append(StringUtils.defaultString(null)).append(DATE).append(CURRENT_CODE)
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
		return code.toString();
	}

	/*
	 * (非 Javadoc) <p>Title: getCustomerContactInfo</p> <p>Description: </p>
	 * 
	 * @param contactList
	 * 
	 * @param customerId
	 * 
	 * @return
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * getCustomerContactInfo(java.util.List, java.lang.String)
	 */
	@Override
	public List<CustomerContactInfo> getCustomerContactInfo(List<CustomerContactInfo> contactList, String customerId) {
		List<CustomerContactInfo> list = customerInfoMapper.getCustomerContactInfo(customerId);
		if (list != null && list.size() > 0) {
			for (CustomerContactInfo contact : list) {// 数据库原有数据，对比后，获取id
				for (CustomerContactInfo con : contactList) {
					if (contact.getEtype().equals(con.getEtype())) { // 同一类型，将id存入新数据中
						con.setId(contact.getId());
						break;
					}
				}
			}
		}
		return contactList;
	}

	/*
	 * (非 Javadoc) <p>Title: getCustomerLocationInfo</p> <p>Description: </p>
	 * 
	 * @param locationList
	 * 
	 * @param customerId
	 * 
	 * @return
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * getCustomerLocationInfo(java.util.List, java.lang.String)
	 */
	@Override
	public List<CustomerLocationInfo> getCustomerLocationInfo(List<CustomerLocationInfo> locationList,
			String customerId) {
		List<CustomerLocationInfo> list = customerInfoMapper.getCustomerLocationInfo(customerId);
		if (list != null && list.size() > 0) {
			for (CustomerLocationInfo location : list) {
				for (CustomerLocationInfo loc : locationList) {
					if (location.getEtype().equals(loc.getEtype())) {
						loc.setId(location.getId());
						break;
					}
				}
			}
		}
		return locationList;
	}

	/*
	 * (非 Javadoc) <p>Title: getCustomerAllInfo</p> <p>Description: </p>
	 * 
	 * @param customerId
	 * 
	 * @param customerType
	 * 
	 * @return
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * getCustomerAllInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public OfflineResp getCustomerAllInfo(String customerId, String customerType) {
		OfflineResp resp = new OfflineResp();
		// 客户基本信息
		resp.setCustomerPublicInfo(customerInfoMapper.getCustomerPublicInfoById(customerId));
		// 返回客户地址信息
		resp.setCustomerLocationInfo(customerInfoMapper.getCustomerLocationInfo(customerId));
		if ("0".equals(customerType)) {// 个人客户
			// 返回客户联系信息
			resp.setCustomerContactInfo(customerInfoMapper.getCustomerContactInfo(customerId));
			// 返回客户工作信息
			resp.setCustomerJobDetailInfo(customerInfoMapper.getCustomerJobDetailInfo(customerId));
			// 返回客户联系人信息
			resp.setCustomerRelationshipInfo(customerInfoMapper.getCustomerRelationshipInfo(customerId));
			// 返回客户个人基本信息
			resp.setPersonalCustomerBaseInfo(customerInfoMapper.getPersonalCustomerInfo(customerId));
			// 返回客户配偶信息
			resp.setCustomerMateInfo(customerInfoMapper.getCustomerMateInfo(customerId));
			// 返回客户资产信息
			resp.setCustomerAssetInfo(customerInfoMapper.getCustomerAssetInfo(customerId));
		} else {
			// 返回企业客户联系人信息
			resp.setCorporationCustomerContactinfo(customerInfoMapper.getCorporationCustomerContactinfo(customerId));
			// 返回企业客户股东信息
			resp.setCorporationCustomerShareholderInfo(
					customerInfoMapper.getCorporationCustomerShareholderInfo(customerId));
			// 返回企业客户法人信息
			resp.setCorporationBaseAndLrInfo(customerInfoMapper.getCorporationBaseAndLrInfo(customerId));
		}
		return resp;
	}

	/*
	 * (非 Javadoc) <p>Title: saveCustomerMateInfo</p> <p>Description: </p>
	 * 
	 * @param customerMateInfo
	 * 
	 * @param customerId
	 * 
	 * @param type
	 * 
	 * @param martialStatus
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * saveCustomerMateInfo(com.company.platform.restapi.model.custom.personal.
	 * CustomerMateInfo, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void saveCustomerMateInfo(CustomerMateInfo customerMateInfo, String customerId, String type,
			String martialStatus) {
		if ("online".equals(type)) {// 在线
			// 删除原有配偶信息
			customerInfoMapper.deleteCustomerMateInfo(customerId);
			if (customerMateInfo != null) {
				customerMateInfo.setId(customerId);
				// 创建配偶信息
				customerInfoMapper.insertCustomerMateInfo(customerMateInfo);
			}
		} else {// 离线
			if (StringUtils.isNotEmpty(martialStatus) && ("2".equals(martialStatus) || "3".equals(martialStatus))) {// 已婚
				if (customerMateInfo != null) {
					// 获取原配偶信息
					CustomerMateInfo oldMate = customerInfoMapper.selectCustomerMateInfo(customerId);
					if (oldMate != null) {
						// 更新配偶信息(动态sql)
						customerInfoMapper.updateCustomerMateInfo(customerMateInfo);
					} else {
						// 创建配偶信息
						customerMateInfo.setId(customerId);
						customerInfoMapper.insertCustomerMateInfo(customerMateInfo);
					}
				}
			} else {
				// 删除原有配偶信息
				customerInfoMapper.deleteCustomerMateInfo(customerId);
			}
		}

	}

	/*
	 * (非 Javadoc) <p>Title: saveCustomerAssetInfo</p> <p>Description: </p>
	 * 
	 * @param customerAssetInfo
	 * 
	 * @param customerId
	 * 
	 * @param type
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * saveCustomerAssetInfo(com.company.platform.restapi.model.custom.personal.
	 * CustomerAssetInfo, java.lang.String, java.lang.String)
	 */
	@Override
	public void saveCustomerAssetInfo(CustomerAssetInfo customerAssetInfo, String customerId, String type) {
		if (customerAssetInfo != null) {
			logger.info("更新客户资产信息");
			customerAssetInfo.setId(customerId);
			if ("".equals(customerAssetInfo.getCurrentHouseArea())) {
				customerAssetInfo.setCurrentHouseArea("0");
			}
			if ("".equals(customerAssetInfo.getVehicleNo())) {
				customerAssetInfo.setHasVehicle("0");
			}
			if ("online".equals(type)) {// 在线
				// 更新房产信息
				customerInfoMapper.updateCustomerHouseInfo(customerAssetInfo);
				// 更新车辆信息
				customerInfoMapper.updateCustomerVehicleInfo(customerAssetInfo);
			} else {// 离线
				// 更新房产信息（动态sql）
				customerInfoMapper.updateCustomerHouseInfoOffline(customerAssetInfo);
				// 更新车辆信息（动态sql）
				customerInfoMapper.updateCustomerVehicleInfoOffline(customerAssetInfo);
			}

		}
	}
	
	/*
	 * (非 Javadoc) <p>Title: modifyCustomerPublicInfo</p>
	 * <p>Description: </p>
	 * 
	 * @param customerId
	 * 
	 * @param mobilePhone
	 *  
	 * @throws BusinessException
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * modifyCustomerPublicInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public void modifyCustomerPublicInfo(String customerId, String mobilePhone) {
		logger.info("修改客户基本信息电话号");
		customerInfoMapper.modifyCustomerPublicInfo(customerId, mobilePhone);
	}

	/*
	 * (非 Javadoc) <p>Title: modifyCustomerPublicInfo</p>
	 * <p>Description: </p>
	 * 
	 * @param customerId
	 * 
	 * @param address
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * insertCustomerLocationInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public void insertCustomerLocationInfo(String customerId, String address) {
		logger.info("修改客户基本信息户籍地址");
		customerInfoMapper.insertCustomerLocation(customerId, "registry", address);
	}

	@Override
	public CustomerInfo selectCustomerInfo(String name, String idcard, String phone) {
		
		return customerInfoMapper.selectCustomerInfo( name,  idcard,  phone);
	}
	
	/*
	 * (非 Javadoc) <p>Title: modifyCustomerPublicInfo</p>
	 * <p>Description: </p>
	 * 
	 * @param customerId
	 * 
	 * @param address
	 * 
	 * @see com.company.platform.restapi.service.custom.ICustomerInfoService#
	 * updateCustomerLocationInfoAddress(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateCustomerLocationInfoAddress(String customerId, String address) {
		logger.info("更新客户基本信息户籍地址");
		customerInfoMapper.updateCustomerLocationAddress(customerId, address);
	}
}
