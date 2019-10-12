package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.Authority;
import com.forestry.model.sys.RoleAuthority;

import core.service.Service;

/**
 * @author lidahu
 * @email 459050855@qq.com
 */
public interface AuthorityService extends Service<Authority> {

	List<Authority> queryByParentIdAndRole(Short role);

	List<Authority> queryChildrenByParentIdAndRole(Long parentId, Short role);

	String querySurfaceAuthorityList(List<RoleAuthority> queryByProerties, Long id, String buttons);

}
