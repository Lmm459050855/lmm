package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.ForestryType;

import core.service.Service;

/**
 * @author lidahu
 * @email 459050855@qq.com
 */
public interface ForestryTypeService extends Service<ForestryType> {

	List<ForestryType> getForestryTypeList(List<ForestryType> resultList);

}
