package com.company.platform.restapi.dao.loan;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.company.platform.restapi.model.repayment.LoanRepayFee;

public class LoanRepayFeeSqlProvider {
	
	public String batchAdd(Map<String, Object> params) {
		List<LoanRepayFee> list = (List<LoanRepayFee>)params.get("list");
		StringBuffer sql = new StringBuffer();
		sql.append("insert into loan_repay_fee(id,loanRepayId,feeAmount,realAmount,isPayment,loanProductApplyFeeId,loanProductApplyId) values");
		MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].loanRepayId},#'{'list[{0}].feeAmount},#'{'list[{0}].realAmount}," +
				"#'{'list[{0}].isPayment},#'{'list[{0}].loanProductApplyFeeId},#'{'list[{0}].loanProductApplyId})");
		for(int i = 0, len=list.size(); i<len; i++) {
			sql.append(messageFormat.format(new Object[] { i+"" }));
            if (i < len - 1) {
            	sql.append(",");
            }
		}
		return sql.toString();
	}
	
	public String batchAddHistory(Map<String, Object> params) {
        List<LoanRepayFee> list = (List<LoanRepayFee>)params.get("list");
        String loanRepaymentRecord = (String)params.get("loanRepaymentRecord");
        Date creatTime = new Date();
        StringBuffer sql = new StringBuffer();
        sql.append("insert into loan_repay_fee_history(id,loanRepaymentRecord,loanRepayHistoryId,feeAmount,isPayment,loanProductApplyFeeId,loanProductApplyId) values");
        MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].id},#'{'loanRepaymentRecord},#'{'list[{0}].loanRepayId},#'{'list[{0}].feeAmount}," +
                "#'{'list[{0}].isPayment},#'{'list[{0}].loanProductApplyFeeId},#'{'list[{0}].loanProductApplyId})");
        for(int i = 0, len=list.size(); i<len; i++) {
            sql.append(messageFormat.format(new Object[] { i+"" }));
            if (i < len - 1) {
                sql.append(",");
            }
        }
        return sql.toString();
	}
}
