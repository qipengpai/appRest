package com.company.platform.restapi.dao.repayment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.platform.restapi.model.repayment.LoanRepayReduction;

public interface LoanRepayReductionMapper {
	

	@Select("select * from loan_repay_reduction")
	List<LoanRepayReduction> selectByReductionId(String reductionId);
	
	@InsertProvider(type = LoanRepayReductionSqlProvider.class, method = "batchAdd")
	int batchAdd(@Param("list") List<LoanRepayReduction> list);

	@Update("update loan_repay_reduction set loanRepaymentRecordId=#{loanRepaymentRecordId},reductionAmount=#{reductionAmount},createTime=#{createTime},reductionTime=#{reductionTime},isUsed=#{isUsed},repayFeeId=#{repayFeeId} where id=#{id}")
	int updateReduction(LoanRepayReduction loanRepayReduction);

	@Insert("insert into loan_repay_reduction (id,loanProductApplyId,businessId,businessType,loanRepaymentRecordId," +
            "reductionType,reductionAmount,reductionAmountState,reductionTime,reductionReason,status,operId,createTime,isUsed,repayFeeId)" +
            "values(#{id},#{loanProductApplyId},#{businessId},#{businessType},#{loanRepaymentRecordId}," +
			"#{reductionType},#{reductionAmount},#{reductionAmountState},#{reductionTime},#{reductionReason},#{status},#{operId},#{createTime},#{isUsed},#{repayFeeId})")
	int saveRepayReduction(LoanRepayReduction loanRepayReduction);
	
	@Select("select * from loan_repay_reduction where loanProductApplyId=#{loanProductApplyId} and businessId=#{businessId} " +
			"and businessType=#{businessType} and reductionType=#{reductionType} and isUsed=#{isUsed}")
	LoanRepayReduction selectReductionInfo(LoanRepayReduction loanRepayReduction);
	
	@Select("select * from loan_repay_reduction where loanProductApplyId=#{loanProductApplyId} " +
			" and reductionType=#{reductionType} and isUsed=#{isUsed}")
	List<LoanRepayReduction> selectReductionInfo_part(LoanRepayReduction loanRepayReduction);
	
	@Select("select * from loan_repay_reduction where loanProductApplyId=#{loanProductApplyId} " +
            " and reductionType=#{reductionType} and isUsed=#{isUsed}")
    LoanRepayReduction selectReduction(LoanRepayReduction loanRepayReduction);
	
	@Delete("delete from loan_repay_reduction where id=#{id}")
	int deleteReductionById(String id);
	
	@Select("select * from loan_repay_reduction where loanProductApplyId=#{loanProductApplyId} and businessId=#{businessId} " +
			"and businessType=#{businessType} and isUsed=1 and loanRepaymentRecordId !='' ")
	List<LoanRepayReduction> selectRepayReductionList(@Param("loanProductApplyId") String loanProductApplyId,@Param("businessId") String businessId,@Param("businessType") int businessType);

	@Select("select * from loan_repay_reduction where loanProductApplyId=#{loanProductApplyId} and businessId=#{businessId} " +
			" and isUsed=1 and loanRepaymentRecordId !='' ")
	List<LoanRepayReduction> selectRepayReduListByRepayId(@Param("loanProductApplyId") String loanProductApplyId,@Param("businessId") String businessId);

	
	@Select("select * from loan_repay_reduction where loanProductApplyId=#{loanProductApplyId}" +
            " and isUsed=1 and loanRepaymentRecordId !='' ")
    List<LoanRepayReduction> selectRepayReduByApplyId(@Param("loanProductApplyId") String loanProductApplyId);
	
	@Select("select * from loan_repay_reduction where businessId=#{businessId}" +
            " and isUsed=1 and loanRepaymentRecordId !='' ")
    List<LoanRepayReduction> selectRepayReduByRepayId(@Param("businessId") String repayId);

    @Select("select * from loan_repay_reduction where loanProductApplyId=#{loanProductApplyId} " +
            "and businessType=#{businessType} and isUsed=#{isUsed}")
    List<LoanRepayReduction> selectReductionList(LoanRepayReduction loanRepayReduction);
	
	@Select("select * from loan_repay_reduction where businessId=#{loanRepaymentId}" +
            " and isUsed=1 and loanRepaymentRecordId !='' order by reductionType ")
    List<Map<String, Object>> selectRepayReduByPlanId(@Param("loanRepaymentId") String loanRepaymentId);
	
	@Select("select * from loan_repay_reduction where businessId=#{loanRepaymentId}" +
            " and isUsed=1 and loanRepaymentRecordId !='' order by reductionType ")
	List<LoanRepayReduction> selectReduByRepayId(@Param("loanRepaymentId") String loanRepaymentId);
	
    @Select("select * from loan_repay_reduction where loanProductApplyId=#{loanProductApplyId} " +
            " and isUsed=#{isUsed} and businessType in (3,4)")
    List<LoanRepayReduction> selectReduByApplyId(LoanRepayReduction loanRepayReduction);

    @Select("select * from loan_repay_reduction where businessId=#{businessId} and isUsed=0")
    List<LoanRepayReduction> selectReductionInfoList(@Param("businessId") String businessId);
    
    @Select("select * from loan_repay_reduction where businessId=#{businessId}")
    List<LoanRepayReduction> selectByBussinessId(@Param("businessId") String businessId);
}
