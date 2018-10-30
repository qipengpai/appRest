/******************************************************************
 *
 *    Package:     com.company.platform.exception
 *
 *    Filename:    GlobalExceptionHandler.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2017
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     zhengjn
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月27日 下午5:33:58
 *
 *    Revision:
 *
 *    2017年3月27日 下午5:33:58
 *        - first revision
 *
 *****************************************************************/
package com.company.platform.base.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.baseenum.GLOBALCONFIG;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.baseenum.ResponseConstantsErrorMessage;
import com.company.platform.base.model.base.BaseHttpParamsResp;
import com.company.platform.base.util.GzippedOutputStreamWrapper;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: TODO(全局异常处理)
 * @author zhengjn
 * @date 2017年9月24日 下午12:42:29
 */
@ControllerAdvice
@SuppressWarnings("all")
public class GlobalControllerExceptionHandler {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	/** 
	* @Title: errorHandler 
	* @Description: TODO(系统异常处理) 
	* @param @param e
	* @param @return    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@ExceptionHandler({ Exception.class, Throwable.class })
	public void errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		String requestUrl = request.getRequestURI();// 获取请求路径
		BaseHttpParamsResp baseHttpParamsResp = BaseHttpParamsResp.requestError(ResponseConstants.FAIL.getRetCode(),
				ResponseConstantsErrorMessage.getErrorMessage(requestUrl));
		baseHttpParamsResp.createSign();
		logger.error(baseHttpParamsResp.toJSONString());
		logger.error(e.getMessage());
		e.printStackTrace();
		this.returnMessage(request, response, baseHttpParamsResp);
	}

	/** 
	* @Title: validateErrorHandler 
	* @Description: TODO(数据准入常规校验异常，如：长度、邮件规则等) 
	* @param @param e
	* @param @return    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseBody
	public void validateErrorHandler(HttpServletRequest request, HttpServletResponse response,
			MethodArgumentNotValidException e) {
		BaseHttpParamsResp baseHttpParamsResp = null;
		BindingResult bindingResult = e.getBindingResult();
		if (bindingResult.hasErrors()) {
			List<FieldError> errorList = bindingResult.getFieldErrors();
			StringBuffer errorMsg = new StringBuffer();
			for (FieldError fieldError : errorList) {
				String str = fieldError.getField();
				if (str.indexOf("[") > 0) {
					int indexStart = str.indexOf("[");
					int indexEnd = str.indexOf("]");
					String num = str.substring(indexStart + 1, indexEnd);
					String objStr = str.substring(0, indexStart);
					if ("customerRelationshipInfo".equals(objStr) || "corporationCustomerContactinfo".equals(objStr)) {
						objStr = "联系人信息";
					} else if ("corporationCustomerShareholderInfo".equals(objStr)) {
						objStr = "股东信息";
					} else {
						objStr = "";
					}
					if (!"".equals(objStr)) {
						objStr = objStr + "第" + (new Integer(num) + 1) + "条信息中";
					}
					errorMsg.append(objStr + fieldError.getDefaultMessage());
				} else {
					errorMsg.append(fieldError.getDefaultMessage());
				}
			}
			baseHttpParamsResp = BaseHttpParamsResp.requestError(ResponseConstants.FIELD_VALIDATE_ERROR.getRetCode(),
					errorMsg.toString());
		}
		if (null == baseHttpParamsResp) {
			baseHttpParamsResp = BaseHttpParamsResp.requestError(ResponseConstants.FIELD_VALIDATE_ERROR.getRetCode(),
					"");
		}
		if (baseHttpParamsResp != null) {
			baseHttpParamsResp.createSign();
		}
		this.returnMessage(request, response, baseHttpParamsResp);
	}

	/** 
	* @Title: CustomErrorHandler 
	* @Description: TODO(系统自定义异常处理) 
	* @param @param e
	* @param @return    设定文件 
	* @return BaseHttpParamsResp    返回类型 
	* @throws 
	*/
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public void CustomErrorHandler(HttpServletRequest request, HttpServletResponse response, BusinessException e) {
		BaseHttpParamsResp baseHttpParamsResp = BaseHttpParamsResp.requestError(e.getCode(), e.getMsg());
		baseHttpParamsResp.createSign();
		logger.error(baseHttpParamsResp.toJSONString());
		this.returnMessage(request, response, baseHttpParamsResp);
	}

	/** 
	* @Title: returnMessage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void returnMessage(HttpServletRequest request, HttpServletResponse response,
			BaseHttpParamsResp baseHttpParamsResp) {
		response.setCharacterEncoding("UTF-8");
		String requestUrl = request.getRequestURI();// 获取请求路径
		try {
			if (requestUrl.contains(GLOBALCONFIG.GZIP_URI1) || requestUrl.contains(GLOBALCONFIG.GZIP_URI2)
					|| requestUrl.contains(GLOBALCONFIG.GZIP_URI3)) {// 若指定路径，进行gzip压缩响应
				response = ((GzippedOutputStreamWrapper) response).getHttpServletResponse();
				GzippedOutputStreamWrapper.sendMessage(response, JSONObject.toJSON(baseHttpParamsResp).toString());
			} else {
				String contentType = "application/json;charset=UTF-8";
				response.setContentType(contentType);
				PrintWriter out = response.getWriter();
				out.print(JSONObject.toJSON(baseHttpParamsResp).toString());
				out.flush();
				out.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error(e1.getMessage());
		}
	}
}
