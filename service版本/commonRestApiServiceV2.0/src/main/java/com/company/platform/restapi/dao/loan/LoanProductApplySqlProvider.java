package com.company.platform.restapi.dao.loan;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.company.platform.restapi.model.loan.LoanProductApply;

public class LoanProductApplySqlProvider {

	public String selectByPage(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT t1.*,t2.name productName,t3.customerName,t3.credentialNo,t3.eType FROM loan_product_apply t1 JOIN ")
				.append("loan_product t2 ON t1.loanProductId=t2.id AND t1.status in (1,2) JOIN customer_public t3 ON t1.customerId=t3.id")
				.append(this.createWhereSql(params));
		Integer actionStatus = (Integer) params.get("actionStatus");
		if (actionStatus != 1) {
			sql.append("order by t1.releaseDate desc ");
		} else {
			sql.append("order by t1.applyTime desc ");
		}
		if (params.get("start") != null) {
			sql.append("limit #{start},#{length}");
		}
		return sql.toString();
	}

	public String selectCount(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from loan_product_apply t1 JOIN loan_product t2 ON t1.loanProductId=t2.id ")
				.append("AND t1.status in (1,2) JOIN customer_public t3 ON t1.customerId=t3.id")
				.append(this.createWhereSql(params));
		return sql.toString();
	}

	private String createWhereSql(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer(" where 1=1 ");
		String code = (String) params.get("code");
		String productName = (String) params.get("productName");
		String customerName = (String) params.get("customerName");
		Integer status = (Integer) params.get("status");
		Integer actionStatus = (Integer) params.get("actionStatus");
		List<String> orgIds = (List<String>) params.get("orgIds");

		// String query_code = params.get("query_code")+"";
		// String query_customerName = params.get("query_customerName")+"";
		// String query_credentialNo = params.get("query_credentialNo")+"";
		// if(StringUtils.isNotBlank(query_code)) {
		// sql.append(" and t1.code like '%").append(code).append("%' ");
		// }
		// if(StringUtils.isNotBlank(query_customerName)) {
		// sql.append(" and t3.customerName like '%").append(code).append("%'
		// ");
		// }
		// if(StringUtils.isNotBlank(query_credentialNo)) {
		// sql.append(" and t3.credentialNo like '%").append(code).append("%'
		// ");
		// }

		if (StringUtils.isNotBlank(code)) {
			sql.append("and t1.code like '%").append(code).append("%' ");
		}

		if (StringUtils.isNotBlank(code)) {
			sql.append("and t1.code like '%").append(code).append("%' ");
		}
		if (StringUtils.isNotBlank(productName)) {
			sql.append("and t2.name like '%").append(productName).append("%' ");
		}
		if (StringUtils.isNotBlank(customerName)) {
			sql.append("and t3.customerName like '%").append(customerName).append("%' ");
		}
		if (status != null) {
			sql.append("and t1.status=#{status} ");
		}
		if (actionStatus != null) {
			if (actionStatus == -1) {
				sql.append("and t1.actionStatus>=1 ");
			} else if (actionStatus == -2) {
				sql.append("and t1.actionStatus>=2 ");
			} else {
				sql.append("and t1.actionStatus=#{actionStatus} ");
			}
		}
		if (orgIds != null && orgIds.size() > 0) {
			if (orgIds.size() == 1) {
				sql.append("and t1.orgId='").append(orgIds.get(0)).append("' ");
			} else {
				sql.append("and t1.orgId in (");
				int last = orgIds.size() - 1;
				int num = 0;
				for (String orgId : orgIds) {
					sql.append("'").append(orgId).append("'");
					if (num++ < last) {
						sql.append(",");
					}
				}
				sql.append(") ");
			}
		}
		return sql.toString();
	}

	public String selectCountMoreParam(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from loan_product_apply t1 JOIN loan_product t2 ON t1.loanProductId=t2.id ")
				.append("AND t1.status in (1,2) JOIN customer_public t3 ON t1.customerId=t3.id")
				.append(this.createWhereSqlMoreParam(params));

		sql.append(" and t3.customerName is not null ");
		return sql.toString();
	}

	public String selectCountMoreParamCreditafter(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from credit_product_apply t1 JOIN credit_product t2 ON t1.loanProductId=t2.id ")
				.append("AND t1.status in (1,2) JOIN customer_public t3 ON t1.customerId=t3.id")
				.append(this.createWhereSqlMoreParamCreditafter(params));
		return sql.toString();
	}

	public String selectByPageMoreParam(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT t1.*,t2.name productName,t3.customerName,t3.credentialNo,t3.credentialType,t3.eType FROM loan_product_apply t1 JOIN ")
				.append("loan_product t2 ON t1.loanProductId=t2.id AND t1.status in (1,2) JOIN customer_public t3 ON t1.customerId=t3.id")
				.append(this.createWhereSqlMoreParam(params));
		sql.append(" and t3.customerName is not null ");
		Integer actionStatus = (Integer) params.get("actionStatus");
		if (actionStatus != 1) {
			sql.append("order by t1.releaseDate desc ");
		} else {
			sql.append("order by t1.applyTime desc ");
		}
		if (params.get("start") != null) {
			sql.append("limit #{start},#{length}");
		}
		return sql.toString();
	}

	public String selectByPageMoreParamCreditafter(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT t1.*,t2.name productName,t3.customerName,t3.credentialNo,t3.eType FROM credit_product_apply t1 JOIN ")
				.append("credit_product t2 ON t1.loanProductId=t2.id AND t1.status in (1,2) JOIN customer_public t3 ON t1.customerId=t3.id")
				.append(this.createWhereSqlMoreParamCreditafter(params));
		Integer actionStatus = (Integer) params.get("actionStatus");
		if (actionStatus != 1) {
			sql.append("order by t1.releaseDate desc ");
		} else {
			sql.append("order by t1.applyTime desc ");
		}
		if (params.get("start") != null) {
			sql.append("limit #{start},#{length}");
		}
		return sql.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String createWhereSqlMoreParam(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer(" where 1=1 ");

		Map<String, Object> queryCond = params.get("queryParam") == null ? new HashMap()
				: (Map<String, Object>) params.get("queryParam");

		String intoPieceNo = (String) params.get("intoPieceNo");
		String productName = (String) params.get("productName");
		String customerName = (String) params.get("customerName");
		Integer status = (Integer) params.get("status");
		Integer actionStatus = (Integer) params.get("actionStatus");
		String loanProductId = (String) params.get("loanProductId");

		String customerType = (String) params.get("customerType");
		String credentialType = (String) params.get("credentialType");
		String credentialNo = (String) params.get("credentialNo");

		if (StringUtils.isNotBlank(loanProductId)) {
			sql.append("and t1.loanProductId = '").append(loanProductId).append("' ");
		}
		if (StringUtils.isNotBlank(credentialNo)) {
			sql.append("and t3.credentialNo like '%").append(credentialNo).append("%' ");
		}

		if (StringUtils.isNotBlank(customerType)) {
			sql.append("and t3.eType like '%").append(customerType).append("%' ");
		}

		if (StringUtils.isNotBlank(credentialType)) {
			sql.append("and t3.credentialType like '%").append(credentialType).append("%' ");
		}

		if (StringUtils.isNotBlank(intoPieceNo)) {
			sql.append("and t1.intoPieceNo like '%").append(intoPieceNo).append("%' ");
		}

		if (StringUtils.isNotBlank(productName)) {
			sql.append("and t2.name like '%").append(productName).append("%' ");
		}
		if (StringUtils.isNotBlank(customerName)) {
			sql.append("and t3.customerName like '%").append(customerName).append("%' ");
		}
		if (status != null) {
			sql.append("and t1.status=#{status} ");
		}
		if (actionStatus != null) {
			if (actionStatus == -1) {
				sql.append("and t1.actionStatus>=1 ");
			} else if (actionStatus == -2) {
				sql.append("and t1.actionStatus>=2 ");
			} else {
				sql.append("and t1.actionStatus=#{actionStatus} ");
			}
		}

		if (queryCond.containsKey("applyOrRepayDate") && !"".equals(queryCond.get("applyOrRepayDate").toString())) {// 进件编号
			String[] arr = queryCond.get("applyOrRepayDate").toString().split(" - ");
			if ("1".equals(queryCond.get("action").toString())) {
				sql.append(" and DATE_FORMAT(t1.applyTime, '%Y-%m-%d') >= '" + arr[0] + "'");
				sql.append(" and DATE_FORMAT(t1.applyTime, '%Y-%m-%d') <= '" + arr[1] + "'");
			} else {
				sql.append(" and DATE_FORMAT(t1.releaseDate, '%Y-%m-%d') >= '" + arr[0] + "'");
				sql.append(" and DATE_FORMAT(t1.releaseDate, '%Y-%m-%d') <= '" + arr[1] + "'");
			}
		}

		if (params.containsKey("orgIds")) {
			List<String> orgIds = (List<String>) params.get("orgIds");
			if (orgIds != null && orgIds.size() > 0) {
				if (orgIds.size() == 1) {
					sql.append("and t1.orgId='").append(orgIds.get(0)).append("' ");
				} else {
					sql.append("and t1.orgId in (");
					int last = orgIds.size() - 1;
					int num = 0;
					for (String orgId : orgIds) {
						sql.append("'").append(orgId).append("'");
						if (num++ < last) {
							sql.append(",");
						}
					}
					sql.append(") ");
				}
			}
		}

		if (params.containsKey("accountManagerId")) {
			sql.append(" and t1.accountManagerId = '").append(params.get("accountManagerId").toString()).append("' ");
		}
		return sql.toString();
	}

	private String createWhereSqlMoreParamCreditafter(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer(" where 1=1 ");
		String code = (String) params.get("code");
		String productName = (String) params.get("productName");
		String customerName = (String) params.get("customerName");
		Integer status = (Integer) params.get("status");
		Integer actionStatus = (Integer) params.get("actionStatus");
		List<String> orgIds = (List<String>) params.get("orgIds");

		String customerType = (String) params.get("customerType");
		String credentialType = (String) params.get("credentialType");
		String credentialNo = (String) params.get("credentialNo");
		String productId = (String) params.get("productId");

		if (StringUtils.isNotBlank(productId)) {
			sql.append("and t1.loanProductId = '").append(productId).append("' ");
		}
		if (StringUtils.isNotBlank(credentialNo)) {
			sql.append("and t3.credentialNo like '%").append(credentialNo).append("%' ");
		}

		if (StringUtils.isNotBlank(customerType)) {
			sql.append("and t3.eType like '%").append(customerType).append("%' ");
		}

		if (StringUtils.isNotBlank(credentialType)) {
			sql.append("and t3.credentialType like '%").append(credentialType).append("%' ");
		}

		if (StringUtils.isNotBlank(code)) {
			sql.append("and t1.code like '%").append(code).append("%' ");
		}
		if (StringUtils.isNotBlank(productName)) {
			sql.append("and t2.name like '%").append(productName).append("%' ");
		}
		if (StringUtils.isNotBlank(customerName)) {
			sql.append("and t3.customerName like '%").append(customerName).append("%' ");
		}
		if (status != null) {
			sql.append("and t1.status=#{status} ");
		}
		if (actionStatus != null) {
			if (actionStatus == -1) {
				sql.append("and t1.actionStatus>=1 ");
			} else if (actionStatus == -2) {
				sql.append("and t1.actionStatus>=2 ");
			} else {
				sql.append("and t1.actionStatus=#{actionStatus} ");
			}
		}
		if (orgIds != null && orgIds.size() > 0) {
			if (orgIds.size() == 1) {
				sql.append("and t1.orgId='").append(orgIds.get(0)).append("' ");
			} else {
				sql.append("and t1.orgId in (");
				int last = orgIds.size() - 1;
				int num = 0;
				for (String orgId : orgIds) {
					sql.append("'").append(orgId).append("'");
					if (num++ < last) {
						sql.append(",");
					}
				}
				sql.append(") ");
			}
		}
		return sql.toString();
	}

	public String batchAdd(Map<String, Object> params) {
		List<LoanProductApply> list = (List<LoanProductApply>) params.get("list");
		StringBuffer sql = new StringBuffer();
		sql.append(
				"insert into loan_product_apply(id,loanProductId,customerId,code,applyAmount,replyAmount,termCount,termUnit,"
						+ "authTermCount,authTermUnit,repayTermCount,repayTermUnit,repayType,applyTime,interestRate,penaltyRate,guaranteeType,"
						+ "useType,intoPieceType,productType,ownContract,status,actionStatus,accountManagerId,registerId,registerTime,intoPieceNo,"
						+ "loanAccount,loanCardNo,loanBank,loanOrg,repaymentAccount,repaymentCardNo,repaymentBank,repaymentOrg,commitTime,"
						+ "supplementMsg,procdefId,orgId,releaseDate,taskType,merchantState,merchantChargeType,merchantId) values");
		MessageFormat messageFormat = new MessageFormat(
				"(#'{'list[{0}].id},#'{'list[{0}].loanProductId},#'{'list[{0}].customerId},"
						+ "#'{'list[{0}].code},#'{'list[{0}].applyAmount},#'{'list[{0}].replyAmount},#'{'list[{0}].termCount},#'{'list[{0}].termUnit},"
						+ "#'{'list[{0}].authTermCount},#'{'list[{0}].authTermUnit},#'{'list[{0}].repayTermCount},#'{'list[{0}].repayTermUnit},"
						+ "#'{'list[{0}].repayType},#'{'list[{0}].applyTime},#'{'list[{0}].interestRate},#'{'list[{0}].penaltyRate},#'{'list[{0}].guaranteeType},"
						+ "#'{'list[{0}].useType},#'{'list[{0}].intoPieceType},#'{'list[{0}].productType},#'{'list[{0}].ownContract},#'{'list[{0}].status},"
						+ "#'{'list[{0}].actionStatus},#'{'list[{0}].accountManagerId},#'{'list[{0}].registerId},#'{'list[{0}].registerTime},#'{'list[{0}].intoPieceNo},"
						+ "#'{'list[{0}].loanAccount},#'{'list[{0}].loanCardNo},#'{'list[{0}].loanBank},#'{'list[{0}].loanOrg},#'{'list[{0}].repaymentAccount},"
						+ "#'{'list[{0}].repaymentCardNo},#'{'list[{0}].repaymentBank},#'{'list[{0}].repaymentOrg},#'{'list[{0}].commitTime},"
						+ "#'{'list[{0}].supplementMsg},#'{'list[{0}].procdefId},#'{'list[{0}].orgId},#'{'list[{0}].releaseDate},#'{'list[{0}].taskType},"
						+ "#'{'list[{0}].merchantState},#'{'list[{0}].merchantChargeType},#'{'list[{0}].merchantId})");
		for (int i = 0, len = list.size(); i < len; i++) {
			sql.append(messageFormat.format(new Object[] { i + "" }));
			if (i < len - 1) {
				sql.append(",");
			}
		}
		return sql.toString();
	}

	public String selectPageCountByUser(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM ")
				.append("loan_product_apply t,loan_task_oper_user t1,loan_product t2,customer_public t3 ")
				.append("WHERE t1.userId=#{userId} AND t1.loanProductApplyId=t.id ")
				.append(this.createWhereSql((Integer) params.get("operTaskType")));
		return sql.toString();
	}

	public String selectPageByUser(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.*,t2.name productName,t3.customerName,t3.credentialNo,t3.eType FROM ")
				.append("loan_product_apply t,loan_task_oper_user t1,loan_product t2,customer_public t3 ")
				.append("WHERE t1.userId=#{userId} AND t1.loanProductApplyId=t.id ")
				.append(this.createWhereSql((Integer) params.get("operTaskType"))).append("ORDER BY t.applyTime desc ");
		if (params.get("start") != null && params.get("length") != null) {
			sql.append("LIMIT #{start},#{length}");
		}
		return sql.toString();
	}

	private String createWhereSql(Integer operTaskType) {
		StringBuffer sql = new StringBuffer();
		if (operTaskType != null) {
			if (operTaskType == 0) {
				sql.append("AND t1.operTaskType=0 AND t.taskType IN(2,3) ");
			} else if (operTaskType == 1) {
				sql.append("AND t1.operTaskType=1 AND t.taskType=5 ");
			}
		}
		sql.append("AND t.loanProductId=t2.id AND t.customerId=t3.id ");
		return sql.toString();
	}

	public String queryIntoDistributionCount(Map<String, Object> params) {
		Map<String, Object> queryCond = (Map<String, Object>) params.get("queryCond");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM loan_product_apply apply,customer_public public,loan_product product WHERE ")
				.append("apply.registerId IS NULL AND apply.status=0 AND apply.customerId=public.id AND apply.loanProductId=product.id AND product.autoProcessStatus IS NULL ")
				.append(this.createQueryIntoDistributionWhere(queryCond));
		return sql.toString();
	}

	public String queryIntoDistribution(Map<String, Object> params) {
		Map<String, Object> queryCond = (Map<String, Object>) params.get("queryCond");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apply.*,public.customerName,public.credentialType,public.credentialNo,public.eType,")
				.append("product.name FROM loan_product_apply apply,customer_public public,loan_product product ")
				.append("WHERE apply.registerId IS NULL AND apply.status=0 AND apply.customerId=public.id AND ")
				.append("apply.loanProductId=product.id ").append("AND product.autoProcessStatus IS NULL ")
				.append(this.createQueryIntoDistributionWhere(queryCond)).append("ORDER BY apply.applyTime DESC ");
		if (params.get("start") != null && params.get("length") != null) {
			sql.append("LIMIT #{start},#{length}");
		}
		return sql.toString();
	}

	private String createQueryIntoDistributionWhere(Map<String, Object> queryCond) {
		StringBuffer sql = new StringBuffer();
		if (queryCond != null) {
			String eType = (String) queryCond.get("customerType");
			String code = (String) queryCond.get("code");
			String credentialType = (String) queryCond.get("credentialType");
			String credentialNo = (String) queryCond.get("credentialNo");
			String customerName = (String) queryCond.get("customerName");
			String loanProductId = (String) queryCond.get("loanProductId");
			if (StringUtils.isNotBlank(eType)) {
				sql.append("AND public.eType='").append(eType).append("' ");
			}
			if (StringUtils.isNotBlank(code)) {
				sql.append("AND apply.code like '%").append(code).append("%' ");
			}
			if (StringUtils.isNotBlank(credentialType)) {
				sql.append("AND public.credentialType='").append(credentialType).append("' ");
			}
			if (StringUtils.isNotBlank(credentialNo)) {
				sql.append("AND public.credentialNo like '%").append(credentialNo).append("%' ");
			}
			if (StringUtils.isNotBlank(customerName)) {
				sql.append("AND public.customerName like '%").append(customerName).append("%' ");
			}
			if (StringUtils.isNotBlank(loanProductId)) {
				sql.append("AND apply.loanProductId='").append(loanProductId).append("' ");
			}
		}
		return sql.toString();
	}
}
