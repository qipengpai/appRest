package com.company.platform.restapi.validated.collateral;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.base.util.MatchUtil;
import com.company.platform.restapi.dao.collateral.CollateralManageMapper;
import com.company.platform.restapi.dao.custom.CustomerInfoMapper;
import com.company.platform.restapi.model.collateral.CollateralWarrantInfo;
import com.company.platform.restapi.model.custom.CustomerPublicInfo;

/** 
* @ClassName: CollateralInfoValidate 
* @Description: TODO(押品信息校验) 
* @author 王雪 
* @date 2018年5月22日 下午5:59:37 
*  
*/
@Service
public class CollateralInfoValidate {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(CollateralInfoValidate.class);

	@Autowired
	CollateralManageMapper collateralManageMapper;

	@Autowired
	CustomerInfoMapper customerInfoMapper;

	/** 
	* @Title: collateralStatusValidate 
	* @Description: TODO(押品状态校验) 
	* @param @param id
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public void collateralStatusValidate(String id) throws BusinessException {
		logger.info("押品状态校验");
		// 押品权证信息状态校验
		this.collateralWarrantValidate(id);
		// 贷款押品关联校验
		this.loanCollateralRelValidate(id);
	}

	/** 
	* @Title: collateralWarrantValidate 
	* @Description: TODO(押品权证信息状态校验) 
	* @param @param id
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public Map<String, Object> collateralWarrantValidate(String id) throws BusinessException {
		// 获取押品权证信息
		Map<String, Object> map = collateralManageMapper.getCollateralWarrantInfoById(id);
		if (map == null) {
			logger.info("押品不存在");
			throw new BusinessException(ResponseConstants.COLLATERAL_WARRANT_NOT_EXIST.getRetCode(),
					ResponseConstants.COLLATERAL_WARRANT_NOT_EXIST.getRetMsg());
		} else if (Boolean.parseBoolean(map.get("deleteStatus").toString())) {
			logger.info("押品已被删除");
			throw new BusinessException(ResponseConstants.COLLATERAL_WARRANT_DELETED.getRetCode(),
					ResponseConstants.COLLATERAL_WARRANT_DELETED.getRetMsg());
		} else if (Boolean.parseBoolean(map.get("isUsed").toString())) {
			logger.info("押品正在被使用");
			throw new BusinessException(ResponseConstants.COLLATERAL_WARRANT_USED.getRetCode(),
					ResponseConstants.COLLATERAL_WARRANT_USED.getRetMsg());
		}
		return map;
	}

	/** 
	* @Title: loanCollateralRelValidate 
	* @Description: TODO(贷款押品关联校验) 
	* @param @param id
	* @param @throws BusinessException    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public Map<String, Object> loanCollateralRelValidate(String id) throws BusinessException {
		// 获取贷款押品关联
		Map<String, Object> relMap = collateralManageMapper.getLoanCollateralRelByColId(id);
		if (relMap != null) {
			if ("1".equals(relMap.get("warrantStatus").toString())
					|| "2".equals(relMap.get("warrantStatus").toString())) {
				logger.info("押品已入库");
				throw new BusinessException(ResponseConstants.COLLATERAL_WARRANT_STORAGE.getRetCode(),
						ResponseConstants.COLLATERAL_WARRANT_STORAGE.getRetMsg());
			}
		}
		return relMap;
	}

	/** 
	* @Title: collateralRelValidate 
	* @Description: TODO(贷款押品关联校验--取消引用) 
	* @param @param id
	* @param @return
	* @param @throws BusinessException    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	public Map<String, Object> collateralRelValidate(String id) throws BusinessException {
		// 获取贷款押品关联
		Map<String, Object> relMap = collateralManageMapper.getLoanCollateralRelByColId(id);
		if (relMap != null) {
			if ("1".equals(relMap.get("warrantStatus").toString())
					|| "2".equals(relMap.get("warrantStatus").toString())) {
				logger.info("押品已入库");
				throw new BusinessException(ResponseConstants.COLLATERAL_WARRANT_STORAGE.getRetCode(),
						ResponseConstants.COLLATERAL_WARRANT_STORAGE.getRetMsg());
			}
		} else {
			logger.info("押品已取消引入");
			throw new BusinessException(ResponseConstants.COLLATERAL_REL_CANCEL_INTRODUCED.getRetCode(),
					ResponseConstants.COLLATERAL_REL_CANCEL_INTRODUCED.getRetMsg());
		}
		return relMap;
	}

	public void collateralInfoValidate(CollateralWarrantInfo info) throws BusinessException {
		// 校验浮点数，整数1-12位，小数0-2位（可以用来校验金额）
		String NUMBER_12_2 = "^\\d{1,12}(.\\d{1,2}){0,1}$";
		// 校验数据格式
		if (!MatchUtil.match(info.getEsValue(), NUMBER_12_2)) {
			throw new BusinessException(ResponseConstants.ES_VALUE_ERROR.getRetCode(),
					ResponseConstants.ES_VALUE_ERROR.getRetMsg());
		}
		if (StringUtils.isNotEmpty(info.getEsValueSys()) && !MatchUtil.match(info.getEsValueSys(), NUMBER_12_2)) {
			throw new BusinessException(ResponseConstants.ES_VALUE_SYS_ERROR.getRetCode(),
					ResponseConstants.ES_VALUE_SYS_ERROR.getRetMsg());
		}
		// 校验权属人
		CustomerPublicInfo cus = customerInfoMapper.getCustomerPublicInfoById(info.getCustomerId());
		if (cus == null || StringUtils.isEmpty(cus.getId())) {
			throw new BusinessException(ResponseConstants.COLLATERAL_WARRANT_STORAGE.getRetCode(),
					ResponseConstants.COLLATERAL_WARRANT_STORAGE.getRetMsg());
		}
	}
}
