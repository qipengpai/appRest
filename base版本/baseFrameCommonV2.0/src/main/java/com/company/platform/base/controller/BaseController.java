/******************************************************************
 *
 *    Package:     com.company.platform.controller.demo
 *
 *    Filename:    BaseController.java
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
package com.company.platform.base.controller;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.company.platform.base.util.DesSecret;
import com.company.platform.base.util.cipher.Ancrypt;
import com.company.platform.base.util.cipher.Decrypt;
import com.company.platform.base.util.cipher.ISecret;
import com.company.platform.base.util.cipher.SecretContext;
import com.company.platform.security.model.SecurityUser;
import com.company.platform.security.service.WeixinCustomUserDetailsService;

/**
 * @ClassName: BaseController
 * @Description: TODO(Controller基类，基础继承)
 * @author zhengjn
 * @date 2017年9月19日 下午1:37:13
 * 
 */
public class BaseController {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(BaseController.class);

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	@Autowired
	WeixinCustomUserDetailsService weixinCustomUserDetailsService;

	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isInfoEnabled()) {
			logger.info("开始加载 request response  session 对象");
		}
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		if (logger.isInfoEnabled()) {
			logger.info("结束加载 request response  session 对象");
		}
	}

	/**
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException  
	* @Title: ancrypt 
	* @Description: TODO(加密) 
	* @param @param bean
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	public Object ancrypt(Object bean) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = bean.getClass().getDeclaredFields();//获取所有注解字段
		for (Field field : fields) {
			if (field.isAnnotationPresent(Ancrypt.class)) {
				Ancrypt ancrypt = (Ancrypt) field.getAnnotation(Ancrypt.class);
				String type = ancrypt.type();//取得加密算法
				field.setAccessible(true); //设置些属性是可以访问的  
				Object val = field.get(bean);//得到此属性的值 
				if (StringUtils.isEmpty(type) || "DES".equalsIgnoreCase(type)) {
					if (field.getType().getName().equals("java.lang.String")) {
						ISecret sSecret = new DesSecret();
						SecretContext context = new SecretContext();
						context.setKeyMessage(val.toString());
						field.set(bean, sSecret.ancrypt(context));//加密赋值
					} else if (field.getType().isAssignableFrom(List.class)) {

					} else if (field.getType().getSuperclass().getName()
							.equals("com.company.platform.base.model.base.BaseModel")) {//对象内包含BaseModel类型bean
						Object _nval = field.get(bean);
						Field[] _fields = _nval.getClass().getDeclaredFields();
						for (Field _field : _fields) {
							if (_field.isAnnotationPresent(Ancrypt.class)) {
								Ancrypt _ancrypt = (Ancrypt) _field.getAnnotation(Ancrypt.class);
								String _type = _ancrypt.type();//取得加密算法
								_field.setAccessible(true); //设置些属性是可以访问的  
								Object _val = _field.get(_nval);//得到此属性的值 
								if (StringUtils.isEmpty(_type) || "DES".equalsIgnoreCase(_type)) {
									if (_field.getType().getName().equals("java.lang.String")) {
										ISecret _sSecret = new DesSecret();
										SecretContext _context = new SecretContext();
										_context.setKeyMessage(_val.toString());
										_field.set(_nval, _sSecret.ancrypt(_context));//加密赋值
									}
								}
							}
						}
					}
				}
			}
		}
		return bean;

	}

	/**
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException  
	* @Title: decrypt 
	* @Description: TODO(解密) 
	* @param @param bean
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	public Object decrypt(Object bean) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = bean.getClass().getDeclaredFields();//获取所有注解字段
		for (Field field : fields) {
			if (field.isAnnotationPresent(Decrypt.class)) {
				Decrypt decrypt = (Decrypt) field.getAnnotation(Decrypt.class);
				String type = decrypt.type();//取得加密算法	
				field.setAccessible(true); //设置些属性是可以访问的  
				Object val = field.get(bean);//得到此属性的值 
				if (StringUtils.isEmpty(type) || "DES".equalsIgnoreCase(type)) {
					ISecret sSecret = new DesSecret();
					SecretContext context = new SecretContext();
					context.setKeyMessage(val.toString());
					field.set(bean, sSecret.decrypt(context));//加密赋值
				} else if (field.getType().isAssignableFrom(List.class)) {

				} else if (field.getType().getSuperclass().getName()
						.equals("com.company.platform.base.model.base.BaseModel")) {//对象内包含BaseModel类型bean
					Object _nval = field.get(bean);
					Field[] _fields = _nval.getClass().getDeclaredFields();
					for (Field _field : _fields) {
						if (_field.isAnnotationPresent(Decrypt.class)) {
							Decrypt _decrypt = (Decrypt) _field.getAnnotation(Decrypt.class);
							String _type = _decrypt.type();//取得加密算法
							_field.setAccessible(true); //设置些属性是可以访问的  
							Object _val = _field.get(_nval);//得到此属性的值 
							if (StringUtils.isEmpty(_type) || "DES".equalsIgnoreCase(_type)) {
								if (_field.getType().getName().equals("java.lang.String")) {
									ISecret _sSecret = new DesSecret();
									SecretContext _context = new SecretContext();
									_context.setKeyMessage(_val.toString());
									_field.set(_nval, _sSecret.decrypt(_context));//加密赋值
								}
							}
						}
					}
				}
			}
		}
		return bean;
	}

	public static void main(String s[]) throws IllegalArgumentException, IllegalAccessException {
//		BaseController baseController = new BaseController();
//		TestBean bean = new TestBean();
//		TestBean bean1 = new TestBean();
//		bean.setName("1");
//		bean.setPassword("2");
//		bean.setValue("3");
//		bean.setBean(bean);
//		ArrayList<TestBean> list = new ArrayList<TestBean>();
//		list.add(bean);
//		list.add(bean1);
//		System.out.println(baseController.ancrypt(bean));
//		System.out.println(baseController.decrypt(bean));
	}
}
