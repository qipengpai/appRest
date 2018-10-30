package com.company.platform.restapi.dao.loan;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.platform.restapi.model.repayment.LoanProductApplyFee;
import com.company.platform.restapi.model.repayment.LoanRepayFee;
import com.company.platform.restapi.model.repayment.LoanRepayment;

public interface LoanRepayFeeMapper {
	
	@Select("SELECT rfee.*,afee.name,afee.chargeType,afee.feeType,repay.periodNum FROM loan_repay_fee rfee JOIN loan_product_apply_fee afee " +
			"ON rfee.loanProductApplyId=#{loanProductApplyId} AND rfee.loanProductApplyFeeId=afee.id LEFT JOIN loan_repayment repay " +
			"ON rfee.loanRepayId=repay.id " +
			"ORDER BY afee.feeType, repay.periodNum")
	List<Map<String, Object>> selectFeeInfo(String loanProductApplyId);
	
	@Select("SELECT rfee.*,afee.name,afee.chargeType,afee.feeType,repay.periodNum FROM loan_repay_fee_history rfee JOIN loan_product_apply_fee afee " +
			"ON rfee.loanProductApplyFeeId=afee.id LEFT JOIN loan_repayment_history repay " +
			"ON rfee.loanRepayHistoryId=repay.id WHERE repay.loanRepaymentRecord = #{loanRepaymentRecordId}" +
			"ORDER BY afee.feeType, repay.periodNum")
	List<Map<String, Object>> selectFeeHistoryInfo(String loanRepaymentRecordId);
	
	@Select("SELECT rfee.*,afee.name,afee.chargeType,afee.feeType,repay.periodNum FROM loan_repay_fee rfee JOIN loan_product_apply_fee afee " +
			"ON rfee.loanRepayId=#{loanRepayId} AND rfee.loanProductApplyFeeId=afee.id LEFT JOIN loan_repayment repay " +
			"ON rfee.loanRepayId=repay.id ORDER BY afee.feeType, repay.periodNum")
	List<Map<String, Object>> selectFeeInfoByPlan(String loanRepaymentId);
	
	@Select("SELECT rfee.*, afee.name, afee.chargeType, afee.feeType, repay.periodNum FROM loan_repay_fee rfee JOIN loan_product_apply_fee afee "
			+ "ON rfee.loanProductApplyId = #{loanProductApplyId} AND rfee.loanProductApplyFeeId = afee.id LEFT JOIN loan_repayment repay "
			+ "ON rfee.loanRepayId = repay.id WHERE repay.periodNum = #{minPeriodNum} and afee.feeType != '3' "
			+ "ORDER BY repay.periodNum,afee.loanProductFeeId ASC")
	List<Map<String, Object>> selectFeeInfoCurrent(@Param("loanProductApplyId") String loanProductApplyId, @Param("minPeriodNum") int minPeriodNum);
	
	@Select("SELECT afee.*, cfee.*, repay.id rid "
			+ "FROM loan_product_apply_fee afee "
			+ "JOIN loan_repayment repay "
			+ "ON afee.loanProductApplyId = repay.loanProductApplyId "
			+ "AND afee.periodNum = repay.periodNum "
			+ "LEFT JOIN loan_product_fee_condition cfee "
			+ "ON afee.loanProductFeeId = cfee.feeId "
			+ "AND cfee.loanProductId = #{loanProductId} "
			+ "WHERE afee.loanProductApplyId = #{loanProductApplyId} "
			+ "AND afee.feeType = '3' "
			+ "AND afee.periodNum = #{periodNum} "
			+ "ORDER BY orderNum ASC")
	List<Map<String, Object>> selectFeeDamagesInfo(@Param("loanProductApplyId") String loanProductApplyId, @Param("periodNum") int periodNum, @Param("loanProductId") String loanProductId);
	
	@Select("SELECT * from loan_repayment repay where "
			+ "repay.periodNum = (SELECT MIN(repay.periodNum) FROM loan_repayment repay "
			+ "WHERE repay.loanProductApplyId = #{loanProductApplyId} and repay.repayStatus != '1') and repay.loanProductApplyId = #{loanProductApplyId}")
	LoanRepayment selectMinCurrentNum(String loanProductApplyId);
	
	@Select("SELECT * from loan_repayment repay where "
			+ "repay.periodNum = (SELECT MIN(repay.periodNum) FROM loan_repayment repay "
			+ "WHERE repay.loanProductApplyId = #{loanProductApplyId} and repay.repayStatus != '1') and repay.loanProductApplyId = #{loanProductApplyId} and realPenalty <= 0")
	LoanRepayment selectMinCurrentNumNotPently(String loanProductApplyId);
	
	@Select("SELECT * from loan_repayment repay where "
			+ "repay.periodNum = (SELECT MAX(repay.periodNum) FROM loan_repayment repay "
			+ "WHERE repay.loanProductApplyId = #{loanProductApplyId} and repay.repayStatus != '1') and repay.loanProductApplyId = #{loanProductApplyId}")
	LoanRepayment selectMaxCurrentNum(String loanProductApplyId);
	
	@Select("SELECT rfee.*,afee.name,afee.chargeType,afee.feeType,repay.num FROM credit_repayment_fee rfee JOIN credit_product_apply_fee afee " +
			"ON rfee.loanProductApplyId=#{loanProductApplyId} AND rfee.loanProductApplyFeeId=afee.id LEFT JOIN credit_repayment repay " +
			"ON rfee.loanRepayId=repay.id ORDER BY repay.num,afee.loanProductFeeId ASC")
	List<Map<String, Object>> selectFeeInfoCreditafter(String loanProductApplyId);
	
	@Select("SELECT rfee.*, afee.feeType,afee.calculationBasic,afee.feeRate FROM loan_repay_fee rfee JOIN loan_product_apply_fee afee ON " +
			"rfee.loanProductApplyId=#{loanProductApplyId} AND rfee.isPayment=1 AND rfee.loanProductApplyFeeId=afee.id AND afee.chargeType=#{chargeType}")
	List<Map<String, Object>> selectFeeInfoByChargeType(@Param("loanProductApplyId") String loanProductApplyId, @Param("chargeType") String chargeType);
	
	@Select("SELECT rfee.*,afee.id applyFeeId,afee.name,afee.chargeType,afee.feeType,afee.calculationBasic,afee.feeRate FROM loan_repay_fee rfee JOIN loan_product_apply_fee afee " +
			"ON rfee.loanRepayId=#{repayId} AND rfee.loanProductApplyFeeId=afee.id order by afee.feeType")
	List<Map<String, Object>> selectRepayFee(String repayId);
	
	@Select("select sum(lrf.feeAmount) dueFeeAmount from loan_repay_fee lrf left join loan_product_apply_fee lpaf on lrf.loanProductApplyFeeId = lpaf.id where lrf.loanRepayId = #{repayId} and lrf.isPayment = 1 and lpaf.feeType = 4")
	Map<String, Object> selectDueFee(String repayId);
	
	@Select("select sum(lrf.feeAmount) realFeeAmount from loan_repay_fee lrf left join loan_product_apply_fee lpaf on lrf.loanProductApplyFeeId = lpaf.id where lrf.loanRepayId = #{repayId} and lrf.isPayment = 1")
	Map<String, Object> selectRealFee(String repayId);
	
	@Select("SELECT rfee.*,afee.name,afee.chargeType,afee.feeType,afee.calculationBasic,afee.feeRate FROM credit_repayment_fee rfee JOIN credit_product_apply_fee afee " +
			"ON rfee.loanRepayId=#{repayId} AND rfee.loanProductApplyFeeId=afee.id")
	List<Map<String, Object>> selectRepayFeeRepayDetail(String repayId);
	
	@Select("select * from credit_repayment_record where creaditRepayId = #{repayId}")
	List<Map<String, Object>> selectRepayRecord(@Param("repayId")String repayId);
	
	@Select("SELECT rfee.*,afee.name,afee.chargeType,afee.feeType,afee.calculationBasic,afee.feeRate FROM credit_repayment_fee rfee JOIN credit_product_apply_fee afee " +
			"ON rfee.loanRepayId=#{repayId} AND rfee.loanProductApplyFeeId=afee.id")
	List<Map<String, Object>> selectRepayFeeCreditafter(String repayId);
	
	@Select("select rfee.*,afee.name,afee.chargeType,afee.feeType from loan_repay_fee rfee join loan_product_apply_fee afee on " +
			"rfee.loanProductApplyId=#{loanProductApplyId} and rfee.loanProductApplyFeeId=afee.id and afee.feeType=3 and rfee.isPayment=0")
	List<Map<String, Object>> selectRepayPenaltyFee(String loanProductApplyId);

	@Select("select rfee.*,afee.name,afee.chargeType,afee.feeType from credit_repayment_fee rfee join credit_product_apply_fee afee on " +
			"rfee.loanProductApplyId=#{loanProductApplyId} and rfee.loanProductApplyFeeId=afee.id and afee.feeType=3 and rfee.isPayment=0")
	List<Map<String, Object>> selectRepayPenaltyFeeCreditafter(String loanProductApplyId);
	
	@Select("select rfee.*,afee.name,afee.chargeType,afee.feeType from credit_repayment_fee rfee join credit_product_apply_fee afee on " +
			"rfee.loanProductApplyId=#{loanProductApplyId} and rfee.loanProductApplyFeeId=afee.id and afee.feeType=2")
	List<Map<String, Object>> selectRepayLateFeeCreditafter(String loanProductApplyId);
	
	@InsertProvider(type = LoanRepayFeeSqlProvider.class, method = "batchAdd")
	int batchAdd(@Param("list") List<LoanRepayFee> list);
	
	@Update("update loan_repay_fee set loanRepayId=#{loanRepayId},feeAmount=#{feeAmount}," +
			"isPayment=#{isPayment},loanProductApplyFeeId=#{loanProductApplyFeeId}," +
			"loanProductApplyId=#{loanProductApplyId},realAmount=#{realAmount} where id=#{id}")
	int update(LoanRepayFee loanRepayFee);
	
	@Insert("insert into loan_repay_fee values(#{id}, #{loanRepayId}, #{feeAmount}, #{isPayment}, #{loanProductApplyFeeId}, #{loanProductApplyId}, #{realAmount})")
	int insert(LoanRepayFee loanRepayFee);
	
	@Update("update credit_repayment_fee set loanRepayId=#{loanRepayId},feeAmount=#{feeAmount}," +
			"isPayment=#{isPayment},loanProductApplyFeeId=#{loanProductApplyFeeId}," +
			"loanProductApplyId=#{loanProductApplyId} where id=#{id}")
	int updateCreditafter(@Param("loanRepayId") String loanRepayId,@Param("feeAmount") BigDecimal feeAmount,@Param("isPayment") int isPayment,@Param("loanProductApplyFeeId") String loanProductApplyFeeId,@Param("loanProductApplyId") String loanProductApplyId,@Param("id") String id);
	
	@Update("update loan_repay_fee set isPayment=#{isPayment} where id=#{id}")
	int updateIsPayment(@Param("id") String id, @Param("isPayment") int isPayment);
	
	@Update("update credit_repayment_fee set isPayment=#{isPayment} where id=#{id}")
	int updateIsPaymentCredit(@Param("id") String id, @Param("isPayment") int isPayment);
	
	@Update("update loan_repay_fee set isPayment=#{isPayment} where loanRepayId=#{loanRepayId}")
	int updateIsPayments(@Param("loanRepayId") String loanRepayId, @Param("isPayment") int isPayment);
	
	@Select("select * from loan_repay_fee where id=#{id}")
	LoanRepayFee selectById(String id);
	
	@Select("select * from credit_repayment_fee where id=#{id}")
	Map<String, Object> selectByIdCreditafter(String id);
	
	@Select("select f2.* from loan_product_apply_fee f1 join loan_repay_fee f2 on f2.loanProductApplyId=#{loanProductApplyId} and " +
			"f1.id=f2.loanProductApplyFeeId and f1.chargeType='3' and f2.isPayment=0")
	LoanRepayFee selectFirstRepayDayFee(String loanProductApplyId);
	
	@Select("select f2.* from loan_product_apply_fee f1 join loan_repay_fee f2 on f2.loanProductApplyId=#{loanProductApplyId} and " +
			"f1.id=f2.loanProductApplyFeeId and f1.chargeType='5' and f2.isPayment=0")
	LoanRepayFee selectBreakContractFee(String loanProductApplyId);

	@Select("SELECT rfee.*,afee.name,afee.chargeType,afee.feeType,repay.periodNum FROM loan_repay_fee rfee JOIN loan_product_apply_fee afee " +
			"ON afee.extensionApplyId=#{extensionApplyId} AND afee.applyType=1 AND rfee.loanProductApplyFeeId=afee.id LEFT JOIN " +
			"loan_repayment repay ON rfee.loanRepayId=repay.id ORDER BY afee.loanProductFeeId,repay.periodNum ASC")
	List<Map<String, Object>> selectFeeByExtensionApplyId(String extensionApplyId);
	
	@Select("select * from loan_repay_fee WHERE loanProductApplyFeeId IN (SELECT id FROM loan_product_apply_fee WHERE loanProductApplyId = (SELECT lpa.id FROM loan_product_apply lpa WHERE lpa.intoPieceNo LIKE CONCAT('%',#{intoPieceNo},'%')) AND feeType = #{feeType});")
	List<LoanRepayFee> selectRepayFeeHistory(@Param("intoPieceNo") String intoPieceNo,@Param("feeType") String feeType);
	
	@Select("select lrf.loanRepayId,lpaf.feeType from loan_repay_fee lrf,loan_product_apply_fee lpaf,loan_repayment lr where lrf.loanRepayId = lr.id and lrf.loanProductApplyFeeId = lpaf.id and lrf.id=#{loanRepayFeeId}")
	Map<String, Object> selectRepaymentIdAndFeeType(@Param("loanRepayFeeId") String loanRepayFeeId);

	@InsertProvider(type = LoanRepayFeeSqlProvider.class, method = "batchAddHistory")
    int batchAddHistory(@Param("list") List<LoanRepayFee> list,@Param("loanRepaymentRecord") String loanRepaymentRecord);
	
	@Select("select * from loan_repay_fee where loanProductApplyId = #{loanProductApplyId}")
	List<LoanRepayFee> selectFeeListByApplyId(@Param("loanProductApplyId") String loanProductApplyId);
	
	@Delete("delete from loan_repay_fee where loanRepayId=#{loanRepayId}")
	int deleteFeeByRepayId(@Param("loanRepayId") String loanRepayId);
	
	@Select("SELECT * FROM loan_product_apply_fee lpaf "
			+ "JOIN loan_product_fee_condition lpfc ON lpaf.loanProductFeeId = lpfc.feeId "
			+ "WHERE lpaf.loanProductApplyId = #{id} "
			+ "AND lpaf.feeType = '3' "
			+ "AND lpaf.periodNum = #{minPeriodNum} "
			+ "AND lpfc.loanProductId=#{loanProductId} "
			+ "ORDER BY lpfc.orderNum ASC ")
	List<Map<String, Object>> selectInfoById(@Param("id") String id , @Param("minPeriodNum") int minPeriodNum, @Param("loanProductId") String loanProductId);

	@Select("select * from loan_product_apply_fee where loanProductApplyId = #{loanProductApplyId} and feeType='3' and periodNum = #{periodNum} and feeConditionId = #{feeConditionId}")
	List<Map<String, Object>> selectFeeDamagesInfoMore(@Param("loanProductApplyId") String loanProductApplyId, @Param("periodNum") int periodNum, @Param("feeConditionId") String feeConditionId);
	
	@Select("select * from loan_product_apply_fee where id = #{loanProductApplyFeeId}")
	List<Map<String, Object>> selectInfoByFeeIdAndApId(@Param("loanProductApplyFeeId") String loanProductApplyFeeId);

	@Select("select * from loan_product_apply_fee where loanProductApplyId = #{loanProductApplyId} and periodNum = #{periodNum} and feeType = 3 ")
	List<LoanProductApplyFee> selectProductApplyFee(@Param("loanProductApplyId") String loanProductApplyId, @Param("periodNum") int periodNum);

	@Update("update loan_repay_fee set realAmount=#{realAmount} where id=#{id}")
	int updateRealAmount(@Param("id") String id, @Param("realAmount") String realAmount);
	
	@Select("select sum(feeAmount) from loan_repay_fee where loanRepayId=#{loanRepayId} and isPayment = 0 ")
	BigDecimal queryDueTotalFeeAmount(@Param("loanRepayId") String loanRepayId);
	
	@Select("select * from loan_repay_fee where loanRepayId=#{loanRepayId}")
	List<LoanRepayFee> selectLoanRepayFeeByRepayId(@Param("loanRepayId") String loanRepayId);
	
	@Select("SELECT feeType FROM loan_product_apply_fee WHERE id IN (select loanProductApplyFeeId from loan_repay_fee WHERE id = #{id})")
	String selectFeeTypeByRepayFeeId(String id);
}
