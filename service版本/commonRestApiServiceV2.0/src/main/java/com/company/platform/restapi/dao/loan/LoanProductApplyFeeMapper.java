package com.company.platform.restapi.dao.loan;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.platform.restapi.model.repayment.LoanProductApplyFee;

public interface LoanProductApplyFeeMapper {

	@Delete("delete from loan_product_apply_fee where loanProductApplyId=#{loanProductApplyId} and applyType=0")
	int deleteByLoanProductApplyId(String loanProductApplyId);
	
	@Delete("delete from loan_product_apply_fee where extensionApplyId=#{extensionApplyId} and applyType=1")
	int deleteByExtensionApplyId(String extensionApplyId);
	
	@InsertProvider(type = LoanProductApplyFeeSqlProvider.class, method = "batchAdd")
	int batchAdd(@Param("list") List<LoanProductApplyFee> list);
	
	@Select("select * from loan_product_apply_fee where loanProductApplyId=#{loanProductApplyId} order by chargeType,periodNum asc")
	List<LoanProductApplyFee> selectByLoanProductApplyId(String loanProductApplyId);
	
	@Select("select * from loan_product_apply_fee where extensionApplyId=#{extensionApplyId} and applyType=1 order by loanProductFeeId,periodNum asc")
	List<LoanProductApplyFee> selectByExtensionApplyId(String extensionApplyId);
	
	@Update("update loan_product_apply_fee set status=#{status} where id=#{id}")
	int updateStatus(@Param("id") String id, @Param("status") int status);
	
	@Update("update credit_product_apply_fee set status=#{status} where id=#{id}")
	int updateStatusCredit(@Param("id") String id, @Param("status") int status);
	
	@Select("select lpaf.* from loan_product_apply_fee lpaf,loan_product_apply lpa where lpaf.loanProductApplyId = lpa.id AND lpa.intoPieceNo like CONCAT('%',#{intoPieceNo},'%') AND lpaf.feeType = #{feeType} ")
    List<LoanProductApplyFee> selectByIntoPieceNo(@Param("intoPieceNo") String intoPieceNo,@Param("feeType") String feeType);
	
	@Select("select id from loan_repayment where loanProductApplyId=#{loanProductApplyId} and periodNum=#{periodNum}")
	String selectRepaymentByPeriodNum(@Param("loanProductApplyId") String loanProductApplyId, @Param("periodNum") int periodNum);
	
    @Select("select repayStatus from loan_repayment where loanProductApplyId=#{loanProductApplyId} and periodNum=#{periodNum}")
    int selectRepayStatus(@Param("loanProductApplyId") String loanProductApplyId, @Param("periodNum") int periodNum);

    @Select("select * from loan_product_apply_fee where loanProductApplyId=#{loanProductApplyId} and periodNum=#{periodNum} and feeType = '3'")
    List<LoanProductApplyFee> selectBreachAmountInfo(@Param("loanProductApplyId") String loanProductApplyId, @Param("periodNum") int periodNum);	
	
    @Select("select * from loan_product_apply_fee where id=#{id}")
    LoanProductApplyFee selectApplyFeeById(String id);
    
    @Select("select sum(realAmount) from loan_repay_fee where loanProductApplyId = #{loanProductApplyId}")
    BigDecimal getRealTotalFee(@Param("loanProductApplyId") String loanProductApplyId);
    
	@Update("update loan_product_apply_fee set actualFeeAmount=#{actualFeeAmount},feeAmount=#{feeAmount} where id=#{id}")
	int updateFeeAmountById(LoanProductApplyFee applyFee);
}
