package com.forestry.dao.sys;

import java.util.List;

import com.forestry.model.sys.Forestry;

import core.dao.Dao;

/**
 * @author lidahu
 * @email 459050855@qq.com
 */
public interface ForestryDao extends Dao<Forestry> {
	
	List<Object[]> queryExportedForestry(Long[] ids);

}
