package com.zkbr.platform.controller.activiti.autoTask;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

/**
 * 合同签订，自动流程任务
 * */
public class ContractSigned implements JavaDelegate {
	
	Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariable("audit", "pass");
	}

}
