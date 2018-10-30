package com.company.platform.restapi.model.setup;

import org.hibernate.validator.constraints.NotEmpty;

import com.company.platform.base.model.base.BaseHttpParamsAppReq;
import com.company.platform.base.util.cipher.Decrypt;

/** 
* @ClassName: ModifyPwdMessage 
* @Description: TODO(修改密码) 
* @author zhengjn 
* @date 2017年11月2日 下午4:24:12 
*  
*/
@SuppressWarnings("serial")
public class ModifyPwdMessage extends BaseHttpParamsAppReq {

    @Decrypt
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;

    @Decrypt
    @NotEmpty(message = "当前密码不能为空")
    private String oldPassword;


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

}
