package com.company.platform.base.service.sys.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.dao.sys.RoleMapper;
import com.company.platform.base.service.sys.IRoleService;
import com.company.platform.base.util.DateUtil;

/** 
* @ClassName: RoleServiceImp 
* @Description: TODO(角色信息操作实现类) 
* @author luyuchi
* @date 2018年3月21日 下午1:09:48 
*  
*/
@Service
public class RoleServiceImp implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Map<String, Object>> queryRoleDetailByIds(String roleids) {
		if (StringUtils.isEmpty(roleids)) {
			return null;
		}
		String[] roleStrs = roleids.split(",");
		
		List<Map<String, Object>> data = roleMapper.queryRoleDetailByIds(roleStrs);
        if (data!= null && !data.isEmpty()) {
            for (Map<String, Object> map : data) {
                if(map.containsKey("createTime") && null != map.get("createTime")) {
                    map.put("createTime", DateUtil.dateFormat((Date) map.get("createTime"))); 
                }   
            }
        }
        return data;
	}

}
