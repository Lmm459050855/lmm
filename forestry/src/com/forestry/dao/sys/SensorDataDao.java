package com.forestry.dao.sys;

import java.util.List;

import com.forestry.model.sys.SensorData;

import core.dao.Dao;

/**
 * @author lidahu
 * @email 459050855@qq.com
 */
public interface SensorDataDao extends Dao<SensorData> {

	List<Object[]> doGetSensorDataStatistics(Short sensorType);

}
