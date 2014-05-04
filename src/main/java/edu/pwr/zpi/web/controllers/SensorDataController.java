package edu.pwr.zpi.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pwr.zpi.data.entities.SensorData;
import edu.pwr.zpi.data.services.SensorDataService;

@Controller
@RequestMapping("/data")
public class SensorDataController {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(SensorDataController.class);
	
	@Autowired
	private SensorDataService dataService;
	
	@RequestMapping(value="/public", method = {RequestMethod.GET, RequestMethod.POST}) 
	public @ResponseBody List<SensorData> publicData(HttpServletResponse res) {
		logger.info("SensorDataController.publicData {}", "get all");
		List<SensorData> list = dataService.getLatestPublicSensorData();
		return list;
	}
	
	@RequestMapping(value="/latest", method = {RequestMethod.GET, RequestMethod.POST}) 
	public @ResponseBody List<SensorData> latestData(HttpServletResponse res) {
		logger.info("SensorDataController.latestData {}", "get all");
		List<SensorData> list = dataService.getLatestSensorData();
		return list;
	}
}
