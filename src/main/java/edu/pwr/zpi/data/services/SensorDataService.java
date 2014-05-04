package edu.pwr.zpi.data.services;

import java.util.List;

import edu.pwr.zpi.data.entities.Sensor;
import edu.pwr.zpi.data.entities.SensorData;

public interface SensorDataService {
	
	/**
	 * Returns a list of all sensors available in the system.
	 * @return list of all sensors.
	 */
	List<Sensor> getAllSensors();
	
	/**
	 * Returns a list of latest public sensor data.
	 * @return list of latest public sensor data.
	 */
	List<SensorData> getLatestPublicSensorData();
	
	/**
	 * Returns a list of latest sensor data.
	 * @return list of latest sensor data.
	 */
	List<SensorData> getLatestSensorData();
	
}