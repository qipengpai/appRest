package com.company.platform.restapi.service.repayment;

import java.util.List;

import com.company.platform.restapi.model.repayment.LoanRepayReduction;

public interface ILoanRepayReductionService {

    /**
     * 通过借贷、业务ID、业务类型、减免类型查找减免记录
     * @param loanRepayReduction
     * @return
     */
	LoanRepayReduction selectRepayReduction(LoanRepayReduction loanRepayReduction);
	
    /**
     * 通过借贷、业务类型、减免类型查找减免记录
     * @param loanRepayReduction
     * @return
     */
	LoanRepayReduction selectRepayReduction_part(LoanRepayReduction loanRepayReduction);
	
    /**
     * 通过借贷、减免类型查找减免记录
     * @param loanRepayReduction
     * @return
     */
    LoanRepayReduction queryRepayReduction(LoanRepayReduction loanRepayReduction);
	
	List<LoanRepayReduction> splitTotalReduAmount(LoanRepayReduction loanRepayReduction);
	
    /**
     * 查询还款过的减免信息列表
     * @param loanProductApplyId
     * @param businessId
     * @param businessType
     * @return
     */
	List<LoanRepayReduction> selectRepayReductionList(String loanProductApplyId,String businessId,int businessType);
	
	/**
     * 查询还款过的减免信息列表
     * @return
     */
    List<LoanRepayReduction> selectRepayReduByApplyId(String loanProductApplyId);
    
    /**
     * 查询该期还款过的减免数据
     * @param repayId
     * @return
     */
    List<LoanRepayReduction> selectRepayReduByRepayId(String repayId);
    

    List<LoanRepayReduction> selectReductionListByRedu(LoanRepayReduction loanRepayReduction);
    
    List<LoanRepayReduction> selectReduByRepayId(String loanRepaymentId);
    
    /**
     * 根据还款计划id查询
     * @param bussinessId
     * @return
     */
    List<LoanRepayReduction> selectByBussinessId(String bussinessId);
}
