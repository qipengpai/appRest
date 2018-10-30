package com.company.platform.restapi.service.setup.imp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.restapi.dao.setup.MobileSetUpMapper;
import com.company.platform.restapi.model.setup.AppBannerInfo;
import com.company.platform.restapi.model.setup.AppBannerInfoResp;
import com.company.platform.restapi.service.setup.IAppBannerInfoService;


@Service
public class AppBannerInfoServiceImp implements IAppBannerInfoService {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(AppBannerInfoServiceImp.class);

    @Autowired
    MobileSetUpMapper mobileSetUpMapper;

	@Override
	public List<AppBannerInfoResp> getAppBannerInfo(String appName) {
		logger.info("service层，获取banner信息开始");
		List<AppBannerInfo> appBannerInfos = mobileSetUpMapper.getBannerInfoList(appName);
		List<AppBannerInfoResp> appBannerInfoResps = new ArrayList<AppBannerInfoResp>();
			for (AppBannerInfo appBannerInfo : appBannerInfos) {
				AppBannerInfoResp appBannerInfoResp = new AppBannerInfoResp();
					appBannerInfoResp.setId(appBannerInfo.getId());
					appBannerInfoResp.setPath(appBannerInfo.getPath());
					appBannerInfoResp.setTitle(appBannerInfo.getTitle());
					appBannerInfoResp.setUrl(appBannerInfo.getUrl());
				appBannerInfoResps.add(appBannerInfoResp);
			}
		logger.info("service层，获取banner信息结束");
		return appBannerInfoResps;
	}

}
