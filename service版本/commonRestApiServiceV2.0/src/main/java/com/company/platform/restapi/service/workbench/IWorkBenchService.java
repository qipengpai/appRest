package com.company.platform.restapi.service.workbench;

import org.springframework.expression.ParseException;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.workbench.WorkBenchResp;

/**
 * 
 * @ClassName: IWorkBenchService
 * @Description: TODO(获取工作台数量信息)
 * @author liang
 * @date 2018年1月24日 下午7:49:33
 *
 */
public interface IWorkBenchService {

	WorkBenchResp getWorkBenchCounts() throws BusinessException, ParseException;

}
