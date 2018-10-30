package com.company.platform.base.service.sys.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.dao.sys.SysUserMapper;
import com.company.platform.base.service.sys.ISysUserService;
import com.company.platform.base.util.DateUtil;

/** 
* @ClassName: SysUserServiceImp 
* @Description: TODO(系统用户操作实现类) 
* @author luyuchi
* @date 2018年3月21日 下午1:10:04 
*  
*/
@Service
public class SysUserServiceImp implements ISysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public List<Map<String, Object>> queryStaffDetailByIds(String userIds) {
		if (StringUtils.isEmpty(userIds)) {
			return null;
		}
		String[] userStrs = userIds.split(",");
		List<Map<String, Object>> data = sysUserMapper.queryStaffDetailByids(userStrs);
		if (data!= null && !data.isEmpty()) {
			for (Map<String, Object> map : data) {
				if(map.containsKey("birthday") && null != map.get("birthday")) {
					map.put("birthday", DateUtil.dateFormat((Date) map.get("birthday")));	
				}	
			}
		}
		return data;
	}

	@Override
	public List<Map<String, Object>> queryUsersByOrgsAndRoles(List<String> orgIds, String roleids) {
		if (StringUtils.isEmpty(roleids)) {
			return null;
		}
		String[] roleStrs = roleids.split(",");
		return sysUserMapper.queryUsersByOrgsAndRoles(orgIds, roleStrs);
	}

	@Override
	public List<Map<String, Object>> queryUsersByOrgsAndPosts(List<String> orgIds, String posterids) {
		if (StringUtils.isEmpty(posterids)) {
			return null;
		}
		String[] posterStrs = posterids.split(",");
		return sysUserMapper.queryUsersByOrgsAndPosts(orgIds, posterStrs);
	}

}
