package com.company.platform.restapi.service.repayment;

import java.util.List;

import com.company.platform.restapi.model.repayment.LoanRepayFee;

public interface ILoanRepaymentFeeService {
	
	List<LoanRepayFee> getRepayFeeList(String customerId);
	
}
