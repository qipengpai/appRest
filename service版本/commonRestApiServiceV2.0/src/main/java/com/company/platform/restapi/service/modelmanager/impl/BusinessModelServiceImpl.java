package com.company.platform.restapi.service.modelmanager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.restapi.dao.modelmanager.BusinessModelMapper;
import com.company.platform.restapi.model.modelmanager.BusColumnInfoModel;
import com.company.platform.restapi.model.modelmanager.BusInfoModel;
import com.company.platform.restapi.model.modelmanager.BusTitleInfoModel;
import com.company.platform.restapi.service.modelmanager.IBusinessModelService;

@Service
public class BusinessModelServiceImpl implements IBusinessModelService {

	@Autowired
	private BusinessModelMapper businessVersionModelMapper;

	@Override
	public List<BusInfoModel> getBusinessModelsByProductId(String productId) {
		return businessVersionModelMapper.getBusinessModelsByProductId(productId);
	}

	@Override
	public List<BusTitleInfoModel> getBusinessTitlesByProductId(String productId) {
		return businessVersionModelMapper.getBusinessTitlesByProductId(productId);
	}

	@Override
	public List<BusColumnInfoModel> getBusinessColumnsByProductId(String productId) {
		return businessVersionModelMapper.getBusinessColumnsByProductId(productId);
	}
}
