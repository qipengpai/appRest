package com.company.platform.restapi.service.identityauth.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.platform.restapi.model.basicdata.ThirdResult;
import com.company.platform.restapi.service.identityauth.IdentityAuthService;
import com.company.platform.restapi.service.identityauth.ZXTService;


/** 
* @ClassName: IdentityAuthServiceImpl 
* @Description: TODO(姓名身份证大数据校验) 
* @author dongjian 
* @date 2018年5月17日 下午2:12:10 
*  
*/
@Service
public class IdentityAuthServiceImpl extends ZXTService<ThirdResult> implements IdentityAuthService {
	@Value("${zxt.secretId}")
	private String secretId;

	@Value("${zxt.secretKey}")
	private String secretKey;

	@Value("${zxt.serviceUrl}")
	private String serviceUrl;

	@Value("${zxt.aesKey}")
	private String aesKey;

	@PostConstruct
	public void init() {
		super.setSecretId(secretId);
		super.setSecretKey(secretKey);
		super.setServiceUrl(serviceUrl);
		super.setAesKey(aesKey);
	}

	@Override
	public ThirdResult callService(final String loanId, final String customerId, final Map<String, Object> parameter)
			throws Exception {
		// 构建查询参数
		final String paramsString = new JSONObject() {
			{
				put("name", parameter.get("customerName"));
				put("id_no", parameter.get("credentialNo"));
				put("protocol_data_type_name", new JSONArray() {
					{
						add("shenFenHeYan");
					}
				});
			}
		}.toJSONString();

		// 初始化请求
		String jsonString = this.initRequest(paramsString);

		return buildResult(paramsString, jsonString);
	}

	@Override
	public ThirdResult checkNameAndCard(Map<String, Object> params) throws Exception {

		return callService(null, null, params);
	}

}
