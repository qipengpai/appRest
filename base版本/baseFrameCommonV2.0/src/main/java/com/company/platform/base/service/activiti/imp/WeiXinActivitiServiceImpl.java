package com.company.platform.base.service.activiti.imp;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.dao.activiti.TaskSetManagerMapper;
import com.company.platform.base.model.activiti.FunctionInfo;
import com.company.platform.base.service.activiti.WeiXinActivitiService;
import com.company.platform.base.util.HttpClientUtil;
import com.company.platform.base.util.RenBaoUtil;

/**
 * 微信开启流程/审批接口
 * 
 * @ClassName: WeiXinActivitiService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2018年9月17日 下午5:07:23
 *
 */
@Service
public class WeiXinActivitiServiceImpl implements WeiXinActivitiService {

	@Value("${weixin.startActiviti}")
	private String startactiviti;
	@Value("${weixin.approveActiviti}")
	private String approveactiviti;
	@Value("${renbao.paccount}")
	private String paccount;
	@Value("${renbao.scode}")
	private String scode;
	@Value("${renbao.url}")
	private String url;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private TaskSetManagerMapper taskSetManagerMapper;
	@Autowired
	private RepositoryService repositoryService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, String> startActiviti(String loanProductApplyId, String nextTaskKey, String audit, String userId,
			String realname) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("loanProductApplyId", loanProductApplyId);
			params.put("nextTaskKey", nextTaskKey);
			params.put("audit", "submit");
			params.put("userId", userId);
			params.put("realname", realname);
			// 调用web端接口
			String res = HttpClientUtil.httpRequest(startactiviti, params, HttpClientUtil.POST);
			if (StringUtils.isNotBlank(res)) {
				Map<String, Object> child = JSONObject.parseObject(res);
				if (child.get("retCode").toString().equals("0000")) {
					result.put("retCode", "0000");
					result.put("retMsg", "操作成功！");
				} else if(child.get("retCode").toString().equals("9000")) {
					result.put("retCode", "9000");
					result.put("retMsg", "审批拒绝！");
				}else{
					result.put("retCode", "9999");
					result.put("retMsg", "操作失败！");
				}
			}

		} catch (Exception e) {
			result.put("retCode", "9999");
			result.put("retMsg", "流程启动错误！");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Map<String, String> approveActiviti(String userId, String loanProductApplyId, String comment, String audit,
			String varJsonStr, String functionName) {
		// 流程图
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceBusinessKey(loanProductApplyId).singleResult();
		// 节点
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

		Map<String, String> result = new HashMap<String, String>();
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("userId", userId);
			params.put("processInstanceId", processInstance.getProcessInstanceId());
			params.put("taskId", task.getId());
			params.put("audit", audit);// 按钮功能
			params.put("varJsonStr", varJsonStr);// 按钮变量{pass:'y'}
			params.put("noticeFlag", "false");
			params.put("noticeTemp", "");
			params.put("nextTaskKey", "");
			params.put("auditUser", "");
			params.put("reason", "");
			params.put("audit", "");// 按钮id
			params.put("processType", "loan");
			params.put("comment", comment);
			params.put("functionName", functionName);// 按钮状态或者功能组件submit
			// String param = "?"+getUrlParamsByMap(params);

			// 调用web端接口
			String res = HttpClientUtil.httpRequest(approveactiviti, params, HttpClientUtil.POST);
			if (StringUtils.isNotBlank(res)) {
				Map<String, Object> child = JSONObject.parseObject(res,Map.class);
				if (child.get("retCode").toString().equals("0000")) {
					result.put("retCode", "0000");
					result.put("retMsg", "操作成功！");
				} else if(child.get("retCode").toString().equals("9000")){
					result.put("retCode", "9000");
					result.put("retMsg", "审批拒绝！");
				}else if(child.get("retCode").toString().equals("9999")){
					result.put("retCode", "9999");
					result.put("retMsg", " 审批失败！");
				}else{
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.put("retCode", "9999");
			result.put("retMsg", "操作异常！");
		}
		return result;
	}

	@Override
	public List<FunctionInfo> getButton(String productId, String procdefKey) {
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(procdefKey)
				.latestVersion().singleResult();
		return taskSetManagerMapper.getButton(productId, pd.getId(), "tasktwo");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> checkYyssys(String customerName, String idCard, String phone) {

		Map<String, Object> result = new HashMap<String, Object>();
		// 请求参数
		LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
		param.put("idcard", idCard);
		param.put("mobile", phone);
		param.put("name", customerName);
		RenBaoUtil.coverKeyValue(param, paccount, scode);
		// 发送json请求
		String str0 = null;
		try {
			str0 = HttpClientUtil.httpRequest(url, param, HttpClientUtil.GET);
			this.logger.info("运营商三要素验证返回报文:" + str0);
			Map<String,Object>  json = JSONObject.parseObject(str0);
			if(StringUtils.isNotBlank(str0) && null != json && "0000".equals(json.get("resCode")+"")){
				Map<String,Object> status = (Map<String, Object>) json.get("data");
				if("2005".equals(status.get("statusCode")+"")){
					result.put("code", "y");
					result.put("message",  "(通过)"+status.get("statusMsg"));
				}else if("2006".equals(status.get("statusCode")+"")){
					result.put("code", "n");
					result.put("message", "(拒绝)"+status.get("statusMsg")+"");
				}else if("2007".equals(status.get("statusCode")+"") || "2013".equals(status.get("statusCode")+"") ){
//					result.put("code", "p");
//					result.put("message", "(人工复核)"+status.get("statusMsg")+"");
					result.put("code", "n");
					result.put("message", "【运营商三要素】调用失败，大数据平台系统内部错误，请人工复核并反馈系统管理员！");
				}else if("2012".equals(status.get("statusCode")+"")) {
					Map<String, Object> resultInfo = (Map<String, Object>)status.get("result");
					String type = resultInfo.get("state") + "";
					String code = "1".equals(type) ? "y" : "n"; //"2".equals(type) ? "n" : "3".equals(type) ? "p" : "n";
					result.put("code", code);
					result.put("message", "y".equals(code) ? "(通过)"+"客户基本信息核验一致。" : "(拒绝)"+"客户基本信息核验不一致。");//"n".equals(code) ? "(拒绝)"+"运营商三要素核验不一致！" : "(人工复核)"+"运营商三要素核验");
					this.logger.info("运营商三要素验证状态:" + type);
				}else{
					result.put("code", "n");
					result.put("message", "(拒绝)"+"户基本信息核验不一致。");
				}
			}else if(StringUtils.isNotBlank(str0) && null != json && json.get("resCode").toString().equals("1001")){
				result.put("code", "n");
				result.put("message", "【运营商三要素】调用失败，没有此接口访问权限，请人工复核并反馈系统管理员！");
				return result;
			}else if(StringUtils.isNotBlank(str0) && null != json && json.get("resCode").toString().equals("1005")){
				result.put("code", "n");
				result.put("message", "【运营商三要素】调用失败，请求参数为空或格式错误，请人工复核并反馈系统管理员！");
				return result;
			}else{
				result.put("code", "n");
				result.put("message", "(拒绝)"+"户基本信息核验不一致。");
				this.logger.info("运营商三要素接口返回值为"+json.get("resMsg")+"");
			}
		} catch (Exception e) {
			result.put("code", "n");
			result.put("message", "(拒绝)"+"运营商三要素查询异常！");
			this.logger.info("运营商三要素接口异常！");
			e.printStackTrace();
		}
		return result;
	}

}
