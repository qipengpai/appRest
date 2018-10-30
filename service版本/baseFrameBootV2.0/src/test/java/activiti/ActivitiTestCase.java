package activiti;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.alibaba.fastjson.JSONObject;
import com.company.platform.BaseFrameBootApplication;
import com.company.platform.restapi.model.activiti.SubmitForApproval;
import com.company.platform.restapi.service.activiti.ActivitiService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =BaseFrameBootApplication.class )
public class ActivitiTestCase {
	
	@Autowired
	private  ActivitiService activitiService;
	/**
	 * 查詢待領取列表
	* @Title: getPerLeisureTask 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId 用戶id
	* @param @param realName 用戶真实姓名
	* @param @param start 起始条数,
	* @param @param end 查询多少条
	* @param @return    设定文件 
	* @return void  {count:"总数",list:"结果集"} 
	* @throws
	 */
	
	public void getPerLeisureTask(){
		Map<String,Object> result = activitiService.getPerLeisureTask("befba99b-c5e4-4b99-aad5-e69f86438488", "演示专用", 0, 10);
		System.out.println(JSONObject.toJSONString(result));
		
	}
	
    /**
     * 查询待办理列表
    * @Title: getPersonalTask 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userId 用戶id
	* @param @param realName 用戶真实姓名
	* @param @param start 起始条数,
	* @param @param end 查询多少条
    * @param @return    设定文件 
    * @return void  {count:"总数",list:"结果集"} 
    * @throws
     */

	public void getPersonalTask(){
		Map<String,Object> result = activitiService.getPersonalTask("befba99b-c5e4-4b99-aad5-e69f86438488", "演示专用", 0, 10);
		System.out.println(JSONObject.toJSONString(result));
		
	}
	
	/**
	 * 领取任务
	 * @Title: claim   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param taskId				节点ID
	 * @param: @param taskKey		                            节点定义key
	 * @param: @param userId				用户ID
	 * @param: @param realName				真实姓名
	 * @param: @return      
	 * @return: boolean true代表领取成功，false代表已经被领取了      
	 * @throws
	 */
	
	public void claim(){
		boolean result = activitiService.claim("1352934", "finishApply", "befba99b-c5e4-4b99-aad5-e69f86438488", "演示专用");
		System.out.println(result);
	}
	
	/**
	 * 
	* @Title: submittedForApprova 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param processInstanceId 流程实例id
	* @param taskId 任务id
	* @param variables 全局流程变量
	* @param localVariables 节点流程流程变量
	* @param comment 审批意见
	* @param audit 审核结果
	* @param auditUserId 审核人
	* @param auditUserName 审核人姓名
	* @param reason 退回拒绝原因
	* @param varJsonStr 按钮设置的流程变量
	* @param nextTaskKey 下一审核节点key值 
	* @param @return {code:"9999流程全部结束/9000拒绝/0000流程继续/9500/操作人与流程审批人不一致/9800/流程异常",message:"处理信息提示"} 
	* @return String    返回类型 
	* @throws
	 */
	@Test
	public void  submittedForApproval(){
		SubmitForApproval submit = new SubmitForApproval();
		/**
		 * 产品申请iD
		 */
		submit.setLoanProductApplyId("1455acb0-5f18-440a-8882-8d16746834dd");
		//流程id
		submit.setProcessInstanceId("1367523");
		//当亲流程审批节点id
		submit.setTaskId("1367538");
		//下一审核节点ID
		submit.setNextTaskKey("finishApply");
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("isFirstAgree", "agree");
		//全局流程变量
		submit.setVariables(variables);
//		//当前节点流程变量
//		submit.setLocalVariables("{noticeTemp=, noticeFlag=false}");
		//审批意见
		submit.setComment("junit测试接口审批");
		//审批结果
		submit.setAudit("agree");
		//退回/拒绝原因
		submit.setReason("");
		//当前审批人
		submit.setAuditUserId("cf4f29dd-1946-4942-b8ff-e634b69320b9");
		//审批人真实姓名
		submit.setAuditUserName("hsph");
		//下一节点 审批人id
		submit.setNextUserId(null);
		//下一节点审批人真实姓名
		submit.setNextUserName(null);
		Map<String,String> result = activitiService.submittedForApproval(submit);
		System.out.println(result);
	}

}
