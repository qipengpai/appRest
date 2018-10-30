package com.company.platform.restapi.model.loan;

import java.math.BigDecimal;

public class LoanEntryQuery {

    private String id;
    /**
     * 用户电话
     */
    private String query_mobilePhone;
    /**
     * 用户姓名
     */
    private String query_customerName;
    /**
     * 开始金额
     */
    private String applyStartAmount;
    /**
     * 结束金额
     */
    private String applyEndAmount;
    /**
     * 身份证号
     */
    private String query_credentialNo;

    /**
     * 查询状态
     */
    private String taskStatus;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 状态
     */
    private String query_status;

    /**
     * 状态
     */
    private String taskId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuery_mobilePhone() {
        return query_mobilePhone;
    }

    public void setQuery_mobilePhone(String query_mobilePhone) {
        this.query_mobilePhone = query_mobilePhone;
    }

    public String getQuery_customerName() {
        return query_customerName;
    }

    public void setQuery_customerName(String query_customerName) {
        this.query_customerName = query_customerName;
    }

    public String getApplyStartAmount() {
        return applyStartAmount;
    }

    public void setApplyStartAmount(String applyStartAmount) {
        this.applyStartAmount = applyStartAmount;
    }

    public String getApplyEndAmount() {
        return applyEndAmount;
    }

    public void setApplyEndAmount(String applyEndAmount) {
        this.applyEndAmount = applyEndAmount;
    }

    public String getQuery_credentialNo() {
        return query_credentialNo;
    }

    public void setQuery_credentialNo(String query_credentialNo) {
        this.query_credentialNo = query_credentialNo;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuery_status() {
        return query_status;
    }

    public void setQuery_status(String query_status) {
        this.query_status = query_status;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
