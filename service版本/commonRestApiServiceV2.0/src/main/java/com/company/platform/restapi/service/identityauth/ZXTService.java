package com.company.platform.restapi.service.identityauth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.company.platform.base.util.AESUtil;
import com.company.platform.restapi.model.basicdata.ThirdResult;


/** 
* @ClassName: ZXTService 
* @Description: TODO(增信通大数据校验) 
* @author dongjian 
* @date 2018年5月17日 下午2:12:55 
* 
* @param <T> 
*/
public abstract class ZXTService<T> extends ThirdAbsClass<T> {

	private String secretId;

	private String secretKey;

	private String serviceUrl;

	private String aesKey;

	@Autowired
	private RestTemplate restTemplate;

	public String getSecretId() {
		return secretId;
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public ZXTService() {
		/*
		 * this.setSecretId("anrunjinrong");
		 * this.setSecretKey("CUQUImdck6JGUDRI"); this.setServiceUrl(
		 * "http://apidata.credittone.com:51666/api/test/query");
		 * this.setAesKey("AaQcnmgYP2XdbDY1");
		 */
	}

	protected String initRequest(final String paramsString) throws Exception {
		ResponseEntity<String> resp = restTemplate.postForEntity(this.getServiceUrl(), new HashMap<String, String>() {
			{
				putAll(encryptParameter());
				put("params", AESUtil.encrypt(paramsString, getAesKey()));
			}
		}, String.class);

		return resp.getBody();

	}

	protected Map<String, String> encryptParameter() throws Exception {
		return new HashMap() {
			{
				put("username", getSecretId());
				put("password", AESUtil.encrypt(getSecretKey(), getAesKey()));
			}
		};
	}

	protected ThirdResult buildResult(String request, String response) {
		JSONObject json = JSONObject.parseObject(response);
		ThirdResult result = new ThirdResult();
		result.setId(json.getString("id"));
		result.setCode(json.getString("status_code"));
		result.setMessage(json.getString("msg"));
		result.setStatus("200".equals(result.getCode()));
		result.setData(json.getJSONObject("data"));
		result.setRequest(request);
		result.setResponse(response);
		return result;
	}

	protected ThirdResult getError(Map<String, Object> parameter) {
		ThirdResult result = new ThirdResult();
		if (parameter != null) {
			result.setRequest(JSONObject.toJSONString(parameter));
		}
		result.setStatus(false);
		result.setCode("9999");
		return result;
	}
}
