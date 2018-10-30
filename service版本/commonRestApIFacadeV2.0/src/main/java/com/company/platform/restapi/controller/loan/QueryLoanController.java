package com.company.platform.restapi.controller.loan;

import com.company.platform.base.aop.requestAccess.RequestAccessAnnotation;
import com.company.platform.base.baseenum.RequestAccessConstants;
import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.controller.BaseController;
import com.company.platform.base.model.base.*;
import com.company.platform.base.service.activiti.WeiXinActivitiService;
import com.company.platform.restapi.model.loan.LoanEntryQuery;
import com.company.platform.restapi.model.loan.LoanProductApply;
import com.company.platform.restapi.model.loan.deal.LoanRepaymentTrailInfo;
import com.company.platform.restapi.model.loan.deal.LoanRepaymentTrialResp;
import com.company.platform.restapi.model.loan.handled.ImageDataInfo;
import com.company.platform.restapi.model.modelmanager.ImgAndDocTitle;
import com.company.platform.restapi.model.repayment.LoanRepayment;
import com.company.platform.restapi.service.loan.ILoanDealService;
import com.company.platform.restapi.service.loan.ILoanProductApplyService;
import com.company.platform.restapi.service.modelmanager.IImgAndDocModelService;
import com.company.platform.restapi.service.repayment.ILoanRepaymentService;
import com.company.platform.restapi.validated.loan.LoanDealValidate;
import com.company.platform.security.service.WeixinCustomUserDetailsService;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wechat")
public class QueryLoanController extends BaseController {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(QueryLoanController.class);

    @Autowired
    private ILoanRepaymentService loanRepaymentService;
    @Autowired
    private WeixinCustomUserDetailsService weixinCustomUserDetailsService;
    @Autowired
    private ILoanProductApplyService loanProductApplyService;
    @Autowired
    private ILoanDealService loanDealService;
    @Autowired
    private WeiXinActivitiService weiXinActivitiService;
	@Autowired
	private IImgAndDocModelService imgAndDocModelService;

    /**
     * @Author qipengpai
     * @Description //TODO 进件,信审 数量明细查询
     * @Date 20:28 2018/9/13
     * @Param [userId]
     * @return com.company.platform.base.model.base.RespInfo
     **/
    @RequestMapping(path = "/getLoanEntryCreditNum", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @RequestAccessAnnotation(modelName = "进件,信审 数量", modelType = RequestAccessConstants.SEARCH, desc = "查询进件进件,信审 数量")
    public RespInfo getLoanEntryCreditNum(String userId){
        //进件,信审 数量明细查询
        Map<String,Object> map = loanRepaymentService.getLoanEntryCreditNum(userId);
        RespInfo respInfo = new RespInfo(map,"进件,信审 数量明细查询成功");
        return respInfo;
    }


    /**
     * @Author qipengpai
     * @Description //TODO 查询进件列表
     * @Date 11:18 2018/9/13
     * @Param [baseHttpParamsPageAppReq]
     * @return com.company.platform.base.model.base.BaseHttpParamsPageResp
     **/
    @RequestMapping(path = "/queryLoanEntry", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @RequestAccessAnnotation(modelName = "进件列表", modelType = RequestAccessConstants.SEARCH, desc = "查询进件列表")
    public PageInfo queryQuickQueryLoan(PageInfo pageInfo,LoanEntryQuery loanEntryQuery,String userId){
        loanEntryQuery.setUserId(userId);
        //查询进件列表
        Page<Map<String,Object>> resp = loanRepaymentService.selectQuickQueryLoan(pageInfo,loanEntryQuery);
        PageInfo<Map<String,Object>> pageInfos = new PageInfo<Map<String,Object>>(resp);
        pageInfos.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
        pageInfos.setResponseCode(ResponseConstants.SUCCESS.getRetCode());
        pageInfos.setResponseMessage("进件列表查询成功");
        return pageInfos;
    }


    /**
     * @Author qipengpai
     * @Description //TODO 查询信审列表
     * @Date 20:03 2018/9/13
     * @Param [baseHttpParamsPageAppReq]
     * @return com.company.platform.base.model.base.BaseHttpParamsPageResp
     **/
    @RequestMapping(path = "/queryLoanCredit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @RequestAccessAnnotation(modelName = "信审列表", modelType = RequestAccessConstants.SEARCH, desc = "查询信审列表")
    public PageInfo queryCreditLoan(PageInfo pageInfo,LoanEntryQuery loanEntryQuery,String userId){
        loanEntryQuery.setUserId(userId);
        loanEntryQuery.setTaskId("queryLoanCredit");
        //查询信审列表
        Page<Map<String,Object>> resp = loanRepaymentService.selectQuickQueryLoan(pageInfo,loanEntryQuery);
        PageInfo<Map<String,Object>> pageInfos = new PageInfo<Map<String,Object>>(resp);
        pageInfos.setExceptionFlag(ResponseConstants.REQUEST_SUCCESS.getRetCode());
        pageInfos.setResponseCode(ResponseConstants.SUCCESS.getRetCode());
        pageInfos.setResponseMessage("信审列表查询成功");
        return pageInfos;
    }

    /**
     * @Author qipengpai
     * @Description //TODO 查询信审结果及分数
     * @Date 11:29 2018/9/14
     * @Param [loanProductApplyId]
     * @return com.company.platform.base.model.base.RespInfo
     **/
    @RequestMapping(path = "/queryCreditResult", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @RequestAccessAnnotation(modelName = "信审结果及分数", modelType = RequestAccessConstants.SEARCH, desc = "信审结果及分数")
    public RespInfo creditResult(String loanProductApplyId) {
        RespInfo respInfo =null;
        try {
            Map<String, Object> retData = new HashMap<String, Object>();
            //获取当前进件核验状态信息
            String intoPieceResult = loanRepaymentService.selectIntoPieceResult(loanProductApplyId);
            if (StringUtils.isBlank(intoPieceResult))
                return  new RespInfo("0000","99999999","进件,信审 数量明细查询异常");
            if("1".equals(intoPieceResult)){
                retData.put("intoPieceResult", "1");
                //信审结果查询
                retData.put("creditResult", "1".equals(loanRepaymentService.selectCreditReuslt(loanProductApplyId)) ? "1" : "2");
                //获取数据分数信息
                String scoreNum = loanRepaymentService.selecCreditReusltScore(loanProductApplyId);
                retData.put("score",StringUtils.isNotBlank(scoreNum) ? scoreNum.substring(0,scoreNum.lastIndexOf(".")) : "0");
            }else{
                retData.put("intoPieceResult", "0".equals(intoPieceResult) ? "0" : "2" );
                //获取拒绝原因（人工审核原因，该原因只针对于核验结果）
                retData.put("message", loanProductApplyService.getApprovalLog(loanProductApplyId).get("message"));
            }
            respInfo = new RespInfo(retData,"进件,信审 数量明细查询成功");
        } catch(Exception e) {
            e.printStackTrace();
            logger.error("", e);
        }
        return respInfo;
    }


    /**
     * @Author qipengpai
     * @Description //TODO 删除未提交进件信息
     * @Date 11:00 2018/9/14
     * @Param [loanProductApplyId]
     * @return SimpleAjaxResult
     **/
    @RequestMapping(path = "/deleteProductApply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @RequestAccessAnnotation(modelName = "删除未提交进件信息", modelType = RequestAccessConstants.SEARCH, desc = "删除未提交进件信息")
    public RespInfo deleteProductApply(String loanProductApplyId) {
        RespInfo respInfo = null;
        try {
            //通过借贷申请id查询借贷申请信息
            LoanProductApply loanProductApply = loanProductApplyService.selectLoanProductApplyById(loanProductApplyId);
            if(loanProductApply != null){
                //删除未提交进件信息
                respInfo =new RespInfo("0000","00000000", loanProductApplyService.deleteApply(loanProductApplyId) ? "删除成功" : "删除异常");
            }else{
                respInfo =new RespInfo("0000","99999999", "不存在该进件");
            }
        } catch(Exception e) {
            logger.error("", e);
            respInfo =new RespInfo("9999", "请求异常");
        }
        return respInfo;
    }



    /*@RequestMapping(path = "/queryLoanApplyDetail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @RequestAccessAnnotation(modelName = "进件/信审 详情", modelType = RequestAccessConstants.SEARCH, desc = "进件/信审 详情（带业务模型及数据，影像模型及数据）")
    public RespInfo queryLoanApplyDetail(String loanProductApplyId, String taskKey, String isCurrTask, String operType) {
        RespInfo respInfo = null;
        Map<String, Object> map =new HashMap<>();
        try {
            if (StringUtils.isNotBlank(loanProductApplyId) && (StringUtils.isNotBlank(taskKey) || "0".equals(isCurrTask) || "1".equals(isCurrTask))) {
                //进件/信审 详情（带业务模型及数据，影像模型及数据）
                map = loanProductApplyService.queryLoanApplyDetail(loanProductApplyId, taskKey, isCurrTask);
            }
             respInfo =new RespInfo(map, "不存在该进件");
        } catch(Exception e) {
            logger.error("", e);
            respInfo =new RespInfo("9999", "请求异常");
        }
        return respInfo;
    }*/


    /**
     * @Author qipengpai
     * @Description //TODO 获取还款计划 （本月应还金额）
     * @Date 13:05 2018/9/17
     * @Param [loanRepaymentTrailInfo]
     * @return com.company.platform.base.model.base.RespInfo
     **/
    @RequestMapping(path = "/getRepayment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @RequestAccessAnnotation(modelName = "获取还款计划", modelType = RequestAccessConstants.SEARCH, desc = "获取还款计划")
    public RespInfo getRepayment(LoanRepaymentTrailInfo loanRepaymentTrailInfo) {
        // 数据校验
        //loanDealValidate.loanRepaymentTrailValidate(loanRepaymentTrailInfo);
        RespInfo respInfo = null;
        try {
            // 获取贷款申请费用信息
            LoanRepaymentTrialResp resp = loanDealService.getRepaymentTrialNoValidate(loanRepaymentTrailInfo);
            Map<String,String> map = new HashMap<String,String>();
            map.put("mouthAmount",null == resp ? null : resp.getRepaymentList().get(0).getAmount());
            respInfo =new RespInfo(map, "获取还款计划中每月应还金额");
        } catch(Exception e) {
            logger.error("", e);
            respInfo =new RespInfo("9999", "请求异常");
        }
        return respInfo;
    }

    /**
     * @Author qipengpai
     * @Description //TODO 获取人工审核必要信息
     * @Date 13:05 2018/9/17
     * @Param [loanRepaymentTrailInfo]
     * @return com.company.platform.base.model.base.RespInfo
     **/
    @RequestMapping(path = "/getProInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @RequestAccessAnnotation(modelName = "获取人工审核必要信息", modelType = RequestAccessConstants.SEARCH, desc = "获取人工审核必要信息")
    public RespInfo getProInfo(String loanProductApplyId) {
        RespInfo respInfo = null;
        try {
            Map<String,Object> map =loanRepaymentService.selectProInfo(loanProductApplyId);
            respInfo =new RespInfo(map, "获取人工审核必要信息");
        } catch(Exception e) {
            logger.error("", e);
            respInfo =new RespInfo("9999", "请求异常");
        }
        return respInfo;
    }

    /**
     * @Author qipengpai
     * @Description //TODO 审批
     * @Date 13:05 2018/9/17
     * @Param [loanRepaymentTrailInfo]
     * @return com.company.platform.base.model.base.RespInfo
     **/
    @RequestMapping(path = "/approveActivitiy", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @RequestAccessAnnotation(modelName = "审批", modelType = RequestAccessConstants.SEARCH, desc = "借贷审批")
    public RespInfo approveActivitiy(String userId,String loanProductApplyId,String comment,String audit,String varJsonStr,String functionName) {
        RespInfo respInfo = null;
        try {
        	//图片校验
    		Map<String,String>  checkImage=uploadFileCheck(loanProductApplyId);
    		if(!checkImage.get("code").equals("0000")) {
    			logger.error("图片你数量核验失败");
                respInfo =new RespInfo("9999", checkImage.get("message"));
                return respInfo;
    		}
        	
            Map<String,String> map = weiXinActivitiService.approveActiviti(userId,loanProductApplyId,comment,audit,varJsonStr,functionName);
            respInfo =new RespInfo("0000",null == map ? "99999999" : "0000".equals(map.get("retCode")) || "9000".equals(map.get("retCode"))? "00000000" : "99999999", "0000".equals(map.get("retCode"))||"9000".equals(map.get("retCode"))  ?"审批操作成功" :"审批操作失败");
        } catch(Exception e) {
            logger.error("", e);
            respInfo =new RespInfo("9999", "审批操作失败");
        }
        return respInfo;
    }
    
    /**
     * 图片数量核验
    * @Title: uploadFileCheck 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param loanProductApplyId
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Map<String,String>    返回类型 
    * @throws
     */
    private Map<String,String> uploadFileCheck(String loanProductApplyId)throws Exception {
			Map<String,String> result = new HashMap<String,String>();
			//通过借贷申请id获取当前模型分类信息
			List<ImgAndDocTitle> imgAndDocTitle = imgAndDocModelService.getImgAndDocTitleInfo(loanProductApplyId);
			String dataId = imgAndDocModelService.getMaterialDataIdPro(imgAndDocTitle.get(0).getImgDocId().toString(), loanProductApplyId);
			for (ImgAndDocTitle imgAndDocTitle2 : imgAndDocTitle) {
				//获得文件可上传数量
				int imageClassNum =  imgAndDocModelService.imageClassNum(imgAndDocTitle2.getId().toString(),dataId);
				//获取文件最少数量
				int imageFloorNum = imgAndDocTitle2.getFloor();
				//获取现有针对于影像分类上传的文件的数量
				// 影像资料数据信息
				List<ImageDataInfo> imageInfoList = imgAndDocModelService.getImageInfoList(loanProductApplyId);
				int imageDataNum = 0;
				for (ImageDataInfo imageDataInfo : imageInfoList) {
					if(imageDataInfo.getClassId().equals(imgAndDocTitle2.getId().toString())){
						imageDataNum ++;
					}
				}
				if(imageFloorNum > imageDataNum){
					result.put("code", "9999");
					result.put("message", "影像资料最少上传"+imageFloorNum+"张！");
					break;
				}else{
					result.put("code", "0000");
					result.put("message", "核验通过");
				}
			}
			return result;
			}
}
