package edu.pwr.zpi.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;	
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.pwr.zpi.data.entities.Sensor;
import edu.pwr.zpi.data.services.SensorDataService;
import static edu.pwr.zpi.helpers.CollectionsHelper.*;
import edu.pwr.zpi.data.constants.Type;

@Controller
@RequestMapping(value="/sensors")
public class SensorController {
	private static Logger logger = LoggerFactory.getLogger(SensorController.class);
	
	
	@Autowired
	private SensorDataService sensorDataService;
	
	public SensorController() {
		logger.info("Sensor controller created");;
	}
	
	@RequestMapping(value="list")
	public String listSensors(Model model) {
		logger.info("SensorController listSensors");;
		List<Sensor> list = sensorDataService.getAllSensors();
		logger.info(" -> {}", flatten(list));
		model.addAttribute(list);
		logger.info("model = {} ", model);
		return "sensor/sensors";
	}
	
	
	@RequestMapping(method=RequestMethod.GET, params="new") 
	public String showCreateSensor(Model model) {
		model.addAttribute(new Sensor());
		model.addAttribute(Type.values());
		logger.info("model = {} ", model);
		return "sensor/newSensor";
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public String doCreateSensor(@ModelAttribute Sensor sensor) {
		logger.info("new sensor {}", sensor);
		return "redirect:sensors/list";
	}
	
	@RequestMapping(value="{sensorType}", method=RequestMethod.GET) 
	public String listSensorsWithType(@PathVariable String sensorType, Model model) {
		logger.info("show all sensors with type ", sensorType);
		return "sensors/list";
	}
}
