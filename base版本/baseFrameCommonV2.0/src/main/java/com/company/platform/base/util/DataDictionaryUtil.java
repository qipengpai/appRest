/******************************************************************
 *
 *    Package:     com.company.platform.base.util
 *
 *    Filename:    DataDictionaryUtil.java
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
package com.company.platform.base.util;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.service.dataDictionary.IDataDictionaryService;

/** 
* @ClassName: DataDictionaryUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhengjn 
* @date 2017年10月11日 下午1:15:17 
*  
*/
@SuppressWarnings("all")
@Service
public class DataDictionaryUtil {

    @Autowired
    IDataDictionaryService DataDictionaryServiceImp;

    public String getGlobalConfigInfo(String name) {
        return DataDictionaryServiceImp.getGlobalConfigInfo(name);
    }

    /**
     * @param type 字典表枚举类型
     * @return key-value map， key代表字典code，value代表字典显示名称
     * 通过字典类型获得枚举项
     * */
	public Map<String, String> getDictDataByType(String type) {
        Map<String, String> result = null;
        if (StringUtils.isNotBlank(type)) {
            Map<String, Object> data = (Map<String, Object>) DataDictionaryServiceImp.getDataMap().get(type);
            if (data != null) {
                result = (Map<String, String>) data.get("item");
            }
        }
        return null != result ? result : new TreeMap<String, String>();
    }

    /**
     * @param code 配置项code
     * @return 配置项值
     * */
    public String getConfigData(String code) {
        return DataDictionaryServiceImp.getGlobal_Config(code);
    }

    /**
     * @param type 字典表枚举类型
     * @param code 字典表的值
     * @return 显示名称
     * 通过type类型获得code所对应的显示名称
     * */
    public String getDictDataByTypeAndKey(String type, String code) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(code)) {
            return getDictDataByType(type).get(code);
        }
        return null;
    }
}
