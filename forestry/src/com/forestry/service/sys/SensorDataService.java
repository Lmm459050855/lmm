package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.SensorData;

import core.service.Service;

/**
 * @author lidahu
 * @email 459050855@qq.com
 */
public interface SensorDataService extends Service<SensorData> {

	List<Object[]> doGetSensorDataStatistics(Short sensorType);

	List<Object[]> doGetEnhanceSensorDataStatistics(List<Object[]> list);

}
