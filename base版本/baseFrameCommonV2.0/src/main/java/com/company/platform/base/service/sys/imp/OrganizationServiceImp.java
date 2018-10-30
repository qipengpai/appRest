package com.company.platform.base.service.sys.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.platform.base.dao.sys.OrgMapper;
import com.company.platform.base.model.sys.Organization;
import com.company.platform.base.service.sys.IOrganizationService;

/** 
* @ClassName: OrganizationServiceImp 
* @Description: TODO(组织机构操作实现类) 
* @author luyuchi
* @date 2018年3月21日 下午1:09:07 
*  
*/
@Service
public class OrganizationServiceImp implements IOrganizationService {
	
	@Autowired
	private OrgMapper orgMapper;
	
	private List<Organization> orgList;
	
	@PostConstruct
	public void loadOrg() {
		orgList = orgMapper.queryAllOrganization();
	}

	@Override
	public List<Map<String, Object>> queryOrganizationByOrgIds(String organizationids) {
		if (StringUtils.isEmpty(organizationids)) {
			return null;
		}
		String[] orgStrs = organizationids.split(",");
		
		return orgMapper.queryOrganizationByOrgIds(orgStrs);
	}

	@Override
	public List<String> queryByVisibleOrgIds(String childOrgId) {
		List<String> orgIds = new ArrayList<String>();
		Organization rootOrg = orgMapper.selectByPrimaryKey(childOrgId);
		switch(rootOrg.geteType()) {
			case 1://总公司
			case 2://总公司部门
				for(Organization org:orgList) {
					if(org.geteType() == 1 || org.geteType() == 2) {
						orgIds.add(org.getId());
					}
				}
				break;
			case 3://总公司营业部
				for(Organization org:orgList) {
					if(org.geteType() == 1 || org.geteType() == 2) {
						orgIds.add(org.getId());
					}
				}
				orgIds.add(childOrgId);
				break;
			case 7://合同商户
				for(Organization org:orgList) {
					if(org.geteType() == 1 || org.geteType() == 2) {
						orgIds.add(org.getId());
					}
				}
				orgIds.add(childOrgId);
				break;
			case 4://分公司
				for(Organization org:orgList) {
					if(org.geteType() == 1 || org.geteType() == 2) {
						orgIds.add(org.getId());
					}
					if(org.geteType() == 5) {
						if(childOrgId.equals(org.getPid())) {
							orgIds.add(org.getId());
						}
					}
				}
				orgIds.add(childOrgId);
				break;
			case 5://分公司部门
				for(Organization org:orgList) {
					if(org.geteType() == 1 || org.geteType() == 2) {
						orgIds.add(org.getId());
					}
					if(org.geteType() == 4) {
						if(rootOrg.getPid().equals(org.getId())) {
							orgIds.add(org.getId());
						}
					}
					if(org.geteType() == 5) {
						if(rootOrg.getPid().equals(org.getPid())) {
							orgIds.add(org.getId());
						}
					}
				}
				//orgIds.add(childOrgId);
				break;
			case 6://分公司营业部
				String pid = rootOrg.getPid();
				for(Organization org:orgList) {
					if(org.geteType() == 1 || org.geteType() == 2) {
						orgIds.add(org.getId());
					}
					if(org.geteType() == 5) {
						if(pid.equals(rootOrg.getPid())) {
							orgIds.add(org.getId());
						}
					}
				}
				orgIds.add(pid);
				orgIds.add(childOrgId);
		}
		return orgIds;
	}

	@Override
	public List<Map<String, Object>> queryStaffByOrgId(String organizationids) {
		if (StringUtils.isEmpty(organizationids)) {
			return null;
		}
		String[] orgStrs = organizationids.split(",");
		
		return orgMapper.queryStaffByOrgId(orgStrs);
	}

}
