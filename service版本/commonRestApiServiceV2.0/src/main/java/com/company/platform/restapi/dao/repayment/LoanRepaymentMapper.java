package com.company.platform.restapi.dao.repayment;

import java.util.List;
import java.util.Map;

import com.company.platform.restapi.model.loan.LoanEntryQuery;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.company.platform.restapi.model.repayment.AppLoanRepaymentInfo;
import com.company.platform.restapi.model.repayment.LoanRepayment;

public interface LoanRepaymentMapper {
	
	@Select("select * from loan_repayment where id=#{id}")
	LoanRepayment selectById(String id);
	
	@SelectProvider(type = LoanRepaymentSqlProvider.class, method = "queryRepayments_period")
    public List<AppLoanRepaymentInfo> getRepayInfo(@Param("queryCond") Map<String, String> queryCond);
	
	/*根据产品申请费用id查询该期还款计划*/
    @Select("SELECT * FROM loan_repayment WHERE id IN (SELECT loanRepayId FROM loan_repay_fee WHERE loanProductApplyFeeId = #{loanProductApplyFeeId})")
    LoanRepayment selectLoanRepaymentByApplyFeeId(@Param("loanProductApplyFeeId") String loanProductApplyFeeId);
    
    @Select("select * from loan_repayment where loanProductApplyId=#{applyId} order by periodNum asc")
    List<LoanRepayment> selectByLoanProductApplyId(@Param("applyId") String applyId);

    /**
     * @Author qipengpai
     * @Description //TODO 查询 进件/信审 列表
     * @Date 13:47 2018/9/13
     * @Param [queryCond]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @SelectProvider(type = LoanRepaymentSqlProvider.class, method = "selectQuickQueryLoan")
    Page<Map<String,Object>> selectQuickQueryLoan(@Param("queryCond") Map<String, Object> queryCond);

    /**
     * @Author qipengpai
     * @Description //TODO 进件,信审 数量明细查询
     * @Date 20:28 2018/9/13
     * @Param [userId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @SelectProvider(type = LoanRepaymentSqlProvider.class, method = "getLoanEntryCreditNum")
    Map<String,Object> getLoanEntryCreditNum(@Param("queryCond") Map<String, Object> queryCond);

    /**
     * @Author qipengpai
     * @Description //TODO 获取分数信息
     * @Date 21:13 2018/9/13
     * @Param [loanProductApplyId]
     * @return java.lang.String
     **/
    @Select("select SUM(actualScore) from act_loan_score_log where loanAppid=#{loanProductApplyId}")
    String selecCreditReusltScore(String loanProductApplyId);

    /**
     * @Author qipengpai
     * @Description //TODO 获取当前进件核验状态信息
     * @Date 21:17 2018/9/13
     * @Param [loanProductApplyId]
     * @return java.lang.String
     **/
    @Select("SELECT a.taskStatus FROM act_loan_task_status a, "
            + "( SELECT MAX(id) AS bid FROM act_loan_task_status "
            + "WHERE taskId IN ('taskone', 'tasktwo') AND loanAppid = #{loanProductApplyId} GROUP BY loanAppid ) b "
            + "WHERE a.id = b.bid AND a.taskId IN ('taskone', 'tasktwo') ORDER BY a.id ASC")
    String selectIntoPieceResult(String loanProductApplyId);

    /**
     * @Author qipengpai
     * @Description //TODO 信审状态信息
     * @Date 21:50 2018/9/13
     * @Param [loanProductApplyId]
     * @return java.lang.String
     **/
    @Select("SELECT a.taskStatus FROM act_loan_task_status a, "
            + "( SELECT MAX(id) AS bid FROM act_loan_task_status "
            + "WHERE taskId ='taskthree' AND loanAppid = #{loanProductApplyId} GROUP BY loanAppid ) b "
            + "WHERE a.id = b.bid AND a.taskId ='taskthree' ORDER BY a.id ASC")
    String selectCreditReuslt(String loanProductApplyId);
}
