package com.company.platform.restapi.model.activiti;

import com.company.platform.base.model.base.BaseModel;

/**
 * 待领取任务
* @ClassName: WaitReceiveTask 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author ldy 
* @date 2018年7月10日 下午4:20:00 
*
 */
@SuppressWarnings("serial")
public class WaitReceiveTask extends BaseModel {
	//产品申请ID
	private String loanProductApplyId;
	//进件编号
	private	String	code;	
	//业务类型
	private	String	processTypeName;	
	//客户名称业务类型（value：借贷）
	private	String	customerName;	
	//证件号码
	private	String	credentialNo;	
	//产品名称
	private	String	productName;	
	//申请日期
	private	String	applyTime;	
	//当前节点
	private	String	taskName;	
	//办理人
	private	String	auditName;	
	//所属机构
	private	String	organizationName;	
	//流程类型key
	private String processDefinitionKey;
	//流程名称
	private	String	processDefinitionName;	
	//节点id
	private	String	taskId;	
	//节点key
	private	String	taskKey;	
	//流程实例id
	private	String	processInstanceId;	
	//业务类型（code：loan）
	private	String	processType;
	//业务经理ID
	private String registerId;
	/**   
	 * 产品申请ID   
	 * @return loanProductApplyId 产品申请ID   
	 */
	public String getLoanProductApplyId() {
		return loanProductApplyId;
	}
	
	/**   
	* 产品申请ID   
	* @param loanProductApplyId 产品申请ID   
	*/
	public void setLoanProductApplyId(String loanProductApplyId) {
		this.loanProductApplyId = loanProductApplyId;
	}
	
	/**   
	 * 进件编号   
	 * @return code 进件编号   
	 */
	public String getCode() {
		return code;
	}
	
	/**   
	* 进件编号   
	* @param code 进件编号   
	*/
	public void setCode(String code) {
		this.code = code;
	}
	
	/**   
	 * 业务类型   
	 * @return processTypeName 业务类型   
	 */
	public String getProcessTypeName() {
		return processTypeName;
	}
	
	/**   
	* 业务类型   
	* @param processTypeName 业务类型   
	*/
	public void setProcessTypeName(String processTypeName) {
		this.processTypeName = processTypeName;
	}
	
	/**   
	 * 客户名称业务类型（value：借贷）   
	 * @return customerName 客户名称业务类型（value：借贷）   
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**   
	* 客户名称业务类型（value：借贷）   
	* @param customerName 客户名称业务类型（value：借贷）   
	*/
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**   
	 * 证件号码   
	 * @return credentialNo 证件号码   
	 */
	public String getCredentialNo() {
		return credentialNo;
	}
	
	/**   
	* 证件号码   
	* @param credentialNo 证件号码   
	*/
	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}
	
	/**   
	 * 产品名称   
	 * @return productName 产品名称   
	 */
	public String getProductName() {
		return productName;
	}
	
	/**   
	* 产品名称   
	* @param productName 产品名称   
	*/
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**   
	 * 申请日期   
	 * @return applyTime 申请日期   
	 */
	public String getApplyTime() {
		return applyTime;
	}
	
	/**   
	* 申请日期   
	* @param applyTime 申请日期   
	*/
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	
	/**   
	 * 当前节点   
	 * @return taskName 当前节点   
	 */
	public String getTaskName() {
		return taskName;
	}
	
	/**   
	* 当前节点   
	* @param taskName 当前节点   
	*/
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	/**   
	 * 办理人   
	 * @return auditName 办理人   
	 */
	public String getAuditName() {
		return auditName;
	}
	
	/**   
	* 办理人   
	* @param auditName 办理人   
	*/
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	
	/**   
	 * 所属机构   
	 * @return organizationName 所属机构   
	 */
	public String getOrganizationName() {
		return organizationName;
	}
	
	/**   
	* 所属机构   
	* @param organizationName 所属机构   
	*/
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	/**   
	 * 流程名称   
	 * @return processDefinitionName 流程名称   
	 */
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}
	
	/**   
	* 流程名称   
	* @param processDefinitionName 流程名称   
	*/
	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}
	
	/**   
	 * 节点id   
	 * @return taskId 节点id   
	 */
	public String getTaskId() {
		return taskId;
	}
	
	/**   
	* 节点id   
	* @param taskId 节点id   
	*/
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	/**   
	 * 节点key   
	 * @return taskKey 节点key   
	 */
	public String getTaskKey() {
		return taskKey;
	}
	
	/**   
	* 节点key   
	* @param taskKey 节点key   
	*/
	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}
	
	/**   
	 * 流程实例id   
	 * @return processInstanceId 流程实例id   
	 */
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	
	/**   
	* 流程实例id   
	* @param processInstanceId 流程实例id   
	*/
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	/**   
	 * 业务类型（code：loan）   
	 * @return processType 业务类型（code：loan）   
	 */
	public String getProcessType() {
		return processType;
	}
	
	/**   
	* 业务类型（code：loan）   
	* @param processType 业务类型（code：loan）   
	*/
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	
	/**   
	 * 业务经理ID   
	 * @return registerId 业务经理ID   
	 */
	public String getRegisterId() {
		return registerId;
	}
	
	/**   
	* 业务经理ID   
	* @param registerId 业务经理ID   
	*/
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	
	
	
	
	
}
