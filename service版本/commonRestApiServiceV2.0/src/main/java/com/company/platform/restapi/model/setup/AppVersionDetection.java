package com.company.platform.restapi.model.setup;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;

/**
 * @ClassName: AppVersionDetection
 * @Description: TODO(app 版本更新 )
 * @author zhengjn
 * @date 2017年11月2日 下午4:23:21
 * 
 */
@SuppressWarnings("serial")
public class AppVersionDetection extends BaseHttpParamsAppReq {

	/**
	 * @Fields appType : TODO(app类型)
	 */
	@NotEmpty(message = "app类型")
	private String appType;

	/**
	 * @Fields appVersion : TODO(app版本)
	 */
	@NotEmpty(message = "app版本")
	private String appVersion;

	/**
	 * @Fields appName : TODO(APP名称)
	 */
	@NotEmpty(message = "APP名称")
	private String appName;

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
