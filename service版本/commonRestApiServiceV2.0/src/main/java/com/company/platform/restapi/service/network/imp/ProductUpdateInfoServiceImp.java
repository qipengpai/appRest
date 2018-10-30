package com.company.platform.restapi.service.network.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.company.platform.restapi.service.network.IProductUpdateInfoService;


@Service
public class ProductUpdateInfoServiceImp implements IProductUpdateInfoService {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(ProductUpdateInfoServiceImp.class);

	@Override
	public Boolean getProductUpdateTime(String updateTime) {
		// TODO Auto-generated method stub
		return null;
	}

}
