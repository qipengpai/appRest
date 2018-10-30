package com.company.platform.restapi.service.collateral.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.baseenum.ResponseConstants;
import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.dao.collateral.CollateralManageMapper;
import com.company.platform.restapi.model.collateral.CollateralDetailResp;
import com.company.platform.restapi.model.collateral.CollateralInfo;
import com.company.platform.restapi.model.collateral.CollateralWarrantInfo;
import com.company.platform.restapi.model.collateral.CustomerWarrant;
import com.company.platform.restapi.model.collateral.TemplateElement;
import com.company.platform.restapi.model.collateral.TemplateValue;
import com.company.platform.restapi.service.collateral.ICollateralManageService;

/** 
* @ClassName: CollateralManageServiceImpl 
* @Description: TODO(押品管理servise实现类) 
* @author 王雪 
* @date 2018年5月22日 上午10:03:24 
*  
*/
@Service
public class CollateralManageServiceImpl implements ICollateralManageService {

	@Autowired
	private CollateralManageMapper collateralManageMapper;

	/*
	 * (非 Javadoc) <p>Title: getCollateralList</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralManageService#
	 * getCollateralList()
	 */
	@Override
	public List<CollateralInfo> getCollateralList(List<String> orgList, String userId) {
		return collateralManageMapper.getCollateralList(orgList, userId);
	}

	/*
	 * (非 Javadoc) <p>Title: deleteCollateralInfoById</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralManageService#
	 * deleteCollateralInfoById(java.lang.String)
	 */
	@Override
	public int deleteCollateralInfoById(String id) {
		return collateralManageMapper.deleteCollateralInfoById(id);
	}

	/*
	 * (非 Javadoc) <p>Title: getCustomerWarrantList</p> <p>Description: </p>
	 * 
	 * @param orgList
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralManageService#
	 * getCustomerWarrantList(java.util.List)
	 */
	@Override
	public List<CustomerWarrant> getCustomerWarrantList(List<String> orgList) {
		List<CustomerWarrant> list = new ArrayList<>();
		if (orgList != null && orgList.size() > 0) {
			list = collateralManageMapper.getCustomerWarrantList(orgList);
		}
		return list;
	}

	/*
	 * (非 Javadoc) <p>Title: getCollateralTemplateElementById</p>
	 * <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralManageService#
	 * getCollateralTemplateElementById(java.lang.String)
	 */
	@Override
	public List<TemplateElement> getCollateralTemplateElementById(String id) {
		List<TemplateElement> list = collateralManageMapper.getCollateralTemplateElementById(id);
		List<TemplateElement> elementList = new ArrayList<TemplateElement>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i = i + 2) {
				if ("label".equals(list.get(i).getElementType())) {
					list.get(i + 1).setDisplayName(list.get(i).getDisplayName());
					String selectOption = list.get(i + 1).getSelectOption();
					if (StringUtils.isNotEmpty(selectOption)) {
						list.get(i + 1).setSelectOption(selectOption.replaceAll(" \"", "\""));
					}
					elementList.add(list.get(i + 1));
				}
			}
		}
		return elementList;
	}

	/*
	 * (非 Javadoc) <p>Title: getTitleByTemplateId</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralManageService#
	 * getTitleByTemplateId(java.lang.String)
	 */
	@Override
	public String getTitleByTemplateId(String id) {
		return collateralManageMapper.getTitleByTemplateId(id);
	}

	/*
	 * (非 Javadoc) <p>Title: getCollateralInfoById</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralManageService#
	 * getCollateralInfoById(java.lang.String)
	 */
	@Override
	public CollateralDetailResp getCollateralInfoById(String id) throws BusinessException {
		CollateralDetailResp detail = collateralManageMapper.getCollateralInfoById(id);
		if (detail == null) {
			// 押品不存在或已被删除
			throw new BusinessException(ResponseConstants.COLLATERAL_WARRANT_NOT_EXIST.getRetCode(),
					ResponseConstants.COLLATERAL_WARRANT_NOT_EXIST.getRetMsg());
		}
		return detail;
	}

	/*
	 * (非 Javadoc) <p>Title: getTemplateValueByCollateralId</p> <p>Description:
	 * </p>
	 * 
	 * @param collateralId
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralManageService#
	 * getTemplateValueByCollateralId(java.lang.String)
	 */
	@Override
	public List<TemplateValue> getTemplateValueByCollateralId(String collateralId) {
		return collateralManageMapper.getTemplateValueByCollateralId(collateralId);
	}

	/*
	 * (非 Javadoc) <p>Title: insertCollateralWarrantInfo</p> <p>Description:
	 * </p>
	 * 
	 * @param info
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralManageService#
	 * insertCollateralWarrantInfo(com.company.platform.restapi.model.collateral
	 * .CollateralWarrantInfo)
	 */
	@Override
	public int insertCollateralWarrantInfo(CollateralWarrantInfo info) {
		return collateralManageMapper.insertCollateralWarrantInfo(info);
	}

	/*
	 * (非 Javadoc) <p>Title: updateCollateralWarrantInfo</p> <p>Description:
	 * </p>
	 * 
	 * @param info
	 * 
	 * @return
	 * 
	 * @see
	 * com.company.platform.restapi.service.collateral.ICollateralManageService#
	 * updateCollateralWarrantInfo(com.company.platform.restapi.model.collateral
	 * .CollateralWarrantInfo)
	 */
	@Override
	public int updateCollateralWarrantInfo(CollateralWarrantInfo info) {
		return collateralManageMapper.updateCollateralWarrantInfo(info);
	}

	/** 
	* @Title: saveTemplateValue 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param collateralId
	* @param @param list
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	*/
	@Override
	public int saveTemplateValue(String collateralId, List<TemplateValue> list) {
		// 删除押品模板原实际值
		collateralManageMapper.deleteTemplateValueByCollateralId(collateralId);
		if (list != null && list.size() > 0) {
			// 新建押品模板实际值
			for (TemplateValue tv : list) {
				collateralManageMapper.insertTemplateValue(tv.getKey(), tv.getValue(), collateralId);
			}
		}
		return 0;
	}

}
