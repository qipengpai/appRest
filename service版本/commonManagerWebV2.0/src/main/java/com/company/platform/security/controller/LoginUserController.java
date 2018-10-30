/**
 * 
 */
package com.company.platform.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @ClassName: LoginUserController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年9月28日 上午11:42:15 
*  
*/
@Controller
public class LoginUserController {
	/** 
	* @Title: login 
	* @Description: TODO(跳转至登陆页面) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping("/")
	public String login(){
		return "security/login";
	}
}
