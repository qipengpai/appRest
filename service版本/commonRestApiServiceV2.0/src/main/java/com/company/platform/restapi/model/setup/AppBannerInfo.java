package com.company.platform.restapi.model.setup;


/** 
* @ClassName: AppBannerInfo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 董建 
* @date 2017年12月12日 下午6:24:16 
*  
*/
public class AppBannerInfo {

    private String id;

    private String title;

    private String url;

    private String path;

    private String is_show;

    private String order_id;

    private String create_user;

    private String create_time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIs_show() {
		return is_show;
	}

	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
    
}
