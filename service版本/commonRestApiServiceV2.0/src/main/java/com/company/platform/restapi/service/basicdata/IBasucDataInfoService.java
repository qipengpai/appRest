package com.company.platform.restapi.service.basicdata;

import java.text.ParseException;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.basicdata.BasicDataInfoReq;
import com.company.platform.restapi.model.basicdata.BasicDataInfoResp;

/**
 * 
 * @ClassName: IBasucDataInfoService
 * @Description: TODO(更新基础数据信息)
 * @author liang
 * @date 2018年1月24日 下午7:49:33
 *
 */
public interface IBasucDataInfoService {

	BasicDataInfoResp updateBasicData(BasicDataInfoReq basicDataInfoReq) throws BusinessException, ParseException;

}
