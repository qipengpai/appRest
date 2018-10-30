package com.company.platform.restapi.dao.repayment;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.company.platform.restapi.model.repayment.LoanRepayReduction;

public class LoanRepayReductionSqlProvider {

	public String batchAdd(Map<String, Object> params) {
		List<LoanRepayReduction> list = (List<LoanRepayReduction>)params.get("list");
		StringBuffer sql = new StringBuffer();
		sql.append("insert into loan_repay_reduction(id,loanProductApplyId,businessId,businessType,loanRepaymentRecordId,reductionType,reductionAmount,")
			.append("reductionAmountState,reductionTime,reductionReason,status,operId,createTime,isUsed,repayFeeId) values");
		MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].loanProductApplyId},#'{'list[{0}].businessId}," +
				"#'{'list[{0}].businessType},#'{'list[{0}].loanRepaymentRecordId},#'{'list[{0}].reductionType},#'{'list[{0}].reductionAmount}," +
				"#'{'list[{0}].reductionAmountState},#'{'list[{0}].reductionTime},#'{'list[{0}].reductionReason},#'{'list[{0}].status}," +
				"#'{'list[{0}].operId},#'{'list[{0}].createTime},#'{'list[{0}].isUsed},#'{'list[{0}].repayFeeId})");
		for(int i = 0, len=list.size(); i<len; i++) {
			sql.append(messageFormat.format(new Object[] { i+"" }));
            if (i < len - 1) {
            	sql.append(",");
            }
		}
		return sql.toString();
	}
}
