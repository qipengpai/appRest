/******************************************************************
 *
 *    Package:     com.zkbr.platform.entity.common
 *
 *    Filename:    BaseHttpParamsResp.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2016-2017
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     Administrator
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年3月27日 下午5:33:58
 *
 *    Revision:
 *
 *    2017年3月27日 下午5:33:58
 *        - first revision
 *
 *****************************************************************/
package com.company.platform.base.model.base;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.company.platform.base.baseenum.GLOBALCONFIG;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.util.DateUtil;
import com.company.platform.base.util.RandomUtil;
import com.company.platform.base.util.SHAHelper;
import com.github.pagehelper.Page;

/**
 * @ClassName BaseHttpParamsResp
 * @Description TODO(响应报文的JSON实体类)
 * @author Administrator
 * @Date 2017年9月18日 下午5:33:58
 * @version 1.0.0
 * @param <T>
 */
@SuppressWarnings({"rawtypes", "serial" })
public class BaseHttpParamsPageResp implements Serializable {

    /**
     * 标识是否成功调用 true 成功 false 失败
     */
    @JsonProperty("exceptionFlag")
    private String exceptionFlag;
    /**
     * @Fields responseCode : TODO(响应状态码)
     */
    @JsonProperty("responseCode")
    private String responseCode;

    /**
     * @Fields responseMessage : TODO(响应码描述)
     */
    @JsonProperty("responseMessage")
    private String responseMessage;

    /**
     * @Fields timestamp : TODO(时间，用于验签)
     */
    @JsonProperty("timestamp")
    private String timestamp;

    /**
     * @Fields nonce : TODO(随机数，用于验签)
     */
    @JsonProperty("nonce")
    private String nonce;
    /**
     * @Field @sign : TODO(签名，用于验证防篡改)
     */
    @JsonProperty("sign")
    private String sign;
    // 当前页
    @JsonProperty("pageNum")
    private String pageNum;
    // 每页的数量
    @JsonProperty("pageSize")
    private String pageSize;
    // 总记录数
    @JsonProperty("total")
    private String total;
    // 总页数
    @JsonProperty("pages")
    private String pages;
    // 是否为第一页
    @JsonProperty("isFirstPage")
    private String isFirstPage = "false";
    // 是否为最后一页
    @JsonProperty("isLastPage")
    private String isLastPage = "false";
    // 结果集
    @JsonProperty("resultSet")
    private List resultSet;

    public BaseHttpParamsPageResp() {
    }

    /** 
    * <p>Title: </p> 
    * <p>Description: </p> 
    * @param resultSet
    * @param p true分页 false不分页
    */
    public BaseHttpParamsPageResp(List resultSet, boolean p) {
    	if(p) {
    		if (resultSet instanceof Page) {
    			Page page = (Page) resultSet;
    			this.pageNum = String.valueOf(page.getPageNum());
    			this.pageSize = String.valueOf(page.getPageSize());
    			this.pages = String.valueOf(page.getPages());
    			this.total = String.valueOf(page.getTotal());
    			this.resultSet = resultSet;
    		} else if (resultSet instanceof List) {
    			this.pageNum = "1";
    			this.pageSize = String.valueOf(resultSet.size());
    			this.pages = "1";
    			this.resultSet = resultSet;
    			this.total = String.valueOf(resultSet.size());
    		}
    		if (resultSet instanceof List) {
    			this.exceptionFlag = ResponseConstants.REQUEST_SUCCESS.getRetCode();
    			this.responseCode = ResponseConstants.SUCCESS.getRetCode();
    			// 判断页面边界
    			judgePageBoudary();
    		}
    	} else {
    		this.exceptionFlag = ResponseConstants.REQUEST_SUCCESS.getRetCode();
			this.responseCode = ResponseConstants.SUCCESS.getRetCode();
    		this.resultSet = resultSet;
    		isFirstPage = null;
    		isLastPage = null;
    	}
    }

    // 生成验签
    public void createSign() {
        String Nonce = String.valueOf(RandomUtil.getRandomDigit(8));
        String Timestamp = String.valueOf(DateUtil.getCurrentTimeMillis());
        this.setNonce(Nonce);// 设置随机数
        this.setTimestamp(Timestamp);// 设置毫秒数
        this.setSign(SHAHelper.SHA1(GLOBALCONFIG.APPKEY + Timestamp + Nonce + JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect)));
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        isFirstPage = String.valueOf(pageNum.equals("1"));
        isLastPage = String.valueOf(pageNum.equals(pages));
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = String.valueOf(pageNum);
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = String.valueOf(pageSize);
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = String.valueOf(total);
    }

    public String getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = String.valueOf(pages);
    }

    public List getResultSet() {
        return resultSet;
    }

    public void setResultSet(List resultSet) {
        this.resultSet = resultSet;
    }

    public String getIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = String.valueOf(isFirstPage);
    }

    public String getIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = String.valueOf(isLastPage);
    }

    public String getExceptionFlag() {
        return exceptionFlag;
    }

    public void setExceptionFlag(String exceptionFlag) {
        this.exceptionFlag = exceptionFlag;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
