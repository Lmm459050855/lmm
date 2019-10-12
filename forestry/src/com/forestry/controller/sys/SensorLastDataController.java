package com.forestry.controller.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forestry.core.ForestryBaseController;
import com.forestry.model.sys.SensorLastData;
import com.forestry.service.sys.SensorLastDataService;

/**
 * @author lidahu
 * @email 459050855@qq.com
 */
@Controller
@RequestMapping("/sys/sensorlastdata")
public class SensorLastDataController extends ForestryBaseController<SensorLastData> {

	@Resource
	private SensorLastDataService sensorLastDataService;

}
