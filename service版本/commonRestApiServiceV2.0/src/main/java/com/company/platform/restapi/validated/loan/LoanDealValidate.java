package com.company.platform.restapi.validated.loan;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.loan.deal.LoanRepaymentTrailReq;
import com.company.platform.restapi.model.loan.deal.SubmitApprovalReq;

/** 
* @ClassName: LoanDealValidate 
* @Description: TODO(待审核任务参数校验) 
* @author wangxue 
* @date 2018年7月21日 上午10:22:12 
*  
*/
@Service
public class LoanDealValidate {
	/**
	 * 
	* @Title: productApplyInfoOrgValidate 
	* @Description: TODO(验证数据是否同步（组织机构）) 
	* @param @param customerOrgId 当前登录用户id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public void submitApprovalValidate(SubmitApprovalReq req) throws BusinessException {
		if ("reject".equals(req.getAudit())) {// 退回原因不能为空
			if (StringUtils.isEmpty(req.getReason())) {
				throw new BusinessException(ResponseConstants.AUDIT_REJECT_REASON_IS_EMPTY.getRetCode(),
						ResponseConstants.AUDIT_REJECT_REASON_IS_EMPTY.getRetMsg());
			}
		} else if ("refuse".equals(req.getAudit())) {// 拒绝原因不能为空
			if (StringUtils.isEmpty(req.getReason())) {
				throw new BusinessException(ResponseConstants.AUDIT_REFUSE_REASON_IS_EMPTY.getRetCode(),
						ResponseConstants.AUDIT_REFUSE_REASON_IS_EMPTY.getRetMsg());
			}
		} else if ("agree".equals(req.getAudit()) && "true".equals(req.getChoose())) {// 同意，判断是否选人，下一个节点
			if (StringUtils.isEmpty(req.getNextUserId())) {
				throw new BusinessException(ResponseConstants.NEXT_USER_ID_IS_EMPTY.getRetCode(),
						ResponseConstants.NEXT_USER_ID_IS_EMPTY.getRetMsg());
			} else if (StringUtils.isEmpty(req.getNextUserName())) {
				throw new BusinessException(ResponseConstants.NEXT_USER_NAME_IS_EMPTY.getRetCode(),
						ResponseConstants.NEXT_USER_NAME_IS_EMPTY.getRetMsg());
			}
		}

	}

	/** 
	* @Title: loanRepaymentTrailValidate 
	* @Description: TODO(还款试算请求参数校验) 
	* @param @param req
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void loanRepaymentTrailValidate(LoanRepaymentTrailReq req) throws BusinessException {
		if (!"1".equals(req.getRepayType())) {// ！一次性还本付息
			if (StringUtils.isEmpty(req.getRepayTermCount())) {
				throw new BusinessException(ResponseConstants.REPAY_TERMCOUNT_IS_EMPTY.getRetCode(),
						ResponseConstants.REPAY_TERMCOUNT_IS_EMPTY.getRetMsg());
			} else if (StringUtils.isEmpty(req.getRepayTermUnit())) {
				throw new BusinessException(ResponseConstants.REPAY_TERMUNIT_IS_EMPTY.getRetCode(),
						ResponseConstants.REPAY_TERMUNIT_IS_EMPTY.getRetMsg());
			}
		}
		if ("2".equals(req.getTermUnit()) && req.getRepayTermUnit() != null && !"2".equals(req.getRepayTermUnit())) {// 批准期限选择"月"，还款间隔不支持选择"日"
			throw new BusinessException(ResponseConstants.REPLY_TERM_UNIT_NOT_PASS.getRetCode(),
					ResponseConstants.REPLY_TERM_UNIT_NOT_PASS.getRetMsg());
		} else if ("3".equals(req.getRepayType())) {
			// 还款方式 == 3（等额本息），需批准期限单位 、还款间隔单位均为2（月）
			if (!"2".equals(req.getTermUnit()) || !"2".equals(req.getRepayTermUnit())) {
				throw new BusinessException(ResponseConstants.REPAY_TERM_UNIT_NOT_PASS.getRetCode(),
						ResponseConstants.REPAY_TERM_UNIT_NOT_PASS.getRetMsg());
			}
		}
	}
}
