package com.company.platform.wechat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.service.activiti.WeiXinActivitiService;
import com.company.platform.base.service.dataDictionary.IDataDictionaryService;
import com.company.platform.base.util.MD5Util;
import com.company.platform.restapi.model.custom.CustomerInfo;
import com.company.platform.restapi.service.custom.ICustomerInfoService;
import com.company.platform.security.model.SecurityUser;
import com.company.platform.security.service.WeixinCustomUserDetailsService;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import me.chanjar.weixin.common.error.WxErrorException;


/**
 * 微信页面跳转controller
* @ClassName: WechatController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年9月12日 上午11:21:43 
*
 */
@Controller
@RequestMapping("/wechat")
public class WechatController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WxMaService wxService;
	@Autowired
	WeixinCustomUserDetailsService weixinCustomUserDetailsService;
	@Autowired	
	private IDataDictionaryService dataDictionaryService;
	@Resource
	private ICustomerInfoService customerInfoService;

	@Resource
	private WeiXinActivitiService weiXinActivitiService;
	
	@RequestMapping(path = "/login")
	public String login(ModelMap map){
		return "wechat/login";
	}
	
	/**
	 * 获取用户信息
	* @Title: getOpenUser 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param openId
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/getOpenUser")
	@ResponseBody
	public Map<String,String> getOpenUser(String openId){
		Map<String,String> result = new HashMap<String,String>();
		SecurityUser user = weixinCustomUserDetailsService.getUserDetails(openId);
		if(user!=null){
			result.put("status", "200");
			result.put("userId", user.getId());
			result.put("name", user.getRealName());
		}else{
			result.put("status", "500");
			result.put("userId", "");
			result.put("name", "");
		}
		return result;
	}
	
	/**
	 * 系统加载页面
	* @Title: loading 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/loading")
	public String loading(ModelMap map){
		return "wechat/loading";
	}
	
	@RequestMapping(path = "/loan_apply")
	public String loanApply(Model model){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String loanProductApplyId  = request.getParameter("loanProductApplyId");
		model.addAttribute("loanProductApplyId",loanProductApplyId);
		return "wechat/loan_apply";
	}
	@RequestMapping(path = "/loan_view")
	public String loanView(Model model){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String loanProductApplyId  = request.getParameter("loanProductApplyId");
		model.addAttribute("loanProductApplyId",loanProductApplyId);
		return "wechat/loan_view";
	}
	
	@RequestMapping(path = "/apply_manage")
	public String applyManage(Model model){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();

		String taskStatus  = request.getParameter("taskStatus");
		System.out.println(taskStatus);
		model.addAttribute("taskStatus",taskStatus);
		return "wechat/apply_manage";
	}
	
	/**
	 * 图片跳转
	* @Title: image 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/image_view")
	public String imageView(Model model){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String loanProductApplyId  = request.getParameter("loanProductApplyId");
		model.addAttribute("loanProductApplyId",loanProductApplyId);
		return "wechat/image_view";
	}
	
	/**
	 * 图片查询
	* @Title: image 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/image")
	public String image(Model model){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String loanProductApplyId  = request.getParameter("loanProductApplyId");
		model.addAttribute("loanProductApplyId",loanProductApplyId);
		return "wechat/image";
	}
	/**
	 * 首页跳转
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/main")
	public String main(ModelMap map){
		return "wechat/main";
	}
	
	@RequestMapping(path = "/manual_check")
	public String manualCheck(ModelMap model){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();

		String loanProductApplyId  = request.getParameter("loanProductApplyId");
		System.out.println(loanProductApplyId);
		model.addAttribute("loanProductApplyId",loanProductApplyId);
		return "wechat/manual_check";
	}
	
	@RequestMapping(path = "/result_detail")
	public String resultDetail(ModelMap model){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();

		String loanProductApplyId  = request.getParameter("loanProductApplyId");
		System.out.println(loanProductApplyId);
		model.addAttribute("loanProductApplyId",loanProductApplyId);
		return "wechat/result_detail";
	}
	
	/**
	 * 用户授权接口
	* @Title: userAuthorization 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param username
	* @param @param password
	* @param @param openId
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/userAuthorization", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> userAuthorization(String username ,String password,String openId){
		Map<String,Object> result = new HashMap<String,Object>();
		if(!StringUtils.isBlank(username)&&!StringUtils.isBlank(password)&&!StringUtils.isBlank(openId)){
			SecurityUser user = weixinCustomUserDetailsService.loadUserByUsername(username);
			if(user!=null&&user.getPassword().equals(MD5Util.encode(password))){
				String type = weixinCustomUserDetailsService.roleTypeByUserId(user.getId());
				if(type != null && !type.equals("") && type.equals("00") ) {
					//登陆成功
					result.put("status", 200);
			    	result.put("openId", openId);
			    	result.put("name", user.getRealName());
			    	result.put("userId", user.getId());
			    	result.put("message","登陆成功！");
			    	this.logger.info("登陆成功！");
			    	//更新openId
			    	weixinCustomUserDetailsService.upUserOpenId(username, openId);
			    	//刷新缓存
			    	weixinCustomUserDetailsService.setUserDetails(openId, user);
				} else {
					result.put("status", 500);
			    	result.put("openId", openId);
			    	result.put("name", "");
			    	result.put("userId", "");
			    	result.put("message","登录用户非业务员！");
			    	this.logger.info("登录用户非业务员！");
				}
			} else {
				result.put("status", 500);
		    	result.put("openId", openId);
		    	result.put("name", "");
		    	result.put("userId", "");
		    	result.put("message","登陆名或密码错误！");
		    	this.logger.info("登陆名或密码错误！");
			}
		}else{
			result.put("status", 500);
	    	result.put("openId", "");
	    	result.put("message","登陆名或密码错误！");
	    	this.logger.info("参数为空！");
		}
		
		return result;
	}
	
	
	
	//获得微信授权
	@RequestMapping(path = "/getWechatOpenId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getWechatOpenId(String code) {
		Map<String,Object> result = new HashMap<String,Object>();
		this.logger.info("客户端传递code:"+code);
		
		if(!StringUtils.isBlank(code)){
			String tempOpenId = weixinCustomUserDetailsService.getCodeOrOpenId(code);
			if(tempOpenId==null){
			//获取用户信息--optionId
		    WxMaJscode2SessionResult session = null;
			try {
				session = this.wxService.getUserService().getSessionInfo(code);
			} catch (WxErrorException e) {
				result.put("status", 500);
		    	result.put("openId", "");
				result.put("message","微信获取openId异常！");
		    	this.logger.error("微信获取openId异常");
				e.printStackTrace();
			}
		    if(session!=null&&session.getOpenid()!=null){
		    	result.put("status", 200);
		    	result.put("name","");
		    	result.put("openId", session.getOpenid());
		    	result.put("message","微信openId获取成功！");
		    	//缓存code 防止客户端同一code多次查询
		    	weixinCustomUserDetailsService.setCodeOrOpenId(code, session.getOpenid());
		    	//已授权
				SecurityUser user= weixinCustomUserDetailsService.loadUserByOpenId(session.getOpenid());
				if(user!=null){
					result.put("name", user.getRealName());
					result.put("userStatus", true);
					weixinCustomUserDetailsService.setUserDetails(session.getOpenid(), user);
				}else{
					result.put("name","");
					result.put("userStatus", false);
				}
		    	this.logger.info("微信openId获取成功！");
		    }else{
		    	result.put("status", 500);
		    	result.put("openId", "");
		    	result.put("name","");
		    	result.put("message","微信openId获取失败！");
		    	this.logger.info("微信openId获取失败！");
		    }
		    
		  }else{
			    SecurityUser user= weixinCustomUserDetailsService.loadUserByOpenId(tempOpenId);
			    if(user!=null){
			    	result.put("openId", tempOpenId);
					result.put("name", user.getRealName());
					result.put("userStatus", true);
				}else{
					SecurityUser usersql= weixinCustomUserDetailsService.loadUserByOpenId(tempOpenId);
					if(usersql==null){
						result.put("name","");
						result.put("openId", tempOpenId);
						result.put("userStatus", false);
					}else{
						weixinCustomUserDetailsService.setUserDetails(tempOpenId, usersql);
						result.put("openId", tempOpenId);
						result.put("name", usersql.getRealName());
						result.put("userStatus", true);
					}
				}
			    result.put("status", 200);
		    	result.put("message","微信openId获取成功！"); 
		  }
		}else{
			result.put("status", 500);
	    	result.put("openId", "");
	    	result.put("message","缺少必要参数code！");
	    	this.logger.info("参数code为空！");
		}
		return result;
	}
	
	@RequestMapping(path = "/getWxJsApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getWxJsApi()throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		
		
		return result;
	}
	
	@RequestMapping(path = "/checkyys", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> checkyys(String idCard,String name,String phone)throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		CustomerInfo info =customerInfoService.selectCustomerInfo(name, idCard, phone);
		if(info==null){
			Map<String,Object> resultMap = weiXinActivitiService.checkYyssys(name, idCard, phone);
			if(!resultMap.get("code").toString().equals("y")){
				result.put("status", 500);
		    	result.put("message",resultMap.get("message") );
				return result;
			}
		}
		result.put("status", 200);
    	result.put("message", "运营商三要素查询一致！");
		return result;
	}
	
	
	
	
	@RequestMapping(path = "/getAllRole", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public BaseHttpParamsResp getCode(@RequestBody @Validated BaseHttpParamsAppReq baseHttpParamsAppReq)
			throws Exception {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		BaseHttpParamsResp baseHttpParamsResp = BaseHttpParamsResp.requestSuccess("获取在线用户信息成功", data);
		baseHttpParamsResp.createSign();// 生成验签
		return baseHttpParamsResp;
	}
	
	
	@RequestMapping(path = "/getDataMap", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public BaseHttpParamsResp getDataMap(String type)throws Exception {
		Map<String,Map<String,Object>> dic = dataDictionaryService.getDataMap();
		Map<String,Object> credentialTypeMap = dic.get(type);
		BaseHttpParamsResp baseHttpParamsResp = BaseHttpParamsResp.requestSuccess("成功", credentialTypeMap);
		return baseHttpParamsResp;
	}


	/**
	 * @Author qipengpai
	 * @Description //TODO 用户退出
	 * @Date 9:19 2018/9/28
	 * @Param [map]
	 * @return java.lang.String
	 **/
	@RequestMapping(path = "/logout")
	public String logout(String openId){
        //刷新缓存 清除数据
        Boolean flag =weixinCustomUserDetailsService.delUserDetails(openId);
        logger.info("用户退出 "+flag);
		return "wechat/login";
	}


	/**
	 * @Author qipengpai
	 * @Description //TODO 根据用户openId判断用户是否绑定
	 * @Date 16:58 2018/9/29
	 * @Param [openId]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
    @RequestMapping(path = "/getStatusByOpenId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getStatusByOpenId(String openId) {
        Map<String,Object> result = new HashMap<String,Object>();
        this.logger.info("客户端传递openId:"+openId);
        if(!StringUtils.isBlank(openId)){
            SecurityUser user= weixinCustomUserDetailsService.loadUserByOpenId(openId);
            if(user==null){
                result.put("name","");
                result.put("openId", openId);
                result.put("userStatus", false);
            }else{
                weixinCustomUserDetailsService.setUserDetails(openId, user);
                result.put("openId", openId);
                result.put("name", user.getRealName());
                result.put("userStatus", true);
            }
            result.put("status", 200);
            result.put("message","微信openId获取成功！");
        }
        return result;
    }
	
}
