package com.company.platform.restapi.dao.loan;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.company.platform.restapi.model.loan.LoanProductApply;

public interface LoanProductApplyMapper {

	@Insert("insert into loan_product_apply(id,loanProductId,customerId,code,applyAmount,termCount,termUnit," +
			"repayTermCount,repayTermUnit,interestRate,penaltyRate,penaltyCalculationBasic,guaranteeType,useType,intoPieceType,status," +
			"accountManagerId,registerId,registerTime,intoPieceNo,loanAccount,loanCardNo,loanBank,loanOrg," +
			"repaymentAccount,repaymentCardNo,repaymentBank,repaymentOrg,commitTime,supplementMsg," +
			"procdefId,applyTime,repayType,actionStatus,orgId,releaseDate,replyAmount,productType,ownContract," +
			"authTermCount,authTermUnit,taskType,merchantId,merchantChargeType,merchantChargeRate," +
			"merchantChargeAmount,replyMerchantChargeRate,replyMerchantChargeAmount,merchantState,isOutOfTermPenalty,outOfTermPenaltyCalculationBasic) values(#{id}," +
			"#{loanProductId},#{customerId},#{code},#{applyAmount},#{termCount}," +
			"#{termUnit},#{repayTermCount},#{repayTermUnit},#{interestRate},#{penaltyRate},#{penaltyCalculationBasic},#{guaranteeType}," +
			"#{useType},#{intoPieceType},#{status},#{accountManagerId},#{registerId},#{registerTime}," +
			"#{intoPieceNo},#{loanAccount},#{loanCardNo},#{loanBank},#{loanOrg},#{repaymentAccount}," +
			"#{repaymentCardNo},#{repaymentBank},#{repaymentOrg},#{commitTime},#{supplementMsg}," +
			"#{procdefId},#{applyTime},#{repayType},#{actionStatus},#{orgId},#{releaseDate},#{replyAmount}," +
			"#{productType},#{ownContract},#{authTermCount},#{authTermUnit},#{taskType},#{merchantId}," +
			"#{merchantChargeType},#{merchantChargeRate},#{merchantChargeAmount},#{replyMerchantChargeRate}," +
			"#{replyMerchantChargeAmount},#{merchantState},#{isOutOfTermPenalty},#{outOfTermPenaltyCalculationBasic})")
	int insert(LoanProductApply loanProductApply);

	@Update("update loan_product_apply set loanProductId=#{loanProductId},customerId=#{customerId},code=#{code}," +
			"applyAmount=#{applyAmount},termCount=#{termCount},termUnit=#{termUnit},repayTermCount=#{repayTermCount}," +
			"repayTermUnit=#{repayTermUnit},interestRate=#{interestRate},penaltyRate=#{penaltyRate},penaltyCalculationBasic=#{penaltyCalculationBasic},guaranteeType=#{guaranteeType}," +
			"useType=#{useType},intoPieceType=#{intoPieceType},status=#{status},accountManagerId=#{accountManagerId}," +
			"registerId=#{registerId},registerTime=#{registerTime},intoPieceNo=#{intoPieceNo},loanAccount=#{loanAccount}," +
			"loanCardNo=#{loanCardNo},loanBank=#{loanBank},loanOrg=#{loanOrg},repaymentAccount=#{repaymentAccount}," +
			"repaymentCardNo=#{repaymentCardNo},repaymentBank=#{repaymentBank},repaymentOrg=#{repaymentOrg}," +
			"commitTime=#{commitTime},supplementMsg=#{supplementMsg},procdefId=#{procdefId},applyTime=#{applyTime}," +
			"repayType=#{repayType},actionStatus=#{actionStatus},orgId=#{orgId},releaseDate=#{releaseDate}," +
			"replyAmount=#{replyAmount},productType=#{productType},ownContract=#{ownContract},authTermCount=#{authTermCount}," +
			"authTermUnit=#{authTermUnit},taskType=#{taskType},releaseType=#{releaseType},merchantId=#{merchantId}," +
			"merchantChargeType=#{merchantChargeType},merchantChargeRate=#{merchantChargeRate}," +
			"merchantChargeAmount=#{merchantChargeAmount},replyMerchantChargeRate=#{replyMerchantChargeRate}," +
			"currentPeriodRepayId=#{currentPeriodRepayId},"+
			"firstPeriodCharge=#{firstPeriodCharge},replyMerchantChargeAmount=#{replyMerchantChargeAmount},isOutOfTermPenalty=#{isOutOfTermPenalty}, " +
            "outOfTermPenaltyCalculationBasic=#{outOfTermPenaltyCalculationBasic} " +
            "where id=#{id}")
	int update(LoanProductApply loanProductApply);

	@Update("update loan_product_apply set orgId=#{orgId},registerId=#{registerId},accountManagerId=#{accountManagerId}," +
			"merchantState=#{merchantState},merchantId=#{merchantId},merchantChargeType=#{merchantChargeType} where id=#{id}")
	int updateByDistribution(LoanProductApply loanProductApply);

	/**
	 * @Author qipengpai
	 * @Description //TODO 通过借贷申请id查询借贷申请信息
	 * @Date 11:04 2018/9/14
	 * @Param [id]
	 * @return com.company.platform.restapi.model.loan.LoanProductApply
	 **/
	@Select("select * from loan_product_apply where id=#{id}")
	LoanProductApply selectById(String id);

	@Select("select * from loan_product_apply where code=#{code}")
	LoanProductApply selectByCode(String code);

	@Select("select * from credit_product_apply where id=#{id}")
	Map<String, Object> selectByIdCreditafter(String id);

	@Select("select * from credit_product where id=#{id}")
	Map<String, Object> selectByIdCreditafterProduct(String id);

	@Select("SELECT COUNT(*) FROM loan_product_apply t WHERE t.intoPieceNo=#{intoPieceNo} AND t.id<>#{id}")
	int selectLoanProductApplyCountByIntoPieceNo(@Param("id") String id, @Param("intoPieceNo") String intoPieceNo);

	@Select("SELECT COUNT(*) FROM loan_product_apply t WHERE t.intoPieceNo=#{intoPieceNo}")
	int selectCountByIntoPieceNo(String intoPieceNo);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "selectByPage")
	List<Map<String, Object>> selectByPage(@Param("code") String code, @Param("productName") String productName,
			@Param("customerName") String customerName, @Param("status") Integer status, @Param("actionStatus") Integer actionStatus,
			@Param("orgIds") List<String> orgIds, @Param("start") Integer start, @Param("length") Integer length);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "selectByPageMoreParam")
	List<Map<String, Object>> selectByPageMoreParam(@Param("intoPieceNo") String intoPieceNo, @Param("productName") String productName,
			@Param("customerName") String customerName, @Param("status") Integer status, @Param("actionStatus") Integer actionStatus,
			@Param("orgIds") List<String> orgIds, @Param("customerType") String customerType, @Param("credentialType") String credentialType,
			@Param("credentialNo") String credentialNo, @Param("start") Integer start, @Param("length") Integer length, @Param("loanProductId") String loanProductId,@Param("queryParam") Map<String, Object> queryParam);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "selectByPageMoreParam")
	List<Map<String, Object>> selectByPageRoleTypeMoreParam(@Param("intoPieceNo") String intoPieceNo, @Param("productName") String productName,
			@Param("customerName") String customerName, @Param("status") Integer status, @Param("actionStatus") Integer actionStatus,
			@Param("accountManagerId") String accountManagerId, @Param("customerType") String customerType, @Param("credentialType") String credentialType,
			@Param("credentialNo") String credentialNo, @Param("start") Integer start, @Param("length") Integer length, @Param("loanProductId") String loanProductId,@Param("queryParam") Map<String, Object> queryParam);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "selectByPageMoreParamCreditafter")
	List<Map<String, Object>> selectByPageMoreParamCreditafter(@Param("code") String code, @Param("productName") String productName,
			@Param("customerName") String customerName, @Param("status") Integer status, @Param("actionStatus") Integer actionStatus,
			@Param("orgIds") List<String> orgIds, @Param("customerType") String customerType, @Param("credentialType") String credentialType, @Param("credentialNo") String credentialNo, @Param("start") Integer start, @Param("length") Integer length, @Param("productId") String productId);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "selectCount")
	int selectCount(@Param("code") String code, @Param("productName") String productName,
			@Param("customerName") String customerName, @Param("status") Integer status, @Param("actionStatus") Integer actionStatus,
			@Param("orgIds") List<String> orgIds);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "selectCountMoreParam")
	int selectCountMoreParam(@Param("intoPieceNo") String intoPieceNo, @Param("productName") String productName,
			@Param("customerName") String customerName, @Param("status") Integer status, @Param("actionStatus") Integer actionStatus,
			@Param("orgIds") List<String> orgIds,@Param("customerType") String customerType,@Param("credentialType") String credentialType,
			@Param("credentialNo") String credentialNo,@Param("loanProductId") String loanProductId,@Param("queryParam") Map<String, Object> queryParam);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "selectCountMoreParam")
	int selectCountMoreParamByRoleType(@Param("intoPieceNo") String intoPieceNo, @Param("productName") String productName,
			@Param("customerName") String customerName, @Param("status") Integer status, @Param("actionStatus") Integer actionStatus,
			@Param("accountManagerId") String accountManagerId, @Param("customerType") String customerType,@Param("credentialType") String credentialType,
			@Param("credentialNo") String credentialNo,@Param("loanProductId") String loanProductId,@Param("queryParam") Map<String, Object> queryParam);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "selectCountMoreParamCreditafter")
	int selectCountMoreParamCreditafter(@Param("code") String code, @Param("productName") String productName,
			@Param("customerName") String customerName, @Param("status") Integer status, @Param("actionStatus") Integer actionStatus,
			@Param("orgIds") List<String> orgIds,@Param("customerType") String customerType,@Param("credentialType") String credentialType,@Param("credentialNo") String credentialNo,@Param("productId") String productId);

	@Select("select t.*,t1.name,t1.code loanProductCode from loan_product_apply t join loan_product t1 on t.loanProductId=#{loanProductId} " +
			"and t.status in (0,1,4) and t.loanProductId=t1.id")
	List<Map<String, Object>> selectByLoanProductId(String loanProductId);

	@Select("SELECT apply.*,customer.customerName,customer.credentialNo,product.name FROM loan_product_apply apply JOIN customer_public customer " +
			"ON apply.registerId=#{registerId} and apply.status=#{status} AND apply.customerId=customer.id JOIN loan_product product ON apply.loanProductId=product.id " +
			"ORDER BY apply.applyTime DESC limit #{start},#{length}")
	List<Map<String, Object>> queryToCommitApplyByRegisterId(@Param("registerId") String registerId, @Param("status") int status, @Param("start") int start, @Param("length") int length);

	@Select("select count(*) from loan_product_apply apply JOIN customer_public customer ON apply.registerId=#{registerId} and apply.status=#{status} " +
			"AND apply.customerId=customer.id JOIN loan_product product ON apply.loanProductId=product.id")
	int queryToCommitApplyCountByRegisterId(@Param("registerId") String registerId, @Param("status") int status);

	@Update("update loan_product_apply set actionStatus=#{actionStatus} where id=#{id}")
	int updateActionStatus(@Param("id") String id, @Param("actionStatus") int actionStatus);

	@Update("update credit_product_apply set actionStatus=#{actionStatus} where id=#{id}")
	int updateActionStatusCreditafter(@Param("id") String id, @Param("actionStatus") int actionStatus);

	@Select("SELECT (SELECT COUNT(*) FROM loan_product_apply WHERE customerId=#{customerId} AND `status`=1) count1," +
							"(SELECT COUNT(*) FROM loan_product_apply WHERE customerId=#{customerId} AND `status`=3) count2," +
							"(SELECT COUNT(*) FROM loan_product_apply WHERE customerId=#{customerId} AND actionStatus=2) count3," +
							"(SELECT COUNT(*) FROM loan_product_apply WHERE customerId=#{customerId} AND actionStatus=3) count4")
	Map<String, Integer> selectCustomerApplyCount(String customerId);

	@Select("select count(*) from guarantor_info i join loan_product_apply_guarantor g on i.credentialType=#{credentialType} and i.credentialNo=#{credentialNo} " +
			"and i.id=g.guarantorId and g.guarantorType=0 join loan_product_apply a on g.loanProductApplyId=a.id and a.customerId=#{customerId}")
	int selectGuarantorApplyCount(@Param("customerId") String customerId, @Param("credentialType") String credentialType,
			@Param("credentialNo") String credentialNo);

	@InsertProvider(type = LoanProductApplySqlProvider.class, method = "batchAdd")
	void batchAdd(@Param("list") List<LoanProductApply> list);

	/*@Update("update loan_product_apply set actionStatus = #{actionStatus} where id = #{id}")
	int updateActionStatus(@Param("id") String id, @Param("actionStatus") Integer actionStatus);*/

	@Update("update loan_product_apply set taskType = #{taskType} where id = #{id}")
	int updateTaskType(@Param("id") String id, @Param("taskType") Integer taskType);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "selectPageCountByUser")
	int selectPageCountByUser(@Param("userId") String userId, @Param("operTaskType") Integer operTaskType);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "selectPageByUser")
	List<Map<String, Object>> selectPageByUser(@Param("userId") String userId,
			@Param("operTaskType") Integer operTaskType, @Param("start") Integer start, @Param("length") Integer length);

	@Select("select actionStatus from loan_product_apply where id = #{id}")
	Integer queryActionStatusById(@Param("id") String id);

	@Select("select sum(a.applyAmount) from loan_product_apply a join loan_product_apply_model m on m.modelType=#{modelType} and m.modelInstanceId in (${modelInstanceIds}) " +
			"and m.loanProductApplyId=a.id")
	BigDecimal selectApplyTotalAmountByModelInstance(@Param("modelType") String modelType, @Param("modelInstanceIds") String modelInstanceIds);

	@Select("SELECT * FROM (" +
			"SELECT a.code,a.applyTime,a.applyAmount,a.replyAmount,a.termCount,a.termUnit,a.authTermCount,a.authTermUnit,a.status,a.actionStatus,a.intoPieceType," +
			"a.procdefId,p.name,2 AS pType FROM loan_product_apply a JOIN loan_product p ON a.customerId=#{customerId} AND a.loanProductId=p.id " +
			"UNION ALL " +
			"SELECT a.code,a.applyTime,a.applyAmount,a.replyAmount,a.termCount,a.termUnit,a.authTermCount,a.authTermUnit,a.status,a.actionStatus,a.intoPieceType," +
			"a.procdefId,p.name,1 AS pType FROM credit_product_apply a JOIN credit_product p ON a.customerId=#{customerId} AND a.loanProductId=p.id" +
			") t ORDER BY t.applyTime DESC limit #{start},#{length}")
	List<Map<String, Object>> queryApplyByCustomer(@Param("customerId") String customerId, @Param("start") Integer start, @Param("length") Integer length);

	@Select("SELECT COUNT(*) FROM (" +
			"SELECT a.id FROM loan_product_apply a JOIN loan_product p ON a.customerId=#{customerId} AND a.loanProductId=p.id " +
			"UNION ALL " +
			"SELECT a.id FROM credit_product_apply a JOIN credit_product p ON a.customerId=#{customerId} AND a.loanProductId=p.id) t")
	int queryApplyCountByCustomer(String customerId);

	/**
	 * @Author qipengpai
	 * @Description //TODO 删除未提交进件信息
	 * @Date 11:06 2018/9/14
	 * @Param [id]
	 * @return int
	 **/
	@Delete("delete from loan_product_apply where id=#{id}")
	int deleteById(String id);

	/**
	 * 进件领取任务数量
	 * @param userId
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM " +
			"(SELECT apply.* FROM loan_product_apply apply,user_dept_rel rel,loan_product product " +
			"WHERE apply.registerId IS NULL AND apply.orgId=rel.orgId AND rel.userId=#{userId} " +
			"AND apply.loanProductId=product.id "+
			"AND product.autoProcessStatus IS NULL "+
			"UNION " +
			"SELECT apply.* FROM loan_product_apply apply,org_product_relation relation,user_dept_rel rel,loan_product product " +
			"WHERE apply.registerId IS NULL AND apply.orgId IS NULL AND apply.loanProductId=relation.loanProductId " +
			"AND apply.loanProductId=product.id "+
			"AND product.autoProcessStatus IS NULL "+
			"AND relation.orgId=rel.orgId AND rel.userId=#{userId}) t")
	int queryTakeThePecipeCountByUserId(String userId);
	/**
	 * 进件领取任务列表
	 * @param userId
	 * @param start
	 * @param length
	 * @return
	 */
	@Select("SELECT t.* FROM " +
			"(SELECT apply.*,public.customerName,public.credentialType,public.credentialNo,product.name " +
			"FROM loan_product_apply apply,user_dept_rel rel,customer_public public,loan_product product " +
			"WHERE apply.registerId IS NULL AND apply.orgId=rel.orgId AND rel.userId=#{userId} " +
			"AND apply.customerId=public.id AND apply.loanProductId=product.id " +
			"AND product.autoProcessStatus IS NULL "+
			"UNION " +
			"SELECT apply.*,public.customerName,public.credentialType,public.credentialNo,product.name " +
			"FROM loan_product_apply apply,org_product_relation relation,user_dept_rel rel,customer_public public," +
			"loan_product product WHERE apply.registerId IS NULL AND apply.orgId IS NULL AND " +
			"product.autoProcessStatus IS NULL AND "+
			"apply.loanProductId=relation.loanProductId AND relation.orgId=rel.orgId AND rel.userId=#{userId} " +
			"AND apply.customerId=public.id AND apply.loanProductId=product.id) t " +
			"ORDER BY t.applyTime DESC LIMIT #{start},#{length}")
	List<Map<String, Object>> queryTakeThePecipeByUserId(@Param("userId") String userId, @Param("start") Integer start, @Param("length") Integer length);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "queryIntoDistributionCount")
	int queryIntoDistributionCount(@Param("queryCond") Map<String, Object> queryCond);

	@SelectProvider(type = LoanProductApplySqlProvider.class, method = "queryIntoDistribution")
	List<Map<String, Object>> queryIntoDistribution(@Param("queryCond") Map<String, Object> queryCond,
			@Param("start") Integer start, @Param("length") Integer length);

	@Select({
		"select a.*,b.name from credit_examined a "
		+ "left join credit_product b "
		+ "on a.productId = b.id "
		+ "where a.id=#{businessKey}"
	})
	Map<String, Object> selectExaminedInfo(String id);

	@Select({
		"select a.*,b.name from credit_product_apply a "
		+ "left join credit_product b "
		+ "on a.loanProductId = b.id "
		+ "where a.id=#{businessKey}"
	})
	Map<String, Object> selectProductInfo(String id);


	@Select({
		"select a.*,b.name from credit_apply_info a "
		+ "left join credit_product b "
		+ "on a.creditProductId = b.id "
		+ "where a.id=#{businessKey}"
	})
	Map<String, Object> selectCreditInfo(String id);


	@Select("select count(*) from credit_product_apply apply JOIN customer_public customer ON apply.registerId=#{registerId} and (apply.status=#{status} or apply.status = 4) " +
			"AND apply.customerId=customer.id JOIN credit_product product ON apply.loanProductId=product.id")
	int queryToCommitProductCountByRegisterId(@Param("registerId")String registerId, @Param("status")int status);

	@Select("SELECT apply.*,customer.customerName,customer.credentialNo,product.name FROM credit_product_apply apply JOIN customer_public customer " +
			"ON apply.registerId=#{registerId} and (apply.status=#{status} or apply.status=4) AND apply.customerId=customer.id JOIN credit_product product ON apply.loanProductId=product.id " +
			"ORDER BY apply.applyTime DESC limit #{start},#{length}")
	List<Map<String, Object>> queryToCommitProductByRegisterId(@Param("registerId")String registerId, @Param("status")int status, @Param("start")int start, @Param("length")int length);

	@Select("select * from loan_product_apply where status = '2' and actionStatus in ('2','3') and customerId=#{customerId}")
	List<LoanProductApply> queryApplyByCustomerId(String customerId);

	@Select("select count(*) from loan_product_apply where status = '2' and actionStatus in ('2') and customerId=#{customerId}")
	int queryRepayingCountByCustomerId(String customerId);

	/**
	 * 前期服务费
	 * @param applyId
	 * @return
	 */
	@Select("SELECT SUM(feeAmount) feeAmount FROM loan_product_apply_fee WHERE chargeType=1 AND loanProductApplyId=#{applyId}")
	public BigDecimal serviceCharge(@Param("applyId") String applyId);

	/**
	 * 分期服务费
	 * @param applyId
	 * @return
	 */
	@Select("SELECT SUM(feeAmount) FROM loan_repay_fee WHERE  loanProductApplyId=#{applyId} and loanRepayId is not null")
	public BigDecimal byStagesFree(@Param("applyId") String applyId);

	/**
	 * 单期服务费
	 * @param applyId
	 * @return
	 */
	@Select("SELECT feeAmount FROM loan_repay_fee WHERE  loanProductApplyId=#{applyId} and loanRepayId is not null limit 0,1")
	public BigDecimal oneByStagesFree(@Param("applyId") String applyId);

	@Select("select * from loan_product_apply where actionStatus=2 or actionStatus=3 ")
	List<LoanProductApply> selectInRepaymentOrCompletedApply();

	@Select("SELECT lpa.id, lpa. CODE, lpa.loanProductId, lp.`name` productName, lpa.customerId, cp.customerName, pci.gender, lpa.replyAmount "
			+ " FROM loan_product_apply lpa LEFT JOIN loan_product lp ON lp.id = lpa.loanProductId "
			+ " LEFT JOIN customer_public cp ON cp.id = lpa.customerId "
			+ " LEFT JOIN personal_customer_info pci ON pci.id = cp.id "
			+ " where lpa.id = #{applyId} ")
	public Map<String, Object> getLoanProductApplyInfoById(@Param("applyId") String applyId);

	@Select("select isDailyInterestRate from loan_product lp join loan_product_apply lpa on lp.id = lpa.loanProductId where lpa.id = #{id}")
	boolean checkLoanApplyisDailyInterestRate(String id);

    /**
     * @Author qipengpai
     * @Description //TODO 拒绝或人工审核原因
     * @Date 9:28 2018/9/14
     * @Param [loanProductApplyId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Select("SELECT b.ID_ id,b.MESSAGE_ message FROM act_hi_procinst a,act_hi_comment b WHERE  a.PROC_INST_ID_ = b.PROC_INST_ID_ AND  a.BUSINESS_KEY_=#{loanProductApplyId} AND  LEFT(b.USER_ID_,INSTR(b.USER_ID_,':')-1) !='taskthree'  ORDER BY id DESC LIMIT 0,1")
	Map<String,Object> getApprovalLogMessage(@Param("loanProductApplyId")String loanProductApplyId);



}
