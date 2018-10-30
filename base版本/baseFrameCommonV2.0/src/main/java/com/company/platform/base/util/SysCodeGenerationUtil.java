package com.company.platform.base.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.company.platform.base.dao.product.SysCodeGenerationMapper;

public class SysCodeGenerationUtil {
	
	private SysCodeGenerationMapper sysCodeGenerationMapper;
	
	private volatile static SysCodeGenerationUtil sysCodeGenerationUtil = null;
	
	private SysCodeGenerationUtil() {
		
	}
	
	public static SysCodeGenerationUtil getInstance() {
		if(sysCodeGenerationUtil == null) {
			synchronized (SysCodeGenerationUtil.class) {
				if(sysCodeGenerationUtil == null) {
					sysCodeGenerationUtil = new SysCodeGenerationUtil();
				}
			}
		}
		return sysCodeGenerationUtil;
	}

	public String getCode(String type, String param, ServletContext servletContext) {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		sysCodeGenerationMapper = ac.getBean(SysCodeGenerationMapper.class);
		StringBuffer code = new StringBuffer();
		List<Map<String, String>> list = sysCodeGenerationMapper.selectByType(type);
		if(list != null && list.size() > 0) {
			String PREFIX = "";
			String CURRENT_CODE = "";
			String VERSION = "";
			String DATE = "";
			for(Map<String, String> map : list) {
				if(StringUtils.equals("PREFIX", map.get("code"))) {
					PREFIX = map.get("name");
				} else if(StringUtils.equals("CURRENT_CODE", map.get("code"))) {
					CURRENT_CODE = map.get("name");
				} else if(StringUtils.equals("VERSION", map.get("code"))) {
					//VERSION = map.get("name");
				} else if(StringUtils.equals("DATE", map.get("code"))) {
					DATE = map.get("name");
				}
			}
			if(StringUtils.isBlank(DATE)) {
				DATE = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
				sysCodeGenerationMapper.save("DATE", DATE, type);
				CURRENT_CODE = "001";
			} else {
				String today = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
				if(!today.equals(DATE)) {
					DATE = today;
					sysCodeGenerationMapper.updateVal(type, "DATE", today);
					CURRENT_CODE = "001";
				}
			}
			code.append(PREFIX).append(StringUtils.defaultString(param)).append(DATE).append(CURRENT_CODE).append(VERSION);
			/*CURRENT_CODE字段数值+1（超过999加1，不足3位数前端补0），更新到数据库*/
			Integer CURRENT_CODE_INT = Integer.parseInt(CURRENT_CODE);
			/*if(++CURRENT_CODE_INT > 999) {
				CURRENT_CODE_INT = 1;
			}*/
			CURRENT_CODE_INT++;
			String CURRENT_CODE_Str = Integer.toString(CURRENT_CODE_INT);
			StringBuffer zeroTemp = new StringBuffer();
			if(CURRENT_CODE_Str.length() <= 3) {
				int len = 3 - CURRENT_CODE_Str.length();
				for(int i = 0; i < len; i++) {
					zeroTemp.append("0");
				}
				zeroTemp.append(CURRENT_CODE_Str);
			} else {
				zeroTemp.append(CURRENT_CODE_Str);
			}
			sysCodeGenerationMapper.updateVal(type, "CURRENT_CODE", zeroTemp.toString());
		}
		return code.toString();
	}
	
	public String getCode(String type, String param, HttpServletRequest request) {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		sysCodeGenerationMapper = ac.getBean(SysCodeGenerationMapper.class);
		StringBuffer code = new StringBuffer();
		List<Map<String, String>> list = sysCodeGenerationMapper.selectByType(type);
		if(list != null && list.size() > 0) {
			String PREFIX = "";
			String CURRENT_CODE = "";
			String VERSION = "";
			String DATE = "";
			for(Map<String, String> map : list) {
				if(StringUtils.equals("PREFIX", map.get("code"))) {
					PREFIX = map.get("name");
				} else if(StringUtils.equals("CURRENT_CODE", map.get("code"))) {
					CURRENT_CODE = map.get("name");
				} else if(StringUtils.equals("VERSION", map.get("code"))) {
					//VERSION = map.get("name");
				} else if(StringUtils.equals("DATE", map.get("code"))) {
					DATE = map.get("name");
				}
			}
			if(StringUtils.isBlank(DATE)) {
				DATE = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
				sysCodeGenerationMapper.save("DATE", DATE, type);
				CURRENT_CODE = "001";
			} else {
				String today = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
				if(!today.equals(DATE)) {
					DATE = today;
					sysCodeGenerationMapper.updateVal(type, "DATE", today);
					CURRENT_CODE = "001";
				}
			}
			code.append(PREFIX).append(StringUtils.defaultString(param)).append(DATE).append(CURRENT_CODE).append(VERSION);
			/*CURRENT_CODE字段数值+1（超过999加1，不足3位数前端补0），更新到数据库*/
			Integer CURRENT_CODE_INT = Integer.parseInt(CURRENT_CODE);
			/*if(++CURRENT_CODE_INT > 999) {
				CURRENT_CODE_INT = 1;
			}*/
			CURRENT_CODE_INT++;
			String CURRENT_CODE_Str = Integer.toString(CURRENT_CODE_INT);
			StringBuffer zeroTemp = new StringBuffer();
			if(CURRENT_CODE_Str.length() <= 3) {
				int len = 3 - CURRENT_CODE_Str.length();
				for(int i = 0; i < len; i++) {
					zeroTemp.append("0");
				}
				zeroTemp.append(CURRENT_CODE_Str);
			} else {
				zeroTemp.append(CURRENT_CODE_Str);
			}
			sysCodeGenerationMapper.updateVal(type, "CURRENT_CODE", zeroTemp.toString());
		}
		return code.toString();
	}

	public boolean checkTypeExist(String type, HttpServletRequest request) {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		sysCodeGenerationMapper = ac.getBean(SysCodeGenerationMapper.class);
		int count = sysCodeGenerationMapper.selectLoanByType(type);
		return count > 0;
	}
}
