package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.SysUser;

import core.service.Service;

/**
 * @author lidahu
 * @email 459050855@qq.com
 */
public interface SysUserService extends Service<SysUser> {

	List<SysUser> getSysUserList(List<SysUser> resultList);

}
