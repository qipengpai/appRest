package com.company.platform.base.service.sys.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.dao.sys.PosterMapper;
import com.company.platform.base.service.sys.IPosterService;

/** 
* @ClassName: PosterServiceImp 
* @Description: TODO(岗位操作实现类) 
* @author luyuchi
* @date 2018年3月21日 下午1:09:34 
*  
*/
@Service
public class PosterServiceImp implements IPosterService {
	
	@Autowired
	private PosterMapper posterMapper;

	@Override
	public List<Map<String, Object>> queryPosterByIds(String posterids) {
		if (StringUtils.isEmpty(posterids)) {
			return null;
		}
		String[] posterStrs = posterids.split(",");
		return posterMapper.queryPosterByIds(posterStrs);
	}

}
