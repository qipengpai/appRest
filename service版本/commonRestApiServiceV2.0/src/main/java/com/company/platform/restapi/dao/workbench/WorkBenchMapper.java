package com.company.platform.restapi.dao.workbench;

/**
 * @ClassName: WorkBenchMapper
 * @Description: TODO(获取工作台数量信息)
 * @author liang
 * @date 2018年1月25日 下午2:50:03
 * 
 */
public interface WorkBenchMapper {

	/**
	 * @Title: getTemporaryCount @Description: TODO(获取借贷申请暂存任务数量) @param @param
	 * registerId @param @param status @param @return 设定文件 @return String
	 * 返回类型 @throws
	 */
	int getTemporaryCount(String registerId);

	/**
	 * @Title: getWorkedCount @Description: TODO(获取借贷申请已办进件数量) @param @param
	 * string @param @param userId @param @param userName @param @return
	 * 设定文件 @return String 返回类型 @throws
	 */
	int getWorkedCount(String registerId);

}
