package com.company.platform.restapi.controller.setup;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.platform.base.aop.requestAccess.RequestAccessAnnotation;
import com.company.platform.base.baseenum.RequestAccessConstants;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.restapi.model.setup.ModifyPwdMessage;
import com.company.platform.restapi.model.setup.QuitMobileLogin;
import com.company.platform.restapi.service.setup.IMobileSetUpService;

/**
 * 
* @ClassName: SetUpController 
* @Description: TODO(设置页面) 
* @author zhengjn 
* @date 2017年10月30日 下午4:43:00 
*
 */
@RestController
@RequestMapping("/appApi")
public class MobileSetUpController extends BaseController {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(MobileSetUpController.class);

	@Resource
	IMobileSetUpService mobileSetUpService;

	@Resource
	SessionRegistry sessionRegistry;

	/**
	 * 
	* @Title: modifyPwd 
	* @Description: TODO(修改密码) 
	* @param @param modifyPwdMessage
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/modifyPwd", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "设置", modelType = RequestAccessConstants.UPDATE, desc = "修改密码")
	public BaseHttpParamsResp modifyPwd(@RequestBody @Validated ModifyPwdMessage modifyPwdMessage) throws Exception {
		logger.info("修改密码开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		modifyPwdMessage = (ModifyPwdMessage) this.decrypt(modifyPwdMessage);
		String result = mobileSetUpService.userModifyPwd(modifyPwdMessage);

		if (ResponseConstants.USER_MODIFY_PWD_SUCCESS.getRetCode().equals(result)) {
			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(
					ResponseConstants.USER_MODIFY_PWD_SUCCESS.getRetCode(),
					ResponseConstants.USER_MODIFY_PWD_SUCCESS.getRetMsg(), "");
		} else if (ResponseConstants.USER_MODIFY_PWD_WRONG.getRetCode().equals(result)) {
			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.USER_MODIFY_PWD_WRONG.getRetCode(),
					ResponseConstants.USER_MODIFY_PWD_WRONG.getRetMsg(), "");
		} else {
			baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.USER_MODIFY_PWD_FAIL.getRetCode(),
					ResponseConstants.USER_MODIFY_PWD_FAIL.getRetMsg(), "");
		}

		baseHttpParamsResp.createSign();
		logger.info("修改密码结束");
		return baseHttpParamsResp;
	}

	/**
	 * 
	* @Title: quitMobileLogin 
	* @Description: TODO(退出登陆) 
	* @param @param baseHttpParamsAppReq
	* @param @return
	* @param @throws Exception    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws
	 */
	@RequestMapping(path = "/quitMobileLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@RequestAccessAnnotation(modelName = "设置", modelType = RequestAccessConstants.DELETE, desc = "退出登陆")
	public BaseHttpParamsResp quitMobileLogin(@RequestBody @Validated QuitMobileLogin quitMobileLogin)
			throws Exception {
		logger.info("退出登陆开始");
		BaseHttpParamsResp baseHttpParamsResp = null;
		baseHttpParamsResp = BaseHttpParamsResp.requestSuccess(ResponseConstants.QUIT_MOBILE_LOGIN_SUCCESS.getRetCode(),
				ResponseConstants.QUIT_MOBILE_LOGIN_SUCCESS.getRetMsg(), "");
		String sessionId = this.session.getId();
		baseHttpParamsResp.createSign();
		if (StringUtils.isEmpty(sessionId)) {
			return baseHttpParamsResp;
		}
		try {
			sessionRegistry.getSessionInformation(sessionId).expireNow();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("退出登陆结束");
		return baseHttpParamsResp;

	}

}
