package edu.pwr.zpi.data.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.pwr.zpi.data.constants.Type;
import edu.pwr.zpi.data.dao.SensorDao;
import edu.pwr.zpi.data.entities.Sensor;

@Repository
public class SensorDaoDummyImpl implements SensorDao {

	private List<Sensor> sensors;
	
	public SensorDaoDummyImpl() {
		sensors = new ArrayList<Sensor>();
		sensors.add(createSensor(1L, "Preszure", Type.P));
		sensors.add(createSensor(2L, "T-kuchnia", Type.T));
		sensors.add(createSensor(3L, "T-bejbyrum", Type.T));
		sensors.add(createSensor(4L, "H-bejbyrum", Type.H));
	}
	
	
	private Sensor createSensor(long id, String name, Type type) {
		Sensor s = new Sensor();
		s.setType(type);
		s.setId(id);
		s.setName(name);
		return s;
	}


	@Override
	public List<Sensor> getAllSensors() {
		return sensors;
	}
	
	

}
