package com.company.platform.restapi.service.custom;

import java.util.List;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.custom.CustomerInfo;
import com.company.platform.restapi.model.custom.CustomerInfoReq;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
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

/**
 * 
* @ClassName: ICustomerInfoService 
* @Description: TODO(获取贷款申请人信息) 
* @author 王雪 
* @date 2018年1月23日 下午2:06:37 
*
 */
/** 
* @ClassName: ICustomerInfoService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 王雪 
* @date 2018年3月14日 下午5:10:18 
*  
*/
public interface ICustomerInfoService {
	/**
	 * 
	* @Title: getCustomerInfo 
	* @Description: TODO(获取个人客户信息) 
	* @param @param customerInfoReq (credentialType 客户类型, credentialNo 证件号码, customerOrgId 客户机构id)
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return CustomerInfoResp    返回类型 
	* @throws
	 */
	public CustomerInfoResp getCustomerInfo(CustomerInfoReq customerInfoReq) throws BusinessException;

	/** 
	* @Title: getEnterpriseInfo 
	* @Description: TODO(获取企业客户信息) 
	* @param @param customerInfoReq (credentialType 客户类型, credentialNo 证件号码, customerOrgId 客户机构id)
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return EnterpriseInfoResp    返回类型 
	* @throws 
	*/
	public EnterpriseInfoResp getEnterpriseInfo(CorpCustomerInfoReq customerInfoReq) throws BusinessException;

	/** 
	* @Title: savePersonalCustomerInfo 
	* @Description: TODO(保存客户个人基本信息) 
	* @param @param personalCustomerInfo 客户个人基本信息
	* @param @param type(online, offline)
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void savePersonalCustomerInfo(PersonalCustomerBaseInfo personalCustomerBaseInfo, String type)
			throws BusinessException;

	/** 
	* @Title: saveCustomerLocationInfo 
	* @Description: TODO(保存客户地址信息) 
	* @param @param list
	* @param @param customerId
	* @param @param type(online, offline)
	* @param @throws BusinessException    设定文件 
	* @return List<Map<String,String>>    返回类型 
	* @throws 
	*/
	public List<LocationOrContactBase> saveCustomerLocationInfo(List<CustomerLocationInfo> list, String customerId,
			String type) throws BusinessException;

	/** 
	* @Title: saveCustomerContactInfo 
	* @Description: TODO(保存客户联系信息) 
	* @param @param list
	* @param @param customerId
	* @param @param type(online, offline)
	* @param @throws BusinessException    设定文件 
	* @return List<Map<String,String>>    返回类型 
	* @throws 
	*/
	public List<LocationOrContactBase> saveCustomerContactInfo(List<CustomerContactInfo> list, String customerId,
			String type) throws BusinessException;

	/** 
	* @Title: saveCustomerJobDetailInfo 
	* @Description: TODO(保存客户工作信息) 
	* @param @param job
	* @param @param customerId
	* @param @param type(online, offline)
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void saveCustomerJobDetailInfo(CustomerJobDetailInfo job, String customerId, String type)
			throws BusinessException;

	/** 
	* @Title: saveCustomerRelationshipInfo 
	* @Description: TODO(保存客户联系人信息) 
	* @param @param list
	* @param @param customerId
	* @param @param type(online, offline)
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void saveCustomerRelationshipInfo(List<CustomerRelationshipInfo> list, String customerId, String type)
			throws BusinessException;

	/** 
	* @Title: saveCorporationCustomerContactinfo 
	* @Description: TODO(保存企业客户联系人信息) 
	* @param @param list
	* @param @param customerId
	* @param @param type(online, offline)
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void saveCorporationCustomerContactinfo(List<CorporationCustomerContactinfo> list, String customerId,
			String type) throws BusinessException;

	/** 
	* @Title: saveCorporationCustomerShareholderInfo 
	* @Description: TODO(保存客户股东信息) 
	* @param @param list
	* @param @param customerId
	* @param @param type(online, offline)
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void saveCorporationCustomerShareholderInfo(List<CorporationCustomerShareholderInfo> list, String customerId,
			String type) throws BusinessException;

	/** 
	* @Title: saveCorporationBaseAndLrInfo 
	* @Description: TODO(保存企业基本信息和法人信息) 
	* @param @param info
	* @param @param type(online, offline)
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void saveCorporationBaseAndLrInfo(CorporationBaseAndLrInfo info, String type) throws BusinessException;

	/** 
	* @Title: insertCustomerBaseInfo 
	* @Description: TODO(创建客户信息) 
	* @param @param customerPublic
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void insertCustomerBaseInfo(CustomerPublicInfo customerPublic) throws BusinessException;

	/** 
	* @Title: modifyCustomerPublicUpdateTimeById 
	* @Description: TODO(修改客户更新时间) 
	* @param @param updateTime
	* @param @param id
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void modifyCustomerPublicUpdateTimeById(String updateTime, String id) throws BusinessException;

	/** 
	* @Title: getCustomerNo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param type
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String getCustomerNo(String type);

	/** 
	* @Title: getCustomerContactInfo 
	* @Description: TODO(获取个人客户联系信息，完善数据（id）) 
	* @param @param contactList
	* @param @param customerId
	* @param @return    设定文件 
	* @return CustomerContactInfo    返回类型 
	* @throws 
	*/
	public List<CustomerContactInfo> getCustomerContactInfo(List<CustomerContactInfo> contactList, String customerId);

	/** 
	* @Title: getCustomerLocationInfo 
	* @Description: TODO(获取客户（企业或个人）地址信息，完善数据（id）) 
	* @param @param locationList
	* @param @param customerId
	* @param @return    设定文件 
	* @return List<CustomerLocationInfo>    返回类型 
	* @throws 
	*/
	public List<CustomerLocationInfo> getCustomerLocationInfo(List<CustomerLocationInfo> locationList,
			String customerId);

	/** 
	* @Title: getCustomerAllInfo 
	* @Description: TODO(离线转在线，返回客户全部信息) 
	* @param @param customerId
	* @param @param customerType
	* @param @return    设定文件 
	* @return OfflineResp    返回类型 
	* @throws 
	*/
	public OfflineResp getCustomerAllInfo(String customerId, String customerType);

	/** 
	* @Title: saveCustomerMateInfo 
	* @Description: TODO(保存客户配偶信息) 
	* @param @param customerMateInfo    设定文件 
	* @param @param customerId
	* @param @param type
	* @param @param martialStatus
	* @return void    返回类型 
	* @throws 
	*/
	public void saveCustomerMateInfo(CustomerMateInfo customerMateInfo, String customerId, String type,
			String martialStatus);

	/** 
	* @Title: saveCustomerAssetInfo 
	* @Description: TODO(保存资产信息) 
	* @param @param customerAssetInfo 设定文件 
	* @param @param customerId
	* @param @param type
	* @return void    返回类型 
	* @throws 
	*/
	public void saveCustomerAssetInfo(CustomerAssetInfo customerAssetInfo, String customerId, String type);

	/** 
	* @Title: modifyCustomerPublicInfo 
	* @Description: TODO(更新用户基本信息-手机号码) 
	* @param @param customerId  
	* @param @param mobilePhone 
	* @return void    返回类型 
	* @throws 
	*/
	public void modifyCustomerPublicInfo(String customerId, String mobilePhone);

	/** 
	* @Title: insertCustomerLocationInfo 
	* @Description: TODO(更新用户基本信息-户籍地址) 
	* @param @param customerId  
	* @param @param address 
	* @return void    返回类型 
	* @throws 
	*/
	public void insertCustomerLocationInfo(String customerId, String address);
	
	/**
	 * 查询客户信息
	* @Title: getCustomerInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param name
	* @param @param idcard
	* @param @param phone
	* @param @return    设定文件 
	* @return CustomerInfo    返回类型 
	* @throws
	 */
	public CustomerInfo selectCustomerInfo(String name,String idcard,String phone);
	
	/** 
	* @Title: updateCustomerLocationInfoAddress 
	* @Description: TODO(更新用户基本信息-户籍地址) 
	* @param @param customerId  
	* @param @param address 
	* @return void    返回类型 
	* @throws 
	*/
	public void updateCustomerLocationInfoAddress(String customerId, String address);
}
