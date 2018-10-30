package com.company.platform.restapi.service.repayment.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.restapi.dao.repayment.LoanRepayReductionMapper;
import com.company.platform.restapi.model.repayment.LoanRepayReduction;
import com.company.platform.restapi.service.repayment.ILoanRepayReductionService;

@Service
public class LoanRepayReductionServiceImpl implements ILoanRepayReductionService {

    @Autowired
    private LoanRepayReductionMapper loanRepayReductionMapper;
    

    @Override
    public LoanRepayReduction selectRepayReduction(LoanRepayReduction loanRepayReduction) {
        LoanRepayReduction reduction = loanRepayReductionMapper.selectReductionInfo(loanRepayReduction);
        return reduction;
    }
    
    @Override
    public LoanRepayReduction selectRepayReduction_part(LoanRepayReduction loanRepayReduction) {
        List<LoanRepayReduction> reductionList = loanRepayReductionMapper.selectReductionInfo_part(loanRepayReduction);
        if(reductionList.size()>0){
        	return reductionList.get(0);
        }
        return null;
    }
    
    @Override
    public LoanRepayReduction queryRepayReduction(LoanRepayReduction loanRepayReduction) {
        LoanRepayReduction reduction = loanRepayReductionMapper.selectReduction(loanRepayReduction);
        return reduction;
    }

    @Override
    public List<LoanRepayReduction> splitTotalReduAmount(LoanRepayReduction loanRepayReduction) {
        loanRepayReduction.setReductionType("0");//减免类型，对应字典表reductionType 0:减免总金额
        LoanRepayReduction reduction = loanRepayReductionMapper.selectReductionInfo(loanRepayReduction);
        BigDecimal totalAmount = reduction.getReductionAmount();
        
        //减免顺序，1.费用  2.违约金  3.滞纳金  4.罚息  5.利息  6.本金
        
        BigDecimal totalFee = BigDecimal.ZERO;
        if(totalAmount.compareTo(totalFee)>0){
            totalAmount = totalAmount.subtract(totalFee);//剩余减免总金额
        }else{
            totalFee = totalFee.subtract(totalAmount);//剩余费用金额
        }
        
        return null;
    }

    @Override
    public List<LoanRepayReduction> selectRepayReductionList(String loanProductApplyId, String businessId, int businessType) {
        List<LoanRepayReduction> loanRepayList = loanRepayReductionMapper.selectRepayReductionList(loanProductApplyId,
                businessId, businessType);
        return loanRepayList;
    }
    
    @Override
    public List<LoanRepayReduction> selectRepayReduByApplyId(String loanProductApplyId){
        List<LoanRepayReduction> loanRepayList = loanRepayReductionMapper.selectRepayReduByApplyId(loanProductApplyId);
        return loanRepayList;
    }
    
    @Override
    public List<LoanRepayReduction> selectRepayReduByRepayId(String repayId){
        List<LoanRepayReduction> loanRepay = loanRepayReductionMapper.selectRepayReduByRepayId(repayId);
        return loanRepay;
    }

/*    @Override
    public List<Map<String, Object>> selectRepayReduByPlanId(String loanRepaymentId) {
    	 List<Map<String, Object>> loanReductions = loanRepayReductionMapper.selectRepayReduByPlanId(loanRepaymentId);
    	 Map<String, String> reductionType = dictUtil.getDictDataByType("reductionType");
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 if (null != loanReductions && loanReductions.size() > 0) {
        	 for (Map<String, Object> redu : loanReductions) {
        		 redu.put("reductionType", reductionType.get(redu.get("reductionType").toString()));
        		 redu.put("reductionAmount", TextFormatUtil.numberFormat(redu.get("reductionAmount")));
        		 redu.put("reductionTime", sdf.format(redu.get("reductionTime")));
             }
         }
    	 return loanReductions;
    }*/
    
    @Override
    public List<LoanRepayReduction> selectReductionListByRedu(LoanRepayReduction loanRepayReduction){
        List<LoanRepayReduction> loanRepayList = loanRepayReductionMapper.selectReductionList(loanRepayReduction);
        return loanRepayList;
    }
    
    @Override
    public List<LoanRepayReduction> selectReduByRepayId(String loanRepaymentId) {
    	List<LoanRepayReduction> loanReductionList = loanRepayReductionMapper.selectReduByRepayId(loanRepaymentId);
    	 return loanReductionList;
    }
    
    @Override
    public List<LoanRepayReduction> selectByBussinessId(String bussinessId) {
        return loanRepayReductionMapper.selectByBussinessId(bussinessId);
    }

}
