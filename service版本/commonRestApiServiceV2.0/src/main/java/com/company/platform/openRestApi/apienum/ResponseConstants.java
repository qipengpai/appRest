package com.company.platform.openRestApi.apienum;

/**
 * 类ResponseConstants.java的实现描述：TODO 第三方接口响应代码枚举
 *
 * @company		        北京中科博润科技股份有限公司
 * @author         lifeng@chinatech-br.com
 * @version        1.0
 * Date            2017年12月7日
 * @see            java.lang.Class
 * History:
 *    <author>     <time>      <version>      <desc>
 */
public enum ResponseConstants {
    /**
     * @Fields REQUEST_SUCCESS : TODO(请求调用成功)
     */
    ZXT_ERROR_101("101", "参数错误"),
    
    ZXT_ERROR_102("102", "数据源异常"),

    ZXT_ERROR_101001("101001", "参数-身份证-错误"),

    ZXT_ERROR_101002("101002", "参数-姓名-错误"),

    ZXT_ERROR_101003("101003", "参数-手机-错误"),

    ZXT_ERROR_101004("101004", "参数-银行卡-错误"),

    ZXT_ERROR_101005("101005", "参数-手机-虚拟号码段"),

    ZXT_ERROR_103001("103001", "帐号或密码错误"),

    ZXT_ERROR_103002("103002", "非正式帐号"),

    ZXT_ERROR_103003("103003", "帐号已被注销"),

    ZXT_ERROR_103004("103004", "帐号已被冻结"),

    ZXT_ERROR_103005("103005", "帐号已过期"),

    ZXT_ERROR_103006("103006", "非测试帐号"),

    ZXT_ERROR_104("104", "账户余额不足且无免费条数"),
    
    ZXT_ERROR_105("105", "账户没有订阅该服务"),
    
    ZXT_ERROR_106("106", "查询的服务暂不可用"),
    
    ZXT_ERROR_108("108", "服务暂时停用"),
    
    ZXT_ERROR_109("109", "账户余额不足且无测试条数"),
    
    ZXT_ERROR_110("110", "该样本调用次数已达上限"),
    
    ZXT_ERROR_111("111", "IP 受限"),
    
    ZXT_ERROR_200("200", "查询成功"),
    
    ZXT_ERROR_401("401", "内部错误"),
  
    /**
     * @Fields EXCEPTION : TODO(未知异常通用)
     */
    EXCEPTION("-1", "未知异常");

    private String retCode;

    private String retMsg;

    private ResponseConstants(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public static String getRetMsg(String retCode) {
        ResponseConstants[] responseConstants = ResponseConstants.values();
        for (ResponseConstants ret : responseConstants) {
            if (ret.getRetCode().equals(retCode)) {
                return ret.getRetMsg();
            }
        }
        return "";
    }
}
