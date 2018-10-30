package com.company.platform.restapi.service.repayment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.company.platform.base.model.base.BaseHttpParamsPageAppReq;
import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.base.model.base.PageInfo;
import com.company.platform.base.model.base.RespInfo;
import com.company.platform.restapi.model.loan.LoanEntryQuery;
import com.company.platform.restapi.model.repayment.GetRepayInfoListReq;
import com.company.platform.restapi.model.repayment.LoanRepayment;
import com.github.pagehelper.Page;

public interface ILoanRepaymentService {

	BaseHttpParamsPageResp queryRepaymentInfo(GetRepayInfoListReq getRepayInfoListReq);

	/**
	 * 返回某一期的汇总金额
	 * @param applyId
	 * @return
	 */
	Map<String, BigDecimal> getAmountInfo_repayment(String repaymentId);

	/**
	 * 判断是否逾期
	 * @param loanProductApplyFeeId
	 * @return
	 */
	boolean isOverRepayMent(String loanProductApplyFeeId);

	/** 
	* @Title: selectByLoanProductApplyId 
	* @Description: TODO(根据借贷申请id查询还款计划) 
	* @param @param applyId
	* @param @return    设定文件 
	* @return List<LoanRepayment>    返回类型 
	* @throws 
	*/
	List<LoanRepayment> selectByLoanProductApplyId(String applyId);

	/**
	 * @Author qipengpai
	 * @Description //TODO 查询进件列表
	 * @Date 17:05 2018/9/13
	 * @Param [pageInfo, loanEntryQuery]
	 * @return com.github.pagehelper.Page<java.util.Map<java.lang.String,java.lang.Object>>
	 **/
	Page<Map<String,Object>> selectQuickQueryLoan(PageInfo pageInfo, LoanEntryQuery loanEntryQuery);

	/**
	 * @Author qipengpai
	 * @Description //TODO 进件,信审 数量明细查询
	 * @Date 20:33 2018/9/13
	 * @Param [userId]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	Map<String,Object> getLoanEntryCreditNum(String userId);

	/**
	 * @Author qipengpai
	 * @Description //TODO 获取分数信息
	 * @Date 21:11 2018/9/13
	 * @Param [loanProductApplyId]
	 * @return java.lang.String
	 **/
    String selecCreditReusltScore(String loanProductApplyId);

    /**
     * @Author qipengpai
     * @Description //TODO 获取当前进件核验状态信息
     * @Date 21:16 2018/9/13
     * @Param [loanProductApplyId]
     * @return java.lang.String
     **/
	String selectIntoPieceResult(String loanProductApplyId);

	/**
	 * @Author qipengpai
	 * @Description //TODO 获取当前进件授信结果信
	 * @Date 21:28 2018/9/13
	 * @Param [loanProductApplyId]
	 * @return java.lang.String
	 **/
	String selectCreditReuslt(String loanProductApplyId);

	/**
	 * @Author qipengpai
	 * @Description //TODO 获取当前进件授信结果信
	 * @Date 21:28 2018/9/13
	 * @Param [loanProductApplyId]
	 * @return java.lang.String
	 **/
	Map<String,Object> selectProInfo(String loanProductApplyId);
}
