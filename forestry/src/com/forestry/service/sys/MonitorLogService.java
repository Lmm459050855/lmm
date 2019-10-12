package com.forestry.service.sys;

import java.util.List;

import com.forestry.model.sys.MonitorLog;

import core.service.Service;

/**
 * @author lidahu 
 * @email 459050855@qq.com
 */
public interface MonitorLogService extends Service<MonitorLog> {

	List<MonitorLog> querySensorMonitorLog();

}
