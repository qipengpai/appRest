package com.company.platform.restapi.dao.repayment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.company.platform.restapi.model.repayment.LoanPositive;
import com.company.platform.restapi.model.repayment.LoanRepayment;
import com.company.platform.restapi.model.repayment.RepayPositive;

public interface LoanPositiveMapper {
	
	@Select("select * from loan_positive where id=#{id}")
	LoanRepayment selectById(String id);
	
	@Insert("insert into loan_positive(id,positiveType,positiveAmount,positiveAmountState) " +
            "values(#{id},#{positiveType},#{positiveAmount},#{positiveAmountState})")
    int saveLoanPositive(LoanPositive loanPositive);
	
	@Insert("insert into repay_positive(id,loanRepaymentId,loanPositiveId,positiveTime,positiveReason,repayType) " +
            "values(#{id},#{loanRepaymentId},#{loanPositiveId},#{positiveTime},#{positiveReason},#{repayType})")
    int saveRepayPositive(RepayPositive repayPositive);
	
	@Select("select lp.* from loan_positive lp,repay_positive rp where rp.loanPositiveId = lp.id and rp.loanRepaymentId=#{loanRepaymentId} order by lp.positiveType")
	List<LoanPositive> queryLoanPosList(String loanRepaymentId);
	
    @SelectProvider(type = LoanPositiveSqlProvider.class, method = "unPositiveExcel")
    List<Map<String, Object>> unPositiveExcel(Map<String, Object> queryCond);
    
    @SelectProvider(type = LoanPositiveSqlProvider.class, method = "positiveExcel")
    List<Map<String, Object>> positiveExcel(Map<String, Object> queryCond);
    
    @Select("select * from loan_positive lp,repay_positive rp where rp.loanPositiveId = lp.id and rp.loanRepaymentId=#{loanRepaymentId} order by rp.positiveTime desc")
    List<Map<String, Object>> selectPositiveList(String loanRepaymentId);
    
    @Select("select * from loan_positive lp,repay_positive rp, loan_repayment lr where rp.loanPositiveId = lp.id and rp.loanRepaymentId=lr.id and lr.loanProductApplyId = #{loanApplyId} order by lr.periodNum, rp.positiveTime desc")
    List<Map<String, Object>> selectPositiveListByApply(String loanApplyId);
    
}
