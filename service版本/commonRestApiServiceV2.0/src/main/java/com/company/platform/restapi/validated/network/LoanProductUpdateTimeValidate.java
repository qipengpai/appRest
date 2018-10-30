package com.company.platform.restapi.validated.network;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.dao.basicdata.BasicDataInfoMapper;
import com.company.platform.restapi.model.basicdata.OrgInfo;
import com.company.platform.restapi.model.basicdata.ProductInfo;
import com.company.platform.security.model.SecurityUser;

@Service
public class LoanProductUpdateTimeValidate {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(LoanProductUpdateTimeValidate.class);

	@Autowired
	BasicDataInfoMapper basicDataInfoMapper;

	public void getProductUpdateTimeValidate(String updateTime) throws ParseException, BusinessException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取系统初始化时间：1970-01-01 08:00:00
		Date date = new Date(0);
		String InitializationTime = dateFormat.format(date);
		Date InitializationTimeCom = dateFormat.parse(InitializationTime);
		Date productUpdateTime = dateFormat.parse(updateTime);
		// 获得当app登录用户
		SecurityUser user = null;
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		// 通过用户id获取当前用户组织机构信息
		if (user != null) {
			OrgInfo orgInfo = basicDataInfoMapper.getOrgInfoByUserId(((SecurityUser) user).getId());
			// 通过组织机构id获取已有权限的产品基本信息
			List<ProductInfo> productInfo = new ArrayList<ProductInfo>();
			productInfo = basicDataInfoMapper.getProductInfo(orgInfo.getOrgId());
			if (productInfo.size() > 0) {
				for (ProductInfo productInfo2 : productInfo) {
					// 产品基本信息更新数据时间(数据库提供)
					Date productInfoUpdateTimeBase = dateFormat.parse(productInfo2.getProductInfoUpdateTime());
					if (InitializationTimeCom.equals(productUpdateTime) || productInfoUpdateTimeBase == null) {
						break;
					} else if (productUpdateTime.before(productInfoUpdateTimeBase)) {
						logger.error("产品需要进行更新");
						throw new BusinessException(ResponseConstants.PRODUCT_NEED_UPDATE.getRetCode(),
								ResponseConstants.PRODUCT_NEED_UPDATE.getRetMsg());
					}
				}
			}
		}
	}

}
