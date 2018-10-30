/******************************************************************
 *
 *    Package:     com.company.platform.service.requestAccess.imp
 *
 *    Filename:    RequestAccessServiceImp.java
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
package com.company.platform.base.service.requestAccess.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.platform.base.dao.requestAccess.RequestAccessMapper;
import com.company.platform.base.service.requestAccess.IRequestAccessService;

/** 
* @ClassName: RequestAccessServiceImp 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年9月25日 下午1:44:26 
*  
*/
@Service
public class RequestAccessServiceImp implements IRequestAccessService {

	@Autowired
	RequestAccessMapper requestAccessMapper;
	/* (非 Javadoc) 
	* <p>Title: addRequestAccessInfo</p> 
	* <p>Description: </p> 
	* @param info 
	* @see com.company.platform.service.requestAccess.IRequestAccessService#addRequestAccessInfo(java.util.Map) 
	*/
	@Override
	@Transactional
	@Async
	public void addRequestAccessInfo(Map<String, Object> info) {
		// TODO Auto-generated method stub
		requestAccessMapper.addRequestAccessInfo(info);
	}

}
