package com.company.platform.restapi.service.loan.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.restapi.dao.loan.LoanProductApplyModelMapper;
import com.company.platform.restapi.model.loan.LoanProductApplyModelV;
import com.company.platform.restapi.service.loan.ILoanProductApplyModelService;

/** 
* @ClassName: LoanProductApplyModelServiceImpl 
* @Description: TODO(业务与模型数据关联service实现) 
* @author luyuchi
* @date 2018年1月30日 下午2:03:23 
*  
*/
@Service
public class LoanProductApplyModelServiceImpl implements ILoanProductApplyModelService {
	
	@Autowired
	private LoanProductApplyModelMapper loanProductApplyModelMapper;

	@Override
	public int saveLoanProductApplyModelV(LoanProductApplyModelV loanProductApplyModelV) {
		return loanProductApplyModelMapper.saveLoanProductApplyModelV(loanProductApplyModelV);
	}

}
