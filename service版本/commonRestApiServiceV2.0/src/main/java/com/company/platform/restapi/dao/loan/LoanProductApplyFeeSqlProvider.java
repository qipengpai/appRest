package com.company.platform.restapi.dao.loan;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.company.platform.restapi.model.repayment.LoanProductApplyFee;

public class LoanProductApplyFeeSqlProvider {

	public String batchAdd(Map<String, Object> params) {
		List<LoanProductApplyFee> list = (List<LoanProductApplyFee>)params.get("list");
		StringBuffer sql = new StringBuffer();
		sql.append("insert into loan_product_apply_fee(id,name,feeType,chargeType,feeAmount,calculationBasic,feeRate,feeReduction,")
			.append("actualFeeAmount,status,loanProductFeeId,feeConditionId,loanProductApplyId,periodNum,extensionApplyId,applyType,feeReductionState) values");
		MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].name},#'{'list[{0}].feeType}," +
				"#'{'list[{0}].chargeType},#'{'list[{0}].feeAmount},#'{'list[{0}].calculationBasic},#'{'list[{0}].feeRate}," +
				"#'{'list[{0}].feeReduction},#'{'list[{0}].actualFeeAmount},#'{'list[{0}].status},#'{'list[{0}].loanProductFeeId}," +
				"#'{'list[{0}].feeConditionId},#'{'list[{0}].loanProductApplyId},#'{'list[{0}].periodNum},#'{'list[{0}].extensionApplyId}," +
				"#'{'list[{0}].applyType},#'{'list[{0}].feeReductionState})");
		for(int i = 0, len=list.size(); i<len; i++) {
			sql.append(messageFormat.format(new Object[] { i+"" }));
            if (i < len - 1) {
            	sql.append(",");
            }
		}
		return sql.toString();
	}
}
