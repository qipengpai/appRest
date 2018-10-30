package com.company.platform.restapi.dao.loan;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/** 
* @ClassName: BusinessModelVersionAppMapper 
* @Description: TODO(业务模型出处理) 
* @author yangxu 
* @date 2018年2月5日 下午3:37:26 
*  
*/
public interface BusinessModelVersionAppMapper {

	/** 
	* @Title: getModelIdByCodeAndVersion 
	* @Description: TODO(获取模型id) 
	* @param @param code(编码)
	* @param @param version(版本号)
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	String getModelIdByCodeAndVersion(@Param("code") String code, @Param("version") String version);
	
	/** 
	* @Title: getRecordIdByModelIdAndBusinessId 
	* @Description: TODO(获取recordId) 
	* @param @param businessId(业务id)
	* @param @param busmodId(业务模型Id)
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> getRecordIdByModelIdAndBusinessId(@Param("businessId") String businessId, @Param("busmodId") String busmodId);
	
	/** 
	* @Title: findRecordById 
	* @Description: TODO(获取表单提交数据) 
	* @param @param recordId(表单记录id)
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	*/
	Map<String, Object> findRecordById(@Param("id") String recordId);
	
	/** 
	* @Title: findParamsLogicByModelId 
	* @Description: TODO(获取表单字段) 
	* @param @param modelId(模型Id)
	* @param @param createDate(创建日期)
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> findParamsLogicByModelId(@Param("modelId") String modelId, @Param("createDate") String createDate);
	
	/** 
	* @Title: updateModelData 
	* @Description: TODO(更新模型数据) 
	* @param @param recordId(表单id)
	* @param @param code(字段编码)
	* @param @param data    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	int updateModelData(@Param("recordId") String recordId, @Param("code") String code, @Param("data") String data);
	
	/** 
	* @Title: saveModelRecord 
	* @Description: TODO(保存模型表单) 
	* @param @param dataId(数据id)
	* @param @param businessId(借贷id)
	* @param @param modelId(模型id)
	* @param @param createDate(创建日期)
	* @param @param functionExplain    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	int saveModelRecord(@Param("id") String id, @Param("businessId") String businessId, @Param("busmodId") String busmodId, @Param("createDate") String createDate, @Param("functionExplain") String functionExplain);
	
	/** 
	* @Title: findParamsByModelId 
	* @Description: TODO(获取模型参数) 
	* @param @param modelId(模型id)
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	List<Map<String, Object>> findParamsByModelId(@Param("modelId") String modelId);
	
	/** 
	* @Title: saveModelData 
	* @Description: TODO(保存模型数据) 
	* @param @param dataId(数据id)
	* @param @param inputType(输入类型)
	* @param @param code(字段编码)
	* @param @param data    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	void saveModelData(@Param("recordId") String recordId, @Param("inputType") String inputType, @Param("code") String code, @Param("data") String data);
	
}
