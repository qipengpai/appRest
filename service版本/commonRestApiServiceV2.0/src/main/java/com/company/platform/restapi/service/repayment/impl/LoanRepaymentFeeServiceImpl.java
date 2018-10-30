package com.company.platform.restapi.service.repayment.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.platform.restapi.model.repayment.LoanRepayFee;
import com.company.platform.restapi.service.repayment.ILoanRepaymentFeeService;

@Service
public class LoanRepaymentFeeServiceImpl implements ILoanRepaymentFeeService {

	
	@Override
	public List<LoanRepayFee> getRepayFeeList(String customerId) {		
		return null;
	}
	
}
