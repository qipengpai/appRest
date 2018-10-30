package com.company.platform.restapi.service.index.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.restapi.dao.custom.CustomerPublicInfoMapper;
import com.company.platform.restapi.dao.index.IndexMapper;
import com.company.platform.restapi.dao.loan.LoanCodeMapper;
import com.company.platform.restapi.model.basicdata.ProductInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;
import com.company.platform.restapi.service.index.IndexService;
/**
 * 首页初始化 产品信息、会员信息
* @ClassName: IndeController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年9月12日 下午3:46:36 
*
 */
@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	private CustomerPublicInfoMapper customerPublicInfoMapper;
	@Autowired
	private IndexMapper indexMapper;
	
	@Autowired
	private LoanCodeMapper loanCodeMapper;
	@Override
	public List<ProductInfo> getIndexProductList(String orgId) throws Exception {
		return indexMapper.getIndexProductList(orgId);
	}

	@Override
	public CustomerPublicInfo getCsustomerPublicInfo(String idCard) {
		return customerPublicInfoMapper.selectByIdCard(idCard);
	}

	@Override
	public Map<String, String> getOrgIdByUserId(String userId) {
		
		return loanCodeMapper.getOrgIdByUserId(userId);
	}

	

}
