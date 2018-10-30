package com.company.platform.restapi.dao.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.company.platform.restapi.model.custom.ContactsInfo;
import com.company.platform.restapi.model.custom.CustomerInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.model.custom.EntCustomerInfo;
import com.company.platform.restapi.model.custom.SelContactsInfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationBaseAndLrInfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationCustomerContactinfo;
import com.company.platform.restapi.model.custom.enterprise.CorporationCustomerShareholderInfo;
import com.company.platform.restapi.model.custom.personal.CustomerAssetInfo;
import com.company.platform.restapi.model.custom.personal.CustomerContactInfo;
import com.company.platform.restapi.model.custom.personal.CustomerJobDetailInfo;
import com.company.platform.restapi.model.custom.personal.CustomerLocationInfo;
import com.company.platform.restapi.model.custom.personal.CustomerMateInfo;
import com.company.platform.restapi.model.custom.personal.CustomerRelationshipInfo;
import com.company.platform.restapi.model.custom.personal.PersonalCustomerBaseInfo;

/**
 * 
* @ClassName: CustomerInfoMapper 
* @Description: TODO(个人客户信息) 
* @author 王雪 
* @date 2018年1月23日 下午4:20:54 
*
 */
public interface CustomerInfoMapper {

	/**
	 * 
	* @Title: getCustomerPublicInfo 
	* @Description: TODO(根据证件类型、证件号获取客户信息) 
	* @param @param info (客户证件类型、证件号)
	* @param @return    设定文件 
	* @return CustomerPublicInfo    返回类型
	* @throws
	 */
	CustomerPublicInfo getCustomerPublicInfo(Map<String, String> info);

	/** 
	* @Title: getCustomerPublicInfoById 
	* @Description: TODO(根据客户id获取客户信息) 
	* @param @param id (客户id)
	* @param @return    设定文件 
	* @return CustomerPublicInfo    返回类型 
	* @throws 
	*/
	CustomerPublicInfo getCustomerPublicInfoById(String id);

	/** 
	* @Title: getCustomerContactInfo 
	* @Description: TODO(根据客户id获取客户联系信息) 
	* @param @param customerId 客户id
	* @param @return    设定文件 
	* @return List<CustomerContactInfo>    返回类型 
	* @throws 
	*/
	List<CustomerContactInfo> getCustomerContactInfo(String customerId);

	/** 
	* @Title: getCustomerLocationInfo 
	* @Description: TODO(根据客户id获取客户地址信息) 
	* @param @param customerId 客户id
	* @param @return    设定文件 
	* @return List<CustomerLocationInfo>    返回类型 
	* @throws 
	*/
	List<CustomerLocationInfo> getCustomerLocationInfo(String customerId);

	/** 
	* @Title: getCustomerJobDetailInfo 
	* @Description: TODO(根据客户id获取客户工作信息) 
	* @param @param customerId 客户id
	* @param @return    设定文件 
	* @return List<CustomerJobDetailInfo>    返回类型 
	* @throws 
	*/
	CustomerJobDetailInfo getCustomerJobDetailInfo(String customerId);

	/** 
	* @Title: getCustomerRelationshipInfo 
	* @Description: TODO(根据客户id获取客户联系人信息) 
	* @param @param customerId 客户id
	* @param @return    设定文件 
	* @return List<CustomerRelationshipInfo>    返回类型 
	* @throws 
	*/
	List<CustomerRelationshipInfo> getCustomerRelationshipInfo(String customerId);

	/** 
	* @Title: getPersonalCustomerInfo 
	* @Description: TODO(根据客户id获取客户个人基本信息) 
	* @param @param customerId 客户id
	* @param @return    设定文件 
	* @return PersonalCustomerInfo    返回类型 
	* @throws 
	*/
	PersonalCustomerBaseInfo getPersonalCustomerInfo(String customerId);

	/** 
	* @Title: getCorporationCustomerContactinformat 
	* @Description: TODO(根据客户id获取企业客户联系人信息) 
	* @param @param customerId 客户id
	* @param @return    设定文件 
	* @return List<CorporationCustomerContactinformat>    返回类型 
	* @throws 
	*/
	List<CorporationCustomerContactinfo> getCorporationCustomerContactinfo(String customerId);

	/** 
	* @Title: getCorporationCustomerShareholderInfo 
	* @Description: TODO(根据客户id获取企业客户股东信息) 
	* @param @param customerId 客户id
	* @param @return    设定文件 
	* @return List<CorporationCustomerShareholderInfo>    返回类型 
	* @throws 
	*/
	List<CorporationCustomerShareholderInfo> getCorporationCustomerShareholderInfo(String customerId);

	/** 
	* @Title: getCorporationLrInfo 
	* @Description: TODO(根据客户id获取企业客户法人信息) 
	* @param @param customerId 客户id
	* @param @return    设定文件 
	* @return CorporationLrInfo    返回类型 
	* @throws 
	*/
	CorporationBaseAndLrInfo getCorporationBaseAndLrInfo(String customerId);

	/** 
	* @Title: savePersonalCustomerInfo 
	* @Description: TODO(保存客户个人基本信息) 
	* @param @param personalCustomerInfo 个人客户信息
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer savePersonalCustomerInfo(PersonalCustomerBaseInfo personalCustomerBaseInfo);

	/** 
	* @Title: savePersonalCustomerInfoOnline 
	* @Description: TODO(保存客户个人基本信息,在线修改) 
	* @param @param personalCustomerBaseInfo
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer savePersonalCustomerInfoOnline(PersonalCustomerBaseInfo personalCustomerBaseInfo);

	/** 
	* @Title: insertCustomerLocationInfo 
	* @Description: TODO(新建地址信息) 
	* @param @param location
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer insertCustomerLocationInfo(CustomerLocationInfo location);

	/** 
	* @Title: updateCustomerLocationInfo 
	* @Description: TODO(修改地址信息) 
	* @param @param location
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer updateCustomerLocationInfo(CustomerLocationInfo location);

	/** 
	* @Title: updateCustomerLocationInfoOnline 
	* @Description: TODO(修改地址信息) 
	* @param @param location
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer updateCustomerLocationInfoOnline(CustomerLocationInfo location);

	/** 
	* @Title: insertCustomerContactInfo 
	* @Description: TODO(新建联系信息) 
	* @param @param contact
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer insertCustomerContactInfo(CustomerContactInfo contact);

	/** 
	* @Title: updateCustomerContactInfo 
	* @Description: TODO(修改联系信息,在线修改) 
	* @param @param contact
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer updateCustomerContactInfo(CustomerContactInfo contact);

	/** 
	* @Title: updateCustomerContactInfoOnline 
	* @Description: TODO(修改联系信息) 
	* @param @param contact
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer updateCustomerContactInfoOnline(CustomerContactInfo contact);

	/** 
	* @Title: updateCustomerJobDetailInfo 
	* @Description: TODO(更新客户工作信息) 
	* @param @param job
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer updateCustomerJobDetailInfo(CustomerJobDetailInfo job);

	/** 
	* @Title: updateCustomerJobDetailInfoOnline 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param job
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer updateCustomerJobDetailInfoOnline(CustomerJobDetailInfo job);

	/** 
	* @Title: deleteCustomerRelationshipInfo 
	* @Description: TODO(删除客户联系人信息) 
	* @param @param customerId
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer deleteCustomerRelationshipInfo(String customerId);

	/** 
	* @Title: insertCustomerRelationshipInfo 
	* @Description: TODO(新建客户联系人信息) 
	* @param @param ship
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer insertCustomerRelationshipInfo(CustomerRelationshipInfo ship);

	/** 
	* @Title: selectCustomerRelationshipInfo 
	* @Description: TODO(根据证件号、证件类型查询个人客户联系人信息) 
	* @param @param customerId
	* @param @param credentialType
	* @param @param credentialNo
	* @param @return    设定文件 
	* @return CustomerRelationshipInfo    返回类型 
	* @throws 
	*/
	CustomerRelationshipInfo selectCustomerRelationshipInfo(@Param("customerId") String customerId,
			@Param("credentialType") String credentialType, @Param("credentialNo") String credentialNo);

	/** 
	* @Title: updateCustomerRelationshipInfo 
	* @Description: TODO(修改个人客户联系人信息) 
	* @param @param ship
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer updateCustomerRelationshipInfo(CustomerRelationshipInfo ship);

	/** 
	* @Title: deleteCorporationCustomerContactinfo 
	* @Description: TODO(删除企业客户联系人信息) 
	* @param @param customerId
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer deleteCorporationCustomerContactinfo(String customerId);

	/** 
	* @Title: insertCorporationCustomerContactinfo 
	* @Description: TODO(新建企业客户联系人信息) 
	* @param @param contact
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer insertCorporationCustomerContactinfo(CorporationCustomerContactinfo contact);

	/** 
	* @Title: selectCorporationCustomerContactinfo 
	* @Description: TODO(根据证件类型、证件号查询企业客户联系人信息) 
	* @param @param typeOfCertificate
	* @param @param certificateNnumber
	* @param @param customerId
	* @param @return    设定文件 
	* @return CorporationCustomerContactinfo    返回类型 
	* @throws 
	*/
	CorporationCustomerContactinfo selectCorporationCustomerContactinfo(
			@Param("typeOfCertificate") String typeOfCertificate,
			@Param("certificateNnumber") String certificateNnumber, @Param("customerId") String customerId);

	/** 
	* @Title: updateCorporationCustomerContactinfo 
	* @Description: TODO(更新企业客户联系人信息) 
	* @param @param contact
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer updateCorporationCustomerContactinfo(CorporationCustomerContactinfo contact);

	/** 
	* @Title: deleteCorporationCustomerShareholderInfo 
	* @Description: TODO(删除客户股东信息) 
	* @param @param customerId
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer deleteCorporationCustomerShareholderInfo(String customerId);

	/** 
	* @Title: insertCorporationCustomerShareholderInfo 
	* @Description: TODO(新建客户股东信息) 
	* @param @param share
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer insertCorporationCustomerShareholderInfo(CorporationCustomerShareholderInfo share);

	/** 
	* @Title: selectCorporationCustomerShareholderInfo 
	* @Description: TODO(根据股东名称查询股东信息) 
	* @param @param shareHolderName
	* @param @param customerId
	* @param @return    设定文件 
	* @return CorporationCustomerShareholderInfo    返回类型 
	* @throws 
	*/
	CorporationCustomerShareholderInfo selectCorporationCustomerShareholderInfo(
			@Param("shareHolderName") String shareHolderName, @Param("customerId") String customerId);

	/** 
	* @Title: updateCorporationCustomerShareholderInfo 
	* @Description: TODO(更新股东信息) 
	* @param @param share
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer updateCorporationCustomerShareholderInfo(CorporationCustomerShareholderInfo share);

	/** 
	* @Title: updateCorporationBaseAndLrInfo 
	* @Description: TODO(修改企业基本信息和法人信息) 
	* @param @param info
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer updateCorporationBaseAndLrInfo(CorporationBaseAndLrInfo info);

	/** 
	* @Title: updateCorporationBaseAndLrInfoOnLine 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param info
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	*/
	Integer updateCorporationBaseAndLrInfoOnLine(CorporationBaseAndLrInfo info);

	/** 
	* @Title: getCustomerinfo 
	* @Description: TODO(通过借款产品申请ID获取客户相关信息) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return LoanProductApplyInfo    返回类型 
	* @throws 
	*/

	CustomerInfo getCustomerinfo(String loanProductApplyId);

	/** 
	* @Title: getEntCustomerInfo 
	* @Description: TODO(通过借款产品申请ID获取企业客户相关信息) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return EntCustomerInfo    返回类型 
	* @throws 
	*/
	EntCustomerInfo getEntCustomerInfo(String loanProductApplyId);

	/** 
	* @Title: getContactsInfo 
	* @Description: TODO(通过借款产品申请ID获取企业客户联系人相关信息) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return List<ContactsInfo>    返回类型 
	* @throws 
	*/
	List<ContactsInfo> getContactsInfo(String loanProductApplyId);

	/** 
	* @Title: getSelContactsInfo 
	* @Description: TODO(通过借款产品申请ID获取个人客户联系人相关信息) 
	* @param @param loanProductApplyId
	* @param @return    设定文件 
	* @return List<SelContactsInfo>    返回类型 
	* @throws 
	*/
	List<SelContactsInfo> getSelContactsInfo(String loanProductApplyId);

	/** 
	* @Title: modifyCustomerPublicUpdateTimeById 
	* @Description: TODO(修改客户更新时间) 
	* @param @param updateTime
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void modifyCustomerPublicUpdateTimeById(@Param("updateTime") String updateTime, @Param("id") String id);

	/** 
	* @Title: getCustomerMateInfo 
	* @Description: TODO(获取客户配偶信息) 
	* @param @param customerId
	* @param @return    设定文件 
	* @return CustomerMateInfo    返回类型 
	* @throws 
	*/
	CustomerMateInfo getCustomerMateInfo(String customerId);

	/** 
	* @Title: getCustomerAssetInfo 
	* @Description: TODO(获取客户资产信息) 
	* @param @param customerId
	* @param @return    设定文件 
	* @return CustomerAssetInfo    返回类型 
	* @throws 
	*/
	CustomerAssetInfo getCustomerAssetInfo(String customerId);

	/** 
	* @Title: deleteCustomerMateInfo 
	* @Description: TODO(删除配偶信息) 
	* @param @param customerId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int deleteCustomerMateInfo(String customerId);

	/** 
	* @Title: insertCustomerMateInfo 
	* @Description: TODO(新建配偶信息) 
	* @param @param customerMateInfo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int insertCustomerMateInfo(CustomerMateInfo customerMateInfo);

	/** 
	* @Title: updateCustomerHouseInfo 
	* @Description: TODO(更新客户房产信息) 
	* @param @param customerAssetInfo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int updateCustomerHouseInfo(CustomerAssetInfo customerAssetInfo);

	/** 
	* @Title: updateCustomerVehicleInfo 
	* @Description: TODO(更新客户车辆信息) 
	* @param @param customerAssetInfo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int updateCustomerVehicleInfo(CustomerAssetInfo customerAssetInfo);

	/** 
	* @Title: selectCustomerMateInfo 
	* @Description: TODO(获取配偶信息) 
	* @param @param customerId
	* @param @return    设定文件 
	* @return CustomerMateInfo    返回类型 
	* @throws 
	*/
	CustomerMateInfo selectCustomerMateInfo(String customerId);

	/** 
	* @Title: updateCustomerMateInfo 
	* @Description: TODO(更新配偶信息) 
	* @param @param customerMateInfo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int updateCustomerMateInfo(CustomerMateInfo customerMateInfo);

	/** 
	* @Title: updateCustomerHouseInfoOffline 
	* @Description: TODO(离线修改房产信息) 
	* @param @param customerAssetInfo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int updateCustomerHouseInfoOffline(CustomerAssetInfo customerAssetInfo);

	/** 
	* @Title: updateCustomerVehicleInfoOffline 
	* @Description: TODO(离线修改车辆信息) 
	* @param @param customerAssetInfo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	int updateCustomerVehicleInfoOffline(CustomerAssetInfo customerAssetInfo);
	
	/** 
	* @Title: modifyCustomerPublicUpdateTimeById 
	* @Description: TODO(修改客户更新时间) 
	* @param @param customerId
	* @param @param mobilePhone    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void modifyCustomerPublicInfo(@Param("id") String customerId, @Param("mobilePhone") String mobilePhone);
	
	/** 
	* @Title: modifyLoanProductApply 
	* @Description: TODO(修改借贷申请信息) 
	* @param @param customerId    设定文件 
	* @param @param address    
	* @return void    返回类型 
	* @throws 
	*/
	void insertCustomerLocation(@Param("customerId") String customerId, @Param("eType") String eType, @Param("address") String address);

	/**
	 * 获取会员信息
	* @Title: getCustomerInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param name
	* @param @param idcard
	* @param @param phone
	* @param @return    设定文件 
	* @return CustomerInfo    返回类型 
	* @throws
	 */
	CustomerInfo selectCustomerInfo(@Param("name")String name, @Param("idcard")String idcard, @Param("phone")String phone);
	
	/** 
	* @Title: updateCustomerLocationAddress 
	* @Description: TODO(修改借贷申请信息) 
	* @param @param customerId    设定文件 
	* @param @param address    
	* @return void    返回类型 
	* @throws 
	*/
	void updateCustomerLocationAddress(@Param("customerId") String customerId, @Param("address") String address);
	
	/** 
	* @Title: getHistoryAddress 
	* @Description: TODO(修改借贷申请信息) 
	* @param @param customerId    设定文件    
	* @return void    返回类型 
	* @throws 
	*/
	String getHistoryAddress(@Param("customerId") String customerId);

}
