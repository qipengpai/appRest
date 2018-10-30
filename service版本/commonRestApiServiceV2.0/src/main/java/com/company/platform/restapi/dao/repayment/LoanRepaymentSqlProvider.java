package com.company.platform.restapi.dao.repayment;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class LoanRepaymentSqlProvider {

	public String queryRepayments_period(Map<String, Object> params) {
		Map<String, String> queryCond = (Map<String, String>) params.get("queryCond");
		String loanProductApplyId = queryCond.get("loanProductApplyId");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT lr.id,lp.name productName,lpa.code,lr.duePrincipal,lr.periodNum,lr.dueRepayDate,lpa.id loanApplyId,")
			.append("lr.repayStatus,lpa.applyAmount,lr.dueInterest,lr.penalty duePenalty ")
			.append("FROM loan_repayment lr,loan_product_apply lpa,loan_product lp,customer_public cp ")
			.append("WHERE lr.loanProductApplyId = lpa.id ")
			.append("AND lpa.loanProductId = lp.id ")
			.append("AND cp.id = lpa.customerId ")
			.append("AND lpa.id = '"+loanProductApplyId+"' ");

		if(queryCond.get("condition") != null) {
	    	if("1".equals(queryCond.get("condition"))){//已还完
	    		sql.append("and lr.repayStatus= 1 ");
	    	}else if("2".equals(queryCond.get("condition"))){//未还完
	    		sql.append("and lr.repayStatus in (0,2) ");
	    	}else if("0".equals(queryCond.get("condition"))){//当期之展示当期未还和未还完的数据数据
	    		sql.append("and lr.repayStatus in (0,2) ");
	    		sql.append("and lr.id IN (SELECT currentPeriodRepayId FROM loan_product_apply WHERE currentPeriodRepayId is not null) ");
	    	}
        }
		
		if("1".equals((String)queryCond.get("condition"))){//已还完
			sql.append(" order by realRepayDate desc ");
		}else{
			sql.append(" order by dueRepayDate ");
		}
		
		return sql.toString();
		
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 查询进件列表
	 * @Date 11:55 2018/9/13
	 * @Param [params]
	 * @return java.lang.String
	 **/
	public String selectQuickQueryLoan(Map<String, Object> params){
		Map<String, Object> queryCond = (Map<String, Object>) params.get("queryCond");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t1.id,t1.intoPieceNo,t1.applyAmount,"
				+ "t1.applyTime,"
				+ "t3.customerName,t3.credentialNo,t3.mobilePhone,"
				+ "t0.taskStatus "
				+ " FROM loan_product_apply t1  "
				+ " left JOIN loan_product t2 ON t1.loanProductId=t2.id")
				.append(" left join sys_user_info t5 on t1.accountManagerId = t5.userId ")
				.append(" left join organization t6 on t1.orgId = t6.id")
				.append(" left JOIN customer_public t3 ON t1.customerId=t3.id ");
		//确定查询进件列表还是信审列表
		sql.append(appendCodition(queryCond));
		//根据条件查询
		sql.append(createQueryWhereQuickQueryLoan(params));
		sql.append(" order by t1.applyTime desc ");
//		if (params.get("start") != null && params.get("length") != null && Integer.parseInt(params.get("length") + "") > 0) {
//			sql.append(" limit #{start},#{length}");
//		}
		return sql.toString();
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 进件,信审 数量明细查询
	 * @Date 20:35 2018/9/13
	 * @Param [params]
	 * @return java.lang.String
	 **/
	public String getLoanEntryCreditNum(Map<String, Object> params){
		StringBuffer sql = new StringBuffer();
		Map<String, Object> queryCond =(Map<String, Object>) params.get("queryCond");
		sql.append(" select (select COUNT(DISTINCT b.loanAppid) from loan_product_apply a,act_loan_task_status b,(select MAX(id)as bid  from act_loan_task_status where taskId in ('taskone','tasktwo')  GROUP BY loanAppid) c where a.id =b.loanAppid and b.id = c.bid and b.taskId in ('taskone','tasktwo')  and b.taskStatus='1'   ");
		sql.append(appendCondition(queryCond));
		sql.append(" ) entrySuccessNum, ");
		sql.append(" (select COUNT(DISTINCT b.loanAppid) from loan_product_apply a,act_loan_task_status b,(select MAX(id)as bid  from act_loan_task_status where taskId in ('taskone','tasktwo')  GROUP BY loanAppid) c where a.id =b.loanAppid and b.id = c.bid and b.taskId in ('taskone','tasktwo')  and b.taskStatus='0'   ");
		sql.append(appendCondition(queryCond));
		sql.append(" ) personCheckNum, ");
		sql.append(" (select COUNT(DISTINCT b.loanAppid) from loan_product_apply a,act_loan_task_status b,(select MAX(id)as bid  from act_loan_task_status where taskId in ('taskone','tasktwo')  GROUP BY loanAppid) c where a.id =b.loanAppid and b.id = c.bid and b.taskId in ('taskone','tasktwo')  and b.taskStatus='2'   ");
		sql.append(appendCondition(queryCond));
		sql.append(" ) entryFailNum, ");
		sql.append(" (select COUNT(DISTINCT b.loanAppid) from loan_product_apply a,act_loan_task_status b where a.id =b.loanAppid and b.taskId = 'taskthree'  and b.taskStatus='1' ");
		sql.append(appendCondition(queryCond));
		sql.append(" ) creditSuccessNum, ");
		sql.append(" (select COUNT(DISTINCT b.loanAppid) from loan_product_apply a,act_loan_task_status b where a.id =b.loanAppid and b.taskId = 'taskthree' and b.taskStatus='2' ");
		sql.append(appendCondition(queryCond));
		sql.append(" ) creditFailNum ");
		return sql.toString();
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 拼接查询 人 查询时间（目前本月）
	 * @Date 20:37 2018/9/13
	 * @Param [queryCond]
	 * @return java.lang.String
	 **/
	private String appendCondition(Map<String,Object> queryCond) {
		StringBuffer sql = new StringBuffer();
		sql.append(" and a.accountManagerId = '").append(queryCond.get("userId").toString()).append("' ");
		if(queryCond.containsKey("loanProductId")&&StringUtils.isNotBlank(queryCond.get("loanProductId") + "")){
			sql.append(" and a.loanProductId = '").append(queryCond.get("loanProductId") + "").append("' ");
		}
		//sql.append(" and DATE_FORMAT( a.applyTime, '%Y%m' ) = DATE_FORMAT( CURDATE() , '%Y%m' ) ");
		return  sql.toString();
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 根据条件查询
	 * @Date 12:01 2018/9/13
	 * @Param [params]
	 * @return java.lang.String
	 **/
	private String createQueryWhereQuickQueryLoan(Map<String, Object> params) {
		Map<String, Object> queryCond = (Map<String, Object>) params.get("queryCond");
		StringBuffer sql = new StringBuffer();
		List<String> orgIds = (List<String>) queryCond.get("orgIds");
		if (orgIds != null && orgIds.size() > 0) {
			if (orgIds.size() == 1) {
				sql.append(" and t1.orgId='").append(orgIds.get(0)).append("' ");
			} else {
				sql.append(" and t1.orgId in (");
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
		if (queryCond.get("auditStatus") != null && StringUtils.isNotBlank(queryCond.get("auditStatus") + "")) {
			String[] arr = queryCond.get("auditStatus").toString().split(",");
			if (arr.length == 1) {
				sql.append(" and t1.status='").append(arr[0]).append("' ");
			} else {
				sql.append(" and t1.status in (");
				int last = arr.length - 1;
				int num = 0;
				for (String actionStatus : arr) {
					sql.append("'").append(actionStatus).append("'");
					if (num++ < last) {
						sql.append(",");
					}
				}
				sql.append(") ");
			}
		}

		if (queryCond.get("repaymentStatus") != null && StringUtils.isNotBlank(queryCond.get("repaymentStatus") + "")) {
			String[] arr = queryCond.get("repaymentStatus").toString().split(",");
			if (arr.length == 1) {
				if ("0".equals(arr[0])) {//未放款
					sql.append(" and t1.releaseType is null and t1.releaseDate is null");
				} else {//已放款
					sql.append(" and t1.releaseType is not null and t1.releaseDate is not null");
				}
			}
		}

		if (queryCond.get("actionStatus") != null && StringUtils.isNotBlank(queryCond.get("actionStatus") + "")) {
			String[] arr = queryCond.get("actionStatus").toString().split(",");
			if (arr.length == 1) {
				if ("0".equals(arr[0])) {
					sql.append(" and (t1.actionStatus = 0 or t1.actionStatus = 1)");
				} else {
					sql.append(" and t1.actionStatus = '").append(arr[0]).append("' ");
				}
			} else {
				sql.append(" and t1.actionStatus in (");
				int last = arr.length - 1;
				int num = 0;
				for (String status : arr) {
					if ("0".equals(status)) {
						sql.append("'").append(status).append("'").append(", '1'");
						;
					} else {
						sql.append("'").append(status).append("'");
					}
					if (num++ < last) {
						sql.append(",");
					}
				}
				sql.append(") ");
			}
		}

		if (queryCond.get("query_applyDate") != null && StringUtils.isNotBlank(queryCond.get("query_applyDate") + "")) {
			String[] applyDate = queryCond.get("query_applyDate").toString().split(" - ");
			sql.append(" and DATE_FORMAT(t1.applyTime, '%Y-%m-%d') >= '" + applyDate[0] + "'");
			sql.append(" and DATE_FORMAT(t1.applyTime, '%Y-%m-%d') <= '" + applyDate[1] + "'");
		}

		if (queryCond.get("query_releaseDate") != null && StringUtils.isNotBlank(queryCond.get("query_releaseDate") + "")) {
			String[] releaseDate = queryCond.get("query_releaseDate").toString().split(" - ");
			sql.append(" and DATE_FORMAT(t1.releaseDate, '%Y-%m-%d') >= '" + releaseDate[0] + "'");
			sql.append(" and DATE_FORMAT(t1.releaseDate, '%Y-%m-%d') <= '" + releaseDate[1] + "'");
		}

		if (queryCond.get("applyStartAmount") != null && StringUtils.isNotBlank(queryCond.get("applyStartAmount") + "")) {
			sql.append(" and t1.applyAmount >= ").append(queryCond.get("applyStartAmount") + "");
		}

		if (queryCond.get("applyEndAmount") != null && StringUtils.isNotBlank(queryCond.get("applyEndAmount") + "")) {
			sql.append(" and t1.applyAmount <= ").append(queryCond.get("applyEndAmount") + "");
		}

		if (queryCond.get("customerType") != null && StringUtils.isNotBlank(queryCond.get("customerType") + "")) {
			sql.append(" and t3.eType like '%").append(queryCond.get("customerType") + "").append("%' ");
		}

		if (queryCond.get("credentialType") != null && StringUtils.isNotBlank(queryCond.get("credentialType") + "")) {
			sql.append(" and t3.credentialType like '%").append(queryCond.get("credentialType") + "").append("%' ");
		}

		if (queryCond.get("query_credentialNo") != null && StringUtils.isNotBlank(queryCond.get("query_credentialNo") + "")) {
			sql.append(" and t3.credentialNo  like '%").append(queryCond.get("query_credentialNo").toString().trim() + "%' ");
		}

		if (queryCond.get("query_customerName") != null && StringUtils.isNotBlank(queryCond.get("query_customerName") + "")) {
			sql.append(" and t3.customerName like '%").append(queryCond.get("query_customerName").toString().trim()).append("%' ");
		}

		if (queryCond.get("query_mobilePhone") != null && StringUtils.isNotBlank(queryCond.get("query_mobilePhone") + "")) {
			sql.append(" and t3.mobilePhone like '%").append(queryCond.get("query_mobilePhone").toString().trim() + "%' ");
		}

		if (queryCond.get("query_status") != null && StringUtils.isNotBlank(queryCond.get("query_status") + "")) {
			sql.append(" and t0.taskStatus = '").append(queryCond.get("query_status").toString().trim()).append("' ");
		}

//		if ("00".equals(queryCond.get("roleType") + "")) {
//			sql.append(" and t1.accountManagerId = '").append(queryCond.get("userId").toString()).append("' ");
//		}
		sql.append(" and t1.accountManagerId = '").append(queryCond.get("userId").toString()).append("' ");

		//sql.append(" and (t1.status=2) or (t1.status=3) ");
		if (queryCond.get("loanProductId") != null && StringUtils.isNotBlank(queryCond.get("loanProductId") + "")) {
			String[] arr = queryCond.get("loanProductId").toString().split(",");
			if (arr.length == 1) {
				sql.append(" and t2.id = '").append(queryCond.get("loanProductId") + "").append("' ");
			} else {
				sql.append(" and t2.id in (");
				int last = arr.length - 1;
				int num = 0;
				for (String id : arr) {
					sql.append("'").append(id).append("'");
					if (num++ < last) {
						sql.append(",");
					}
				}
				sql.append(") ");
			}
		}

		return sql.toString();
	}

	/**
	 * @Author qipengpai
	 * @Description //TODO 确定查询进件列表还是信审列表
	 * @Date 12:00 2018/9/13
	 * @Param [queryCond]
	 * @return java.lang.String
	 **/
	private String appendCodition(Map<String,Object> queryCond) {
		StringBuffer sql = new StringBuffer();
		String taskId = queryCond.get("taskId").toString();
		if (!"queryQuickQueryLoan".equals(taskId)) {
			sql.append(" inner join(select * from act_loan_task_status a,(select MAX(id)as bid  from act_loan_task_status where taskId ='taskthree' GROUP BY loanAppid) b where a.id = b.bid and a.taskId ='taskthree' ORDER BY a.id asc) as t0 ON t1.id = t0.loanAppid ");

		}else {
			sql.append(" left join(select * from act_loan_task_status a,(select MAX(id)as bid  from act_loan_task_status where taskId in ('taskone','tasktwo')  GROUP BY loanAppid) b where a.id = b.bid and a.taskId in ('taskone','tasktwo')  ORDER BY a.id asc) as t0 ON t1.id = t0.loanAppid ");
//			if ("00".equals(queryCond.get("roleType") + "")) {
//				sql.append(" left join(select * from act_loan_task_status a,(select MAX(id)as bid  from act_loan_task_status where taskId in ('taskone','tasktwo')  GROUP BY loanAppid) b where a.id = b.bid and a.taskId in ('taskone','tasktwo')  ORDER BY a.id asc) as t0 ON t1.id = t0.loanAppid ");
//			}else{
//				sql.append(" inner join(select * from act_loan_task_status a,(select MAX(id)as bid  from act_loan_task_status where taskId in ('taskone','tasktwo')  GROUP BY loanAppid) b where a.id = b.bid and a.taskId in ('taskone','tasktwo')  ORDER BY a.id asc) as t0 ON t1.id = t0.loanAppid ");
//			}
		}
		sql.append(" where 1=1 ");
		return sql.toString();
	}
}
