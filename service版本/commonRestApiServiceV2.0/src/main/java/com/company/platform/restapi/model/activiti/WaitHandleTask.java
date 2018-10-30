package com.company.platform.restapi.model.activiti;

import com.company.platform.base.model.base.BaseModel;
import com.company.platform.restapi.model.loan.deal.LoanApplyAuditingInfo;

/**
 * 待办理任务
* @ClassName: WaitHandleTask 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2018年7月11日 下午2:11:18 
*
 */
@SuppressWarnings("serial")
public class WaitHandleTask extends BaseModel {
	// 进件编号
	private String code;
	// 业务类型（value:借贷）
	private String processTypeName;
	// 客户名称
	private String customerName;
	// 证件号码
	private String credentialNo;
	// 产品名称
	private String productName;
	// 申请日期
	private String applyTime;
	// 当前节点
	private String taskName;
	// 办理人
	private String auditName;
	// 所属机构
	private String organizationName;
	// 流程类型key
	private String processDefinitionKey;
	// 流程类型名称
	private String processDefinitionName;
	// 任务创建时间
	private String creatTime;
	// 节点id
	private String taskId;
	// 节点key
	private String taskKey;
	// 流程实例id
	private String processInstanceId;
	// 业务类型（code：loan）
	private String processType;
	// 借贷申请id
	private String businessKey;
	// 借贷申请详情
	private LoanApplyAuditingInfo applyDetail;

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
	 * 业务类型（value:借贷）   
	 * @return processTypeName 业务类型（value:借贷）   
	 */
	public String getProcessTypeName() {
		return processTypeName;
	}

	/**   
	* 业务类型（value:借贷）   
	* @param processTypeName 业务类型（value:借贷）   
	*/
	public void setProcessTypeName(String processTypeName) {
		this.processTypeName = processTypeName;
	}

	/**   
	 * 客户名称   
	 * @return customerName 客户名称   
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**   
	* 客户名称   
	* @param customerName 客户名称   
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
	 * 流程类型   
	 * @return processDefinitionName 流程类型   
	 */
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	/**   
	* 流程类型   
	* @param processDefinitionName 流程类型   
	*/
	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	/**   
	 * 任务创建时间   
	 * @return creatTime 任务创建时间   
	 */
	public String getCreatTime() {
		return creatTime;
	}

	/**   
	* 任务创建时间   
	* @param creatTime 任务创建时间   
	*/
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
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
	 * 借贷申请id   
	 * @return businessKey 借贷申请id   
	 */
	public String getBusinessKey() {
		return businessKey;
	}

	/**   
	* 借贷申请id   
	* @param businessKey 借贷申请id   
	*/
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	/**   
	 * 借贷申请详情   
	 * @return applyDetail 借贷申请详情   
	 */
	public LoanApplyAuditingInfo getApplyDetail() {
		return applyDetail;
	}

	/**   
	* 借贷申请详情   
	* @param applyDetail 借贷申请详情   
	*/
	public void setApplyDetail(LoanApplyAuditingInfo applyDetail) {
		this.applyDetail = applyDetail;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

}
