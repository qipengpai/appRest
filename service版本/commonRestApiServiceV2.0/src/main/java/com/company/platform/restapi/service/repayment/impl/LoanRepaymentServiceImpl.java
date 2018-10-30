package com.company.platform.restapi.service.repayment.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.company.platform.base.model.base.PageInfo;
import com.company.platform.base.util.TextFormatUtil;
import com.company.platform.restapi.model.loan.LoanEntryQuery;
import com.github.pagehelper.Page;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.model.base.BaseHttpParamsPageResp;
import com.company.platform.restapi.dao.loan.LoanProductApplyFeeMapper;
import com.company.platform.restapi.dao.loan.LoanProductApplyMapper;
import com.company.platform.restapi.dao.loan.LoanRepayFeeMapper;
import com.company.platform.restapi.dao.repayment.LoanPositiveMapper;
import com.company.platform.restapi.dao.repayment.LoanRepaymentMapper;
import com.company.platform.restapi.model.loan.LoanProductApply;
import com.company.platform.restapi.model.repayment.AppLoanRepaymentInfo;
import com.company.platform.restapi.model.repayment.GetRepayInfoListReq;
import com.company.platform.restapi.model.repayment.LoanProductApplyFee;
import com.company.platform.restapi.model.repayment.LoanRepayFee;
import com.company.platform.restapi.model.repayment.LoanRepayReduction;
import com.company.platform.restapi.model.repayment.LoanRepayment;
import com.company.platform.restapi.service.repayment.ILoanRepayReductionService;
import com.company.platform.restapi.service.repayment.ILoanRepaymentService;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;


@Service
public class LoanRepaymentServiceImpl implements ILoanRepaymentService {

    @Autowired
    private LoanRepaymentMapper loanRepaymentMapper;

    @Autowired
    private LoanProductApplyMapper loanProductApplyMapper;

    @Autowired
    private LoanRepayFeeMapper loanRepayFeeMapper;

    @Autowired
    private LoanProductApplyFeeMapper loanProductApplyFeeMapper;

    @Autowired
    private ILoanRepayReductionService loanRepayReductionService;

    @Autowired
    private LoanPositiveMapper loanPositiveMapper;

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;


    @Override
    public BaseHttpParamsPageResp queryRepaymentInfo(GetRepayInfoListReq getRepayInfoListReq) {
        String condition = getRepayInfoListReq.getCondition();
        PageHelper.startPage(getRepayInfoListReq.getPageNum(), getRepayInfoListReq.getPageSize());
        List<AppLoanRepaymentInfo> appLoanRepaymentInfo = new ArrayList<AppLoanRepaymentInfo>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("condition", condition);
        map.put("loanProductApplyId", getRepayInfoListReq.getLoanApplyId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (condition != null) {//当期
            appLoanRepaymentInfo = loanRepaymentMapper.getRepayInfo(map);

            for (AppLoanRepaymentInfo repayInfo : appLoanRepaymentInfo) {

                Map<String, BigDecimal> amountInfo = getAmountInfo_repayment(repayInfo.getId());//获取每期汇总信息

                repayInfo.setDuePenalty(amountInfo.get("duePenalty").toString());//应还罚息
                //repayInfo.setArrivalAmount(amountInfo.get("").toString());//到账金额=借款金额-放款时收取的费用
                repayInfo.setDueFeeAmount(amountInfo.get("feeAmount").toString());//应还总费用
                repayInfo.setDueTotalAmount(amountInfo.get("dueTotalAmount").toString());//应还总额
                repayInfo.setNeedPayTotalAmount(amountInfo.get("surplusTotalAmount").toString());//待还金额

                try {
                    Date date = sdf.parse(repayInfo.getDueRepayDate());
                    repayInfo.setDueRepayDate(sdf.format(date));//约定还款时间
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else if ("1".equals(condition)) {//已还完
            //appLoanRepaymentInfo = loanRepaymentMapper.getRepayInfo(map);
        } else if ("2".equals(condition)) {//未还完
            //appLoanRepaymentInfo = loanRepaymentMapper.getRepayInfo(map);
        }

        BaseHttpParamsPageResp baseHttpParamsPageResp = new BaseHttpParamsPageResp(appLoanRepaymentInfo, true);
        return baseHttpParamsPageResp;
    }

    @Override
    public Map<String, BigDecimal> getAmountInfo_repayment(String repaymentId) {

        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();

        //应还总本金
        BigDecimal duePrincipal = new BigDecimal(0);
        //应还总利息
        BigDecimal dueInterest = new BigDecimal(0);
        //应还总罚息
        BigDecimal penalty = new BigDecimal(0);
        //实还总罚息
        BigDecimal realPenalty = new BigDecimal(0);
        //实还总本金
        BigDecimal realPrincipal = new BigDecimal(0);
        //实还总利息
        BigDecimal realInterest = new BigDecimal(0);
        //实还总金额
        BigDecimal realTotalAmount = BigDecimal.ZERO;
        //放款时收取的总费用
        BigDecimal releaseFee = BigDecimal.ZERO;
        //总费用
        BigDecimal feeTotalAmount = BigDecimal.ZERO;
        //减免
        BigDecimal reduAmount = BigDecimal.ZERO;
        //冲正
        BigDecimal positiveAmount = BigDecimal.ZERO;
        //实还总费用
        BigDecimal realTotalFee = BigDecimal.ZERO;

        LoanRepayment loanRepayment = loanRepaymentMapper.selectById(repaymentId);

        LoanProductApply apply = loanProductApplyMapper.selectById(loanRepayment.getLoanProductApplyId());

        if (loanRepayment != null && apply != null) {
            if (loanRepayment.getDuePrincipal() != null) {
                duePrincipal = duePrincipal.add(loanRepayment.getDuePrincipal());
            }
            if (loanRepayment.getDueInterest() != null) {
                dueInterest = dueInterest.add(loanRepayment.getDueInterest());
            }
            if (loanRepayment.getPenalty() != null) {
                penalty = penalty.add(loanRepayment.getPenalty());
            }
            if (loanRepayment.getRealPenalty() != null) {
                realPenalty = realPenalty.add(loanRepayment.getRealPenalty());
            }
            if (loanRepayment.getRealPrincipal() != null) {
                realPrincipal = realPrincipal.add(loanRepayment.getRealPrincipal());
            }
            if (loanRepayment.getRealInterest() != null) {
                realInterest = realInterest.add(loanRepayment.getRealInterest());
            }
            if (loanRepayment.getRealTotalAmount() != null) {
                realTotalAmount = realTotalAmount.add(loanRepayment.getRealTotalAmount());
            }
        }

        //费用集合
        List<LoanRepayFee> loanRepayFeeList = loanRepayFeeMapper.selectLoanRepayFeeByRepayId(repaymentId);

        if (loanRepayFeeList.size() > 0) {
            for (LoanRepayFee loanRepayFee : loanRepayFeeList) {
                LoanProductApplyFee loanProductApplyFee = loanProductApplyFeeMapper.selectApplyFeeById(loanRepayFee.getLoanProductApplyFeeId());
                if (loanProductApplyFee != null) {
                    if ("1".equals(loanProductApplyFee.getChargeType())) {//放款时收取
                        if (loanProductApplyFee.getActualFeeAmount() == null) {
                            releaseFee = releaseFee.add(loanProductApplyFee.getFeeAmount());
                        } else {
                            releaseFee = releaseFee.add(loanProductApplyFee.getActualFeeAmount());
                        }
                    }
                    //总费用：各项费用的和（违约金、滞纳金需要特殊处理，这一期只要逾期就要计算该期的滞纳金；只有收取了违约金才计算进去）
                    if ("4".equals(loanProductApplyFee.getChargeType())) {//逾期收取
                        if (isOverRepayMent(loanProductApplyFee.getId())) {//该期逾期了
                            feeTotalAmount = feeTotalAmount.add(loanProductApplyFee.getActualFeeAmount());
                        }
                    }
                    if ("5".equals(loanProductApplyFee.getChargeType()) && loanProductApplyFee.getStatus() == 1) {
                        //违约收取 并且 付费标志为1的  0：未付费 1：已付费
                        feeTotalAmount = feeTotalAmount.add(loanProductApplyFee.getActualFeeAmount());
                    }
                    if (!"4".equals(loanProductApplyFee.getChargeType()) && !"5".equals(loanProductApplyFee.getChargeType())) {
                        feeTotalAmount = feeTotalAmount.add(loanProductApplyFee.getActualFeeAmount() == null ? loanProductApplyFee.getFeeAmount() : loanProductApplyFee.getActualFeeAmount());
                    }
                    realTotalFee = realTotalFee.add(loanRepayFee.getRealAmount() == null ? BigDecimal.ZERO : loanRepayFee.getRealAmount());//累加当期实还费用总金额
                }
            }
        }


        List<LoanRepayReduction> reduInfoList = loanRepayReductionService.selectRepayReduByRepayId(repaymentId);
        //获取减免列表信息
        if (null != reduInfoList && reduInfoList.size() > 0) {
            for (LoanRepayReduction redu : reduInfoList) {
                reduAmount = reduAmount.add(redu.getReductionAmount());
            }
        }

        //冲正
        if (loanRepayment.getPositiveState() != null && loanRepayment.getPositiveState() == 1) {//冲正标识 0：未冲正 1：已冲正
            List<Map<String, Object>> positiveList = loanPositiveMapper.selectPositiveList(loanRepayment.getId());
            for (Map<String, Object> positive : positiveList) {
                if ("in".equals((String) positive.get("positiveAmountState"))) {//in：+冲正金额 out：-冲正金额
                    positiveAmount = positiveAmount.add((BigDecimal) positive.get("positiveAmount"));
                } else {
                    positiveAmount = positiveAmount.subtract((BigDecimal) positive.get("positiveAmount"));
                }

            }
        }

        //应还总费用：各项费用的和（违约金、滞纳金需要特殊处理，这一期只要逾期就要计算该期的滞纳金；只有收取了违约金才计算进去）
        map.put("feeAmount", feeTotalAmount);

        //实还总费用
        map.put("realTotalFee", realTotalFee);

        //应还总金额 = 应还总本金 + 应还总利息 + 应还总罚息 + 应还总费用
        BigDecimal dueTotalAmount = duePrincipal.add(dueInterest).add(penalty).add(feeTotalAmount);
        map.put("dueTotalAmount", dueTotalAmount);

        //实还总金额
        map.put("realTotalAmount", realTotalAmount);

        //减免总金额
        map.put("reduAmount", reduAmount);

        //冲正总金额
        map.put("positiveTotalAmount", positiveAmount);

        //剩余总金额（元）

        if (apply.getActionStatus() == 3 || loanRepayment.getRepayStatus() == 1) {//如果整个借贷为已还完 或者 该期为已还完，那么该期剩余应还总金额为0
            map.put("surplusTotalAmount", BigDecimal.ZERO);
        } else {
            BigDecimal surplusTotalAmount = BigDecimal.ZERO;//应还总金额 - 实还总金额 - 减免总金额
            surplusTotalAmount = dueTotalAmount.subtract(realTotalAmount).subtract(reduAmount);
            map.put("surplusTotalAmount", surplusTotalAmount);
        }
        map.put("dueInterest", dueInterest);//应还利息
        map.put("duePrincipal", duePrincipal);//应还本金
        map.put("duePenalty", penalty);//应还罚息

        map.put("realInterest", realInterest);//实还利息
        map.put("realPrincipal", realPrincipal);//实还本金
        map.put("realPenalty", realPenalty);//实还罚息

        return map;
    }

    @Override
    public boolean isOverRepayMent(String loanProductApplyFeeId) {

        LoanRepayment repay = loanRepaymentMapper.selectLoanRepaymentByApplyFeeId(loanProductApplyFeeId);
        if (repay != null && repay.getPenalty() != null && repay.getPenalty().compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<LoanRepayment> selectByLoanProductApplyId(String applyId) {
        return loanRepaymentMapper.selectByLoanProductApplyId(applyId);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 查询 进件/信审 列表
     * @Date 20:00 2018/9/13
     * @Param [pageInfo, loanEntryQuery]
     * @return com.github.pagehelper.Page<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public Page<Map<String, Object>> selectQuickQueryLoan(PageInfo pageInfo, LoanEntryQuery loanEntryQuery) {
        if(StringUtils.isNotBlank(loanEntryQuery.getTaskStatus()) && "3".equals(loanEntryQuery.getTaskStatus())){
            loanEntryQuery.setTaskId("queryLoanCredit");
        }else if(StringUtils.isNotBlank(loanEntryQuery.getTaskStatus()) && "2".equals(loanEntryQuery.getTaskStatus())){
            loanEntryQuery.setQuery_status("0");
            loanEntryQuery.setTaskId("queryQuickQueryLoan");
        }else{
            loanEntryQuery.setTaskId("queryQuickQueryLoan");
        }

        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        //查询进件列表
        Page<Map<String, Object>> list = null;
        try {
            list = loanRepaymentMapper.selectQuickQueryLoan(objectToMap(loanEntryQuery));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fixData(list);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 进件,信审 数量明细查询
     * @Date 20:24 2018/9/13
     * @Param [userId]
     * @return com.company.platform.base.model.base.RespInfo
     **/
    @Override
    public Map<String, Object> getLoanEntryCreditNum(String userId) {
        //为后续增加 产品或者组织机预留
        Map<String, Object> queryCond =new HashMap<>();
        queryCond.put("userId",userId);
        //进件,信审 数量明细查询
        return loanRepaymentMapper.getLoanEntryCreditNum(queryCond);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 获取分数信息
     * @Date 21:11 2018/9/13
     * @Param [loanProductApplyId]
     * @return java.lang.String
     **/
    @Override
    public String selecCreditReusltScore(String loanProductApplyId) {
        return loanRepaymentMapper.selecCreditReusltScore(loanProductApplyId);
    }

    /**
     * @Author qipengpai
     * @Description //TODO 获取当前进件核验状态信息
     * @Date 21:16 2018/9/13
     * @Param [loanProductApplyId]
     * @return java.lang.String
     **/
    @Override
    public String selectIntoPieceResult(String loanProductApplyId) {
        return loanRepaymentMapper.selectIntoPieceResult(loanProductApplyId);
    }

    /**
     * @Author qipengpai
     * @Description //TODO  信审结果查询
     * @Date 21:49 2018/9/13
     * @Param [loanProductApplyId]
     * @return java.lang.String
     **/
    @Override
    public String selectCreditReuslt(String loanProductApplyId) {
        return loanRepaymentMapper.selectCreditReuslt(loanProductApplyId);
    }


    /**
     * @Author qipengpai
     * @Description //TODO 获取人工审核必要信息
     * @Date 20:29 2018/9/17
     * @Param [loanProductApplyId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public Map<String,Object>
    selectProInfo(String loanProductApplyId) {
        Map<String,Object> result = new LinkedHashMap<>();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(loanProductApplyId).singleResult();
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        result.put("exid",task.getExecutionId());
        result.put("taskid",task.getId());
        result.put("appid",loanProductApplyId);
        result.put("taskkey",task.getTaskDefinitionKey());
        result.put("type","loan");
        return result;
    }


    /**
     * @Author qipengpai
     * @Description //TODO 数据格式或形式处理
     * @Date 20:00 2018/9/13
     * @Param [list]
     * @return com.github.pagehelper.Page<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    private Page<Map<String,Object>> fixData(Page<Map<String,Object>> list) {
        if(null != list && !list.isEmpty()){
            for (Map<String, Object> map : list) {
                map.put("applyTime", TextFormatUtil.dateFormat((Date) map.get("applyTime"), "yyyy-MM-dd").replace("-","."));
                map.put("applyAmount", TextFormatUtil.numberFormat(map.get("applyAmount")));
                map.put("taskStatus", null == map.get("taskStatus") ? "3" : map.get("taskStatus"));
            }
        }
        return list;
    }


    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}
