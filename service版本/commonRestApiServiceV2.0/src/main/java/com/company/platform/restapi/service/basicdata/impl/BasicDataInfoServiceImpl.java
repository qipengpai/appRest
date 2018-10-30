package com.company.platform.restapi.service.basicdata.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.dao.basicdata.BasicDataInfoMapper;
import com.company.platform.restapi.model.basicdata.BasicDataInfoReq;
import com.company.platform.restapi.model.basicdata.BasicDataInfoResp;
import com.company.platform.restapi.model.basicdata.CityInfo;
import com.company.platform.restapi.model.basicdata.ConfigInfo;
import com.company.platform.restapi.model.basicdata.DicInfo;
import com.company.platform.restapi.model.basicdata.DicRangeInfo;
import com.company.platform.restapi.model.basicdata.JurisdictionInfo;
import com.company.platform.restapi.model.basicdata.ModelBusCodeAndVersion;
import com.company.platform.restapi.model.basicdata.ModelBusColumnInfo;
import com.company.platform.restapi.model.basicdata.ModelBusColumnInfoProp;
import com.company.platform.restapi.model.basicdata.ModelBusInfo;
import com.company.platform.restapi.model.basicdata.ModelBusInfoProp;
import com.company.platform.restapi.model.basicdata.ModelBusTitleInfo;
import com.company.platform.restapi.model.basicdata.ModelBusTitleInfoProp;
import com.company.platform.restapi.model.basicdata.ModelImgInfo;
import com.company.platform.restapi.model.basicdata.ModelImgInfoProp;
import com.company.platform.restapi.model.basicdata.OrgDeptInfo;
import com.company.platform.restapi.model.basicdata.OrgInfo;
import com.company.platform.restapi.model.basicdata.PosterInfo;
import com.company.platform.restapi.model.basicdata.ProcdefInfo;
import com.company.platform.restapi.model.basicdata.ProductInfo;
import com.company.platform.restapi.model.basicdata.RoleInfo;
import com.company.platform.restapi.model.basicdata.UserInfo;
import com.company.platform.restapi.service.basicdata.IBasucDataInfoService;
import com.company.platform.security.model.SecurityUser;

/**
 * 
 * @ClassName: BasicDataInfoServiceImp
 * @Description: TODO(更新基础数据信息实现类)
 * @author liang
 * @date 2018年1月24日 下午7:49:14
 *
 */
@Service
public class BasicDataInfoServiceImpl implements IBasucDataInfoService {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(BasicDataInfoServiceImpl.class);

	@Autowired
	BasicDataInfoMapper basicDataInfoMapper;

	/*
	 * (非 Javadoc) <p>Title: updateBasicData</p> <p>Description: </p>
	 * 
	 * @param basicDataInfoReq
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.basicdata.IBasucDataInfoService#
	 * updateBasicData(com.company.platform.restapi.model.basicdata.
	 * BasicDataInfoReq)
	 */
	@Override
	public BasicDataInfoResp updateBasicData(BasicDataInfoReq basicDataInfoReq)
			throws BusinessException, ParseException {
		// 获得当app登录用户
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BasicDataInfoResp basicDataInfoResp = new BasicDataInfoResp();
		// 获取当前用户信息
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取系统初始化时间：1970-01-01 08:00:00
		Date date = new Date(0);
		String InitializationTime = dateFormat.format(date);
		Date InitializationTimeCom = dateFormat.parse(InitializationTime);
		// 角色信息更新数据时间(app提供)
		Date roleInfoUpdateTime = dateFormat.parse(basicDataInfoReq.getRoleInfoUpdateTime());
		// 岗位信息更新数据时间(app提供)
		Date posterInfoUpdateTime = dateFormat.parse(basicDataInfoReq.getPosterInfoUpdateTime());
		// 组织机构信息更新数据时间(app提供)
		Date orgInfoUpdateTime = dateFormat.parse(basicDataInfoReq.getOrgInfoUpdateTime());
		// 字典信息更新数据时间(app提供)
		Date dicRangeInfoUpdateTime = dateFormat.parse(basicDataInfoReq.getDicRangeInfoUpdateTime());
		// 字典字段更新数据时间(app提供)
		Date dicInfoUpdateTime = dateFormat.parse(basicDataInfoReq.getDicInfoUpdateTime());
		// 全局变量更新数据时间(app提供)
		Date configInfoUpdateTime = dateFormat.parse(basicDataInfoReq.getConfigInfoUpdateTime());
		// 行政区划更新数据时间(app提供)
		Date cityInfoUpdateTime = dateFormat.parse(basicDataInfoReq.getCityInfoUpdateTime());
		// 产品信息更新数据时间(app提供)
		Date productInfoUpdateTime = dateFormat.parse(basicDataInfoReq.getProductInfoUpdateTime());
		// 产品组织机构更新数据时间(app提供)
		Date orgDeptInfoUpdateTime = dateFormat.parse(basicDataInfoReq.getOrgDeptInfoUpdateTime());
		// 流程信息更新数据时间(app提供)
		Date procdefInfoUpdateTime = dateFormat.parse(basicDataInfoReq.getProcdefInfoUpdateTime());

		logger.info("获取用户角色信息开始");
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(((SecurityUser) user).getId());
		userInfo.setRealName(((SecurityUser) user).getRealName());
		userInfo.setUserName(((SecurityUser) user).getUsername());
		// 通过用户id获取用户更新时间
		String userUpdateTime = basicDataInfoMapper.getUserUpdateTimeInfoByUserId(userInfo.getUserId());
		userInfo.setUserInfoUpdateTime(userUpdateTime);
		basicDataInfoResp.setUserInfo(userInfo);
		// 通过用户id获取当前用户角色信息
		List<RoleInfo> roleInfo = new ArrayList<RoleInfo>();
		boolean flag = true;
		roleInfo = basicDataInfoMapper.getRoleInfoByUserId(((SecurityUser) user).getId());
		if (roleInfo.size() > 0) {
			for (RoleInfo roleInfo2 : roleInfo) {
				// 角色信息更新数据时间(数据库提供)
				Date roleInfoUpdateTimeBase = dateFormat.parse(roleInfo2.getRoleInfoUpdateTime());
				if (InitializationTimeCom.equals(roleInfoUpdateTime) || roleInfoUpdateTimeBase == null) {
					flag = true;
					break;
				} else if (roleInfoUpdateTime.after(roleInfoUpdateTimeBase)) {
					flag = false;
					continue;
				} else if (roleInfoUpdateTime.before(roleInfoUpdateTimeBase)) {
					flag = true;
					break;
				} else if (roleInfoUpdateTime.equals(roleInfoUpdateTimeBase)) {
					flag = false;
					continue;
				}
			}
			
		}
		if (flag) {
			basicDataInfoResp.setRoleInfo(roleInfo);
		}else{
			basicDataInfoResp.setRoleInfo(null);
		}
		logger.info("获取用户角色信息结束");
		logger.info("获取用户岗位信息开始");
		// 通过用户id获取当前用户岗位信息
		List<PosterInfo> posterInfo = new ArrayList<PosterInfo>();
		flag = true;
		posterInfo = basicDataInfoMapper.getPosterInfoByUserId(((SecurityUser) user).getId());
		if (posterInfo.size() > 0) {
			for (PosterInfo posterInfo2 : posterInfo) {
				// 角色信息更新数据时间(数据库提供)
				Date posterInfoUpdateTimeBase = dateFormat.parse(posterInfo2.getPosterInfoUpdateTime());
				if (InitializationTimeCom.equals(posterInfoUpdateTime) || posterInfoUpdateTimeBase == null) {
					flag = true;
					break;
				} else if (posterInfoUpdateTime.after(posterInfoUpdateTimeBase)) {
					flag = false;
					continue;
				} else if (posterInfoUpdateTime.before(posterInfoUpdateTimeBase)) {
					flag = true;
					break;
				} else if (posterInfoUpdateTime.equals(posterInfoUpdateTimeBase)) {
					flag = false;
					continue;
				}
			}
		}
		if (flag) {
			basicDataInfoResp.setPosterInfo(posterInfo);
		}else{
			basicDataInfoResp.setPosterInfo(null);
		}
		logger.info("获取用户岗位信息结束");
		logger.info("获取用户组织机构信息开始");
		// 通过用户id获取当前用户组织机构信息
		OrgInfo orgInfo = basicDataInfoMapper.getOrgInfoByUserId(((SecurityUser) user).getId());
		flag = true;
		// 组织机构信息更新数据时间(数据库提供)
		Date orgInfoUpdateTimeBase = dateFormat.parse(orgInfo.getOrgInfoUpdateTime());
		if (InitializationTimeCom.equals(orgInfoUpdateTime) || orgInfoUpdateTimeBase == null) {
			flag = true;
		} else if (orgInfoUpdateTime.after(orgInfoUpdateTimeBase)) {
			flag = false;
		} else if (orgInfoUpdateTime.before(orgInfoUpdateTimeBase)) {
			flag = true;
		} else if (orgInfoUpdateTime.equals(orgInfoUpdateTimeBase)) {
			flag = false;
		}
		if (flag) {
			basicDataInfoResp.setOrgInfo(orgInfo);
		}else{
			basicDataInfoResp.setOrgInfo(null);
		}
		logger.info("获取用户组织机构信息结束");
		logger.info("获取字典父节点信息开始");
		// 获取当前数据字典父节点信息
		List<DicRangeInfo> dicRangeInfo = new ArrayList<DicRangeInfo>();
		flag = true;
		dicRangeInfo = basicDataInfoMapper.getDicRangeInfo();
		if (dicRangeInfo.size() > 0) {
			for (DicRangeInfo dicRangeInfo2 : dicRangeInfo) {
				// 数据字典父节点信息更新数据时间(数据库提供)
				Date dicRangeInfoUpdateTimeBase = dateFormat.parse(dicRangeInfo2.getDicRangeInfoUpdateTime());
				if (InitializationTimeCom.equals(dicRangeInfoUpdateTime) || dicRangeInfoUpdateTimeBase == null) {
					flag = true;
					break;
				} else if (dicRangeInfoUpdateTime.after(dicRangeInfoUpdateTimeBase)) {
					flag = false;
					continue;
				} else if (dicRangeInfoUpdateTime.before(dicRangeInfoUpdateTimeBase)) {
					flag = true;
					break;
				} else if (dicRangeInfoUpdateTime.equals(dicRangeInfoUpdateTimeBase)) {
					flag = false;
					continue;
				}
			}
		}
		if (flag) {
			basicDataInfoResp.setDicRangeInfo(dicRangeInfo);
		}else{
			basicDataInfoResp.setDicRangeInfo(null);
		}
		logger.info("获取字典父节点信息结束");
		logger.info("获取字典子节点信息开始");
		// 获取当前数据字典子节点信息
		List<DicInfo> dicInfo = new ArrayList<DicInfo>();
		flag = true;
		dicInfo = basicDataInfoMapper.getDicInfo();
		if (dicInfo.size() > 0) {
			for (DicInfo dicInfo2 : dicInfo) {
				// 数据字典父节点信息更新数据时间(数据库提供)
				Date dicInfoUpdateTimeBase = dateFormat.parse(dicInfo2.getDicInfoUpdateTime());
				if (InitializationTimeCom.equals(dicInfoUpdateTime) || dicInfoUpdateTimeBase == null) {
					flag = true;
					break;
				} else if (dicInfoUpdateTime.after(dicInfoUpdateTimeBase)) {
					flag = false;
					continue;
				} else if (dicInfoUpdateTime.before(dicInfoUpdateTimeBase)) {
					flag = true;
					break;
				} else if (dicInfoUpdateTime.equals(dicInfoUpdateTimeBase)) {
					flag = false;
					continue;
				}
			}
		}
		if (flag) {
			basicDataInfoResp.setDicInfo(dicInfo);
		}else{
			basicDataInfoResp.setDicInfo(null);
		}
		logger.info("获取字典子节点信息结束");
		logger.info("获取当前全局变量信息开始");
		// 获取当前全局变量信息
		List<ConfigInfo> configInfo = new ArrayList<ConfigInfo>();
		flag = true;
		configInfo = basicDataInfoMapper.getConfigInfo();
		if (configInfo.size() > 0) {
			for (ConfigInfo configInfo2 : configInfo) {
				// 全局变量信息更新数据时间(数据库提供)
				Date configInfoUpdateTimeBase = dateFormat.parse(configInfo2.getConfigInfoUpdateTime());
				if (InitializationTimeCom.equals(configInfoUpdateTime) || configInfoUpdateTimeBase == null) {
					flag = true;
					break;
				} else if (configInfoUpdateTime.after(configInfoUpdateTimeBase)) {
					flag = false;
					continue;
				} else if (configInfoUpdateTime.before(configInfoUpdateTimeBase)) {
					flag = true;
					break;
				} else if (configInfoUpdateTime.equals(configInfoUpdateTimeBase)) {
					flag = false;
					continue;
				}
			}
		}
		if (flag) {
			basicDataInfoResp.setConfigInfo(configInfo);
		}else{
			basicDataInfoResp.setConfigInfo(null);
		}
		logger.info("获取当前全局变量信息结束");
		logger.info("获取当前行政区划信息开始");
		// 获取当前行政区划信息
		List<CityInfo> cityInfo = new ArrayList<CityInfo>();
		flag = true;
		cityInfo = basicDataInfoMapper.getCityInfo();
		if (cityInfo.size() > 0) {
			for (CityInfo cityInfo2 : cityInfo) {
				// 行政区划信息更新数据时间(数据库提供)
				Date cityInfoUpdateTimeBase = dateFormat.parse(cityInfo2.getCityInfoUpdateTime());
				if (InitializationTimeCom.equals(cityInfoUpdateTime) || cityInfoUpdateTimeBase == null) {
					flag = true;
					break;
				} else if (cityInfoUpdateTime.after(cityInfoUpdateTimeBase)) {
					flag = false;
					continue;
				} else if (cityInfoUpdateTime.before(cityInfoUpdateTimeBase)) {
					flag = true;
					break;
				} else if (cityInfoUpdateTime.equals(cityInfoUpdateTimeBase)) {
					flag = false;
					continue;
				}
			}
		}
		if (flag) {
			basicDataInfoResp.setCityInfo(cityInfo);
		}else{
			basicDataInfoResp.setCityInfo(null);
		}
		logger.info("获取当前行政区划信息结束");
		logger.info("通过组织机构id获取已有权限的产品基本信息开始");
		// 通过组织机构id获取已有权限的产品基本信息
		List<ProductInfo> productInfo = new ArrayList<ProductInfo>();
		flag = true;
		productInfo = basicDataInfoMapper.getProductInfo(orgInfo.getOrgId());
		if (productInfo.size() > 0) {
			for (ProductInfo productInfo2 : productInfo) {
				// 产品基本信息更新数据时间(数据库提供)
				Date productInfoUpdateTimeBase = dateFormat.parse(productInfo2.getProductInfoUpdateTime());
				if (InitializationTimeCom.equals(productInfoUpdateTime) || productInfoUpdateTimeBase == null) {
					flag = true;
					break;
				} else if (productInfoUpdateTime.after(productInfoUpdateTimeBase)) {
					flag = false;
					continue;
				} else if (productInfoUpdateTime.before(productInfoUpdateTimeBase)) {
					flag = true;
					break;
				} else if (productInfoUpdateTime.equals(productInfoUpdateTimeBase)) {
					flag = false;
					continue;
				}
			}
		}
		if (flag) {
			if (productInfo.size() > 0) {
				for (ProductInfo productInfo2 : productInfo) {
					Map<String, String> modelVersionInfo = new HashMap<String, String>();
					modelVersionInfo.put("id", productInfo2.getId());
					modelVersionInfo.put("modelType", "BUM");
					// 通过产品id和业务模型类型（BUM）获取业务模型版本号
					ModelBusCodeAndVersion modelBusCodeAndVersion = basicDataInfoMapper
							.getBusModelCodeAndVersion(modelVersionInfo);
					// 通过code和version获取模型信息
					if (modelBusCodeAndVersion != null) {
						Map<String, String> modelInfo = new HashMap<String, String>();
						modelInfo.put("code", modelBusCodeAndVersion.getCode());
						modelInfo.put("version", modelBusCodeAndVersion.getVersion());
						ModelBusInfo modelBusInfo = basicDataInfoMapper.getModelBusInfo(modelInfo);
						if (modelBusInfo != null) {
							productInfo2.setBusModelId(modelBusInfo.getId());
						}
					}
					modelVersionInfo.put("modelType", "IMM");
					// 通过产品id和影像模型类型（IMM）获取影像模型版本号
					ModelBusCodeAndVersion modelImgCodeAndVersion = basicDataInfoMapper
							.getBusModelCodeAndVersion(modelVersionInfo);
					// 通过code和version获取模型信息
					if (modelImgCodeAndVersion != null) {
						Map<String, String> modelClassInfo = new HashMap<String, String>();
						modelClassInfo.put("code", modelImgCodeAndVersion.getCode());
						modelClassInfo.put("version", modelImgCodeAndVersion.getVersion());
						String modelImgId = basicDataInfoMapper.getModelImgId(modelClassInfo);
						productInfo2.setImgModelId(modelImgId);
					}
				}
			}
			basicDataInfoResp.setProductInfo(productInfo);
		}else{
			basicDataInfoResp.setProductInfo(null);
		}
		logger.info("通过组织机构id获取已有权限的产品基本信息结束");
		logger.info("获取所有模型信息开始");
		//获取业务模型信息
		List<ModelBusInfo> modelBusInfoResp = new ArrayList<ModelBusInfo>();
		modelBusInfoResp = basicDataInfoMapper.getModelBusInfoProp();
		basicDataInfoResp.setModelBusInfo(modelBusInfoResp);
		//获取业务模型分类信息
		List<ModelBusTitleInfo> modelBusTitleInfoResp = new ArrayList<ModelBusTitleInfo>();
		modelBusTitleInfoResp = basicDataInfoMapper.getModelBusTitleInfoProp();
		basicDataInfoResp.setModelBusTitleInfo(modelBusTitleInfoResp);
		//获取业务模型字段信息
		List<ModelBusColumnInfo> modelBusColumnInfoResp = new ArrayList<ModelBusColumnInfo>();
		modelBusColumnInfoResp = basicDataInfoMapper.getModelBusColumnInfoProp();
		basicDataInfoResp.setModelBusColumnInfo(modelBusColumnInfoResp);
		//获取影像模型信息
		List<ModelImgInfo> modelImgInfoResp = new ArrayList<ModelImgInfo>();
		modelImgInfoResp = basicDataInfoMapper.getModelImgInfoProp();
		basicDataInfoResp.setModelImgInfo(modelImgInfoResp);
		logger.info("获取所有模型信息结束");
		logger.info("获取当前组织机构产品关联信息开始");
		// 获取当前组织机构产品关联信息
		if(basicDataInfoResp.getProductInfo() != null){
			if (basicDataInfoResp.getProductInfo().size() > 0) {
				List<OrgDeptInfo> orgDeptInfo = new ArrayList<OrgDeptInfo>();
				// 通过当前登录人组织机构获取产品关联信息
				orgDeptInfo = basicDataInfoMapper.getOrgDeptInfo(orgInfo.getOrgId());
				/*if (orgDeptInfo.size() > 0) {
					for (OrgDeptInfo orgDeptInfo2 : orgDeptInfo) {
						// 更新数据时间(数据库提供)
						Date orgDeptInfoUpdateTimeBase = dateFormat.parse(orgDeptInfo2.getOrgDeptInfoUpdateTime());
						if (InitializationTimeCom.equals(orgDeptInfoUpdateTime) || orgDeptInfoUpdateTimeBase == null) {
							flag = true;
						} else if (orgDeptInfoUpdateTime.after(orgDeptInfoUpdateTimeBase)) {
							flag = false;
							continue;
						} else if (orgDeptInfoUpdateTime.before(orgDeptInfoUpdateTimeBase)) {
							flag = true;
						} else if (orgDeptInfoUpdateTime.equals(orgDeptInfoUpdateTimeBase)) {
							flag = false;
							continue;
						}
					}
				}
				if (flag) {
					basicDataInfoResp.setOrgDeptInfo(orgDeptInfo);
				}else{
					basicDataInfoResp.setOrgDeptInfo(null);
				}*/
				basicDataInfoResp.setOrgDeptInfo(orgDeptInfo);
			}
		}else{
			basicDataInfoResp.setOrgDeptInfo(null);
		}
		logger.info("获取当前组织机构产品关联信息结束");
		logger.info("通过组织机构id获取产品中的流程key开始");
		// 通过组织机构id获取产品中的流程key
		List<ProcdefInfo> procdefInfo = new ArrayList<ProcdefInfo>();
		List<JurisdictionInfo> jurisdictionInfoResp = new ArrayList<JurisdictionInfo>();
		procdefInfo = basicDataInfoMapper.getProcdefInfo(orgInfo.getOrgId());
		flag = true;
		if(procdefInfo != null){
			if (procdefInfo.size() > 0) {
				for (ProcdefInfo procdefInfo2 : procdefInfo) {
					// 产品基本信息更新数据时间(数据库提供)
					Date procdefInfoUpdateTimeBase = dateFormat.parse(procdefInfo2.getProcdefInfoUpdateTime());
					if (InitializationTimeCom.equals(procdefInfoUpdateTime) || procdefInfoUpdateTimeBase == null) {
						flag = true;
						break;
					} else if (procdefInfoUpdateTime.after(procdefInfoUpdateTimeBase)) {
						flag = false;
						continue;
					} else if (procdefInfoUpdateTime.before(procdefInfoUpdateTimeBase)) {
						flag = true;
						break;
					} else if (procdefInfoUpdateTime.equals(procdefInfoUpdateTimeBase)) {
						flag = false;
						continue;
					}
					
				}
				if (flag) {
					basicDataInfoResp.setProcdefInfo(procdefInfo);
					for (ProcdefInfo procdefInfo2 : procdefInfo) {
						Map<String, String> jurisdictionInfoReq = new HashMap<String, String>();
						jurisdictionInfoReq.put("productId", procdefInfo2.getProductId());
						jurisdictionInfoReq.put("procdefKey", procdefInfo2.getProcdefKey());
						List<JurisdictionInfo> jurisdictionInfo = new ArrayList<JurisdictionInfo>();
						// 获取当前权限信息
						jurisdictionInfo = basicDataInfoMapper.getJurisdictionInfoPro(jurisdictionInfoReq);
						if (jurisdictionInfo.size() > 0) {
							for (JurisdictionInfo jurisdictionInfo2 : jurisdictionInfo) {
								jurisdictionInfoResp.add(jurisdictionInfo2);
							}
						}
					}
				}
				basicDataInfoResp.setJurisdictionInfo(jurisdictionInfoResp);
			}
		}
		
		logger.info("通过组织机构id获取产品中的流程key结束");

		return basicDataInfoResp;
	}
}
