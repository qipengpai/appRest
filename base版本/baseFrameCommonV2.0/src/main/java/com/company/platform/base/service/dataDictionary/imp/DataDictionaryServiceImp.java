/******************************************************************
 *
 *    Package:     com.company.platform.base.service.dataDictionary.imp
 *
 *    Filename:    DataDictionaryServiceImp.java
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
package com.company.platform.base.service.dataDictionary.imp;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.company.platform.base.dao.dataDictionary.DataDictionaryMapper;
import com.company.platform.base.service.dataDictionary.IDataDictionaryService;

/**
 * @ClassName: DataDictionaryServiceImp
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhengjn
 * @date 2017年10月11日 下午1:29:26
 */
@Service
public class DataDictionaryServiceImp implements IDataDictionaryService {

	// 日志
	private final Logger logger = LoggerFactory.getLogger(DataDictionaryServiceImp.class);

	@Autowired
	DataDictionaryMapper dataDictionaryMapper;

	/*
	 * (非 Javadoc) <p>Title: getGlobalConfigInfo</p> <p>Description: </p>
	 * @param name
	 * @see
	 * com.company.platform.base.service.dataDictionary.IDataDictionaryService#getGlobalConfigInfo(java.lang.String)
	 */
	@Override
	@Cacheable(value = "dataDictionary", key = "#name")
	public String getGlobalConfigInfo(String name) {
		// TODO Auto-generated method stub
		return dataDictionaryMapper.getGlobalConfigInfo(name);
	}

	@Override
	@Cacheable(value = "DataMapCache")
	public Map<String, Map<String, Object>> getDataMap() {
		Map<String, Map<String, Object>> dataMap = new HashMap<String, Map<String, Object>>();
		List<Map<String, Object>> systemDatas = dataDictionaryMapper.getSystemData();
		Map<String, Object> temp = null;
		Map<String, String> item = null;
		//List<Map<String, Object>> tree = null; 
		Map<String, Map<String, Object>> treeTemp = null;
		String key = null;
		for (Map<String, Object> data : systemDatas) {
			String id = (String) data.get("id");
			String relationsId = (String) data.get("relationsId");
			String rangeCode = (String) data.get("rangeCode");
			String code = (String) data.get("code");
			String name = (String) data.get("name");
			if (!rangeCode.equals(key)) {
				temp = new HashMap<String, Object>();
				item = new LinkedHashMap<String, String>();
				//tree = new ArrayList<Map<String, Object>>();
				treeTemp = new HashMap<String, Map<String, Object>>();
				temp.put("item", item);
				//temp.put("tree", tree);
				temp.put("treeTemp", treeTemp);
				key = rangeCode;
				dataMap.put(key, temp);
			}

			Integer isFixed = (Integer) data.get("isFixed");

			if (isFixed.intValue() == 1) {
				temp.put("defValue", code);
			}

			item.put(code, name);

			Map<String, Object> source = new HashMap<String, Object>();
			source.put("code", code);
			source.put("name", name);
			if (relationsId != null) {
				source.put("relationsId", relationsId);
			}

			treeTemp.put(id, source);
		}
		return dataMap;
	}

	@Cacheable(value = "GlobalConfigCache", key = "#code")
	@Override
	public String getGlobal_Config(String code) {
		String value = dataDictionaryMapper.getGlobalData(code);
		return value;
	}

	@CacheEvict(value = "DataMapCache")
	public void delDataMap() {
		logger.info("清除缓存DataMapCache");
	}

	@CacheEvict(value = "GlobalConfigCache")
	public void delGlobal_Config() {
		logger.info("清除缓存GlobalConfigCache");
	}

}
