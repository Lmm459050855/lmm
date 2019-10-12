package com.forestry.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.SysUserDao;
import com.forestry.model.sys.SysUser;

import core.dao.BaseDao;

/**
 * @author lidahu
 * @email 459050855@qq.com
 */
@Repository
public class SysUserDaoImpl extends BaseDao<SysUser> implements SysUserDao {

	public SysUserDaoImpl() {
		super(SysUser.class);
	}

}
