package com.company.platform.restapi.service.setup;

import com.company.platform.base.exception.BusinessException;
import com.company.platform.restapi.model.setup.AppVersion;
import com.company.platform.restapi.model.setup.AppVersionDetection;
import com.company.platform.restapi.model.setup.ModifyPwdMessage;

/**
 * 
* @ClassName: IMobileSetUpService 
* @Description: TODO(移动端设置) 
* @author zhengjn 
* @date 2017年10月30日 下午5:45:37 
*
 */
public interface IMobileSetUpService {

    /**
     * 
    * @Title: userModifyPwd 
    * @Description: TODO(修改密码) 
    * @param @param modifyPwdMessage
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    String userModifyPwd(ModifyPwdMessage modifyPwdMessage) throws BusinessException;
    /**
     * 
    * @Title: appVersionDetection 
    * @Description: TODO(版本检测) 
    * @param @param appVersionDetection
    * @param @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws
     */
    AppVersion appVersionDetection(AppVersionDetection appVersionDetection);
}
