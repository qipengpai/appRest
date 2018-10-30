package com.company.platform.restapi.model.setup;

/**
 * @ClassName: AppVersion
 * @Description: TODO(app版本)
 * @author 董建
 * @date 2017年12月12日 下午4:03:18
 * 
 */
public class AppVersionResp {

	private String status;

	private String version;

	private String url;

	private String size;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
