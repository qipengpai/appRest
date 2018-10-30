package com.company.platform.restapi.dao.repayment;

import java.util.Map;
import java.util.List;
import org.apache.commons.lang3.StringUtils;


public class LoanPositiveSqlProvider {
    
    
    
    public String unPositiveExcel(Map<String, Object> params) {
        StringBuffer sql = new StringBuffer();
        sql.append("select r.*,a.code,a.intoPieceNo,p.name,c.customerName,c.credentialType,c.credentialNo,c.eType ")
            .append("from loan_repayment r,loan_product_apply a,loan_product p,customer_public c ")
            .append("where r.loanProductApplyId=a.id and a.loanProductId=p.id and a.customerId=c.id ")
            .append("and r.extensionState=0 and r.repayStatus = 1 ")
            .append(this.createQueryWhere(params))
            .append("order by r.dueRepayDate,r.periodNum asc ");
        return sql.toString();
    }
    
    public String positiveExcel(Map<String, Object> params) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT r.*,a.code,a.intoPieceNo,p.name,c.customerName,c.credentialType,c.credentialNo,c.eType FROM loan_repayment r,")
            .append("loan_product_apply a,loan_product p,customer_public c WHERE r.loanProductApplyId=a.id AND a.loanProductId=p.id ")
            .append("AND a.customerId=c.id AND r.extensionState=0 AND r.repayStatus=1 ")
            .append(this.createQueryWhere(params))
            .append("order by r.realRepayDate desc ");
        return sql.toString();
    }
    
    private String createQueryWhere(Map<String, Object> queryCond) {
        StringBuffer sql = new StringBuffer();
        if(queryCond.get("positiveState") != null) {
            sql.append(" and r.positiveState = ");
            sql.append(queryCond.get("positiveState"));
        }

        if(queryCond.get("dateRangePicker_1") != null){
            
            String dateRangePicker_1 = queryCond.get("dateRangePicker_1").toString();
            if(dateRangePicker_1.contains(" - ")){
                String start = dateRangePicker_1.split(" - ")[0];
                String end = dateRangePicker_1.split(" - ")[1];
                sql.append(" and r.dueRepayDate between '").append(start).append("' and ").append("date_add('"+end+"',interval 1 day)");
            }
        }
        
        if(queryCond.get("dateRangePicker_2") != null){
            
            String dateRangePicker_2 = queryCond.get("dateRangePicker_2").toString();
            if(dateRangePicker_2.contains(" - ")){
                String start = dateRangePicker_2.split(" - ")[0];
                String end = dateRangePicker_2.split(" - ")[1];
                sql.append(" and r.realRepayDate between '").append(start).append("' and ").append("date_add('"+end+"',interval 1 day)");
            }
        }
        
        if(queryCond.get("query_credentialNo") != null && StringUtils.isNotBlank(queryCond.get("query_credentialNo")+"")) {
            sql.append(" and c.credentialNo like '%").append(queryCond.get("query_credentialNo")+"").append("%' ");
        }
        
        if(queryCond.get("query_customerType") != null && StringUtils.isNotBlank(queryCond.get("query_customerType")+"")) {
            sql.append(" and c.eType like '%").append(queryCond.get("query_customerType")+"").append("%' ");
        }
        
        if(queryCond.get("query_credentialType") != null && StringUtils.isNotBlank(queryCond.get("query_credentialType")+"")) {
            sql.append(" and c.credentialType like '%").append(queryCond.get("query_credentialType")+"").append("%' ");
        }
        
        if(queryCond.get("query_corCredentialType") != null && StringUtils.isNotBlank(queryCond.get("query_corCredentialType")+"")) {
            sql.append(" and c.credentialType like '%").append(queryCond.get("query_corCredentialType")+"").append("%' ");
        }
        
        if(queryCond.get("query_code") != null && StringUtils.isNotBlank(queryCond.get("query_code")+"")) {
            sql.append(" and a.intoPieceNo like '%").append(queryCond.get("query_code")+"").append("%' ");
        }
        if(queryCond.get("query_customerName") != null && StringUtils.isNotBlank(queryCond.get("query_customerName")+"")) {
            sql.append(" and c.customerName like '%").append(queryCond.get("query_customerName")+"").append("%' ");
        }
        if(queryCond.get("query_loanProductId") != null && StringUtils.isNotBlank(queryCond.get("query_loanProductId")+"")) {
            sql.append(" and p.id = '").append(queryCond.get("query_loanProductId")+"").append("' ");
        }
        
        List<String> orgIds = (List<String>)queryCond.get("orgIds");
        if(orgIds != null && orgIds.size() > 0) {
            sql.append(" and a.orgId in (");
            int i = 0;
            int last = orgIds.size()-1;
            for(String orgId : orgIds) {
                sql.append("'"+orgId+"'");
                if(i < last) {
                    sql.append(",");
                }
                queryCond.put("orgId" + i++, orgId);
            }
            sql.append(") ");
        }
        return sql.toString();
    }
    
}
