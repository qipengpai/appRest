package com.company.platform.restapi.service.workbench.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.dao.activiti.ActivitiMapper;
import com.company.platform.restapi.dao.workbench.WorkBenchMapper;
import com.company.platform.restapi.model.workbench.WorkBenchResp;
import com.company.platform.restapi.service.activiti.ActivitiService;
import com.company.platform.restapi.service.workbench.IWorkBenchService;
import com.company.platform.security.model.SecurityUser;

/**
 * 
 * @ClassName: WorkBenchServiceImp
 * @Description: TODO(获取工作台数量信息实现类)
 * @author liang
 * @date 2018年1月24日 下午7:49:14
 *
 */
@Service
public class WorkBenchServiceImpl implements IWorkBenchService {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(WorkBenchServiceImpl.class);

	@Autowired
	WorkBenchMapper workBenchMapper;

	@Autowired
	private ActivitiService activitiService;

	@Autowired
	private ActivitiMapper activitiMapper;

	@Override
	public WorkBenchResp getWorkBenchCounts() throws BusinessException, ParseException {
		logger.info("工作台统计信息");
		// 通过缓存获取userId和userName
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String registerId = user.getId();
		// 借贷申请暂存任务数量
		String temporaryCount = String.valueOf(workBenchMapper.getTemporaryCount(registerId));
		// 借贷申请待领取进件数量(第二阶段开发调用)暂时设置为0
		Map<String, Object> leisureMap = activitiService.getPerLeisureTask(user.getId(), user.getRealName(), 0, null);
		String unclaimedCount = leisureMap.get("count").toString();
		registerId = user.getId() + ":" + user.getRealName();
		// 借贷申请待办任务数量(第二阶段开发调用)暂时设置为0
		String todealCount = String.valueOf(activitiMapper.getPersonalTaskCount(registerId));
		// 借贷申请已办进件数量
		String workedCount = String.valueOf(workBenchMapper.getWorkedCount(registerId));
		WorkBenchResp workBenchResp = new WorkBenchResp();
		workBenchResp.setTemporaryCount(temporaryCount);
		workBenchResp.setUnclaimedCount(unclaimedCount);
		workBenchResp.setTodealCount(todealCount);
		workBenchResp.setWorkedCount(workedCount);
		return workBenchResp;
	}

}