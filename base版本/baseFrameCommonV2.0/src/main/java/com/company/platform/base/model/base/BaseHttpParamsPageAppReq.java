/******************************************************************
 *
 *    Package:     com.company.platform.base.model.base
 *
 *    Filename:    BaseHttpParamsPageAppReq.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2017
 *
 *    Company:     北京中科博润科技股份有限公司
 *
 *    @author:     zhengjn
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
import java.util.Map;

import javax.validation.GroupSequence;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.validated.First;
import com.company.platform.base.validated.Second;
import com.company.platform.base.validated.signTimestampValidated.SignTimestampStandardOrNot;
import com.company.platform.base.validated.signValidated.SignPages;

/**
 * @ClassName: BaseHttpParamsPageAppReq
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月8日 上午9:52:41
 */
@SuppressWarnings("serial")
@GroupSequence({First.class, Second.class, BaseHttpParamsPageAppReq.class })
@SignPages(groups = {Second.class })
public class BaseHttpParamsPageAppReq implements Serializable {

    /**
     * @Fields timestamp : TODO(时间，用于验签)
     */
    @SignTimestampStandardOrNot
    @JsonProperty("timestamp")
    @NotEmpty(message = "时间戳不能为空", groups = {First.class })
    private String timestamp;

    /**
     * @Fields nonce : TODO(随机数，用于验签)
     */
    @JsonProperty("nonce")
    @NotEmpty(message = "随机数不能为空", groups = {First.class })
    private String nonce;
    /**
     * @Field @sign : TODO(签名，用于验证防篡改)
     */
    @JsonProperty("sign")
    @NotEmpty(message = "验签不能为空", groups = {First.class })
    private String sign;
    // 当前页 写null不做分页
    @JsonProperty("pageNum")
    private Integer pageNum;
    // 每页的数量
    @JsonProperty("pageSize")
    private Integer pageSize = 10;

    // 查询条件
    private Map<String, Object> queryCond;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, Object> getQueryCond() {
        return queryCond;
    }

    public void setQueryCond(Map<String, Object> queryCond) {
        this.queryCond = queryCond;
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

}
