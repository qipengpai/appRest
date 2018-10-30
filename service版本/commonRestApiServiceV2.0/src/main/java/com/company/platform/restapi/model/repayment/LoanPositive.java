package com.company.platform.restapi.model.repayment;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoanPositive implements Serializable {

	
	/**
     * 冲正记录表
     */
    private static final long serialVersionUID = 5993409129562654280L;

    private String id;
	
	/**
	 * 冲正交易类型
	 */
	private String positiveType;
	
	/**
	 * 冲正金额
	 */
	private BigDecimal positiveAmount;
	
	/**
	 * in：+冲正金额 out：-冲正金额
	 */
	private String positiveAmountState;


    public String getId() {
        return id;
    }


    public String getPositiveType() {
        return positiveType;
    }


    public BigDecimal getPositiveAmount() {
        return positiveAmount;
    }


    public String getPositiveAmountState() {
        return positiveAmountState;
    }


    public void setId(String id) {
        this.id = id;
    }


    public void setPositiveType(String positiveType) {
        this.positiveType = positiveType;
    }


    public void setPositiveAmount(BigDecimal positiveAmount) {
        this.positiveAmount = positiveAmount;
    }


    public void setPositiveAmountState(String positiveAmountState) {
        this.positiveAmountState = positiveAmountState;
    }


}
