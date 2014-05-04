package edu.pwr.zpi.data.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pwr.zpi.data.dao.SensorDao;
import edu.pwr.zpi.data.entities.Sensor;
import edu.pwr.zpi.data.entities.SensorData;
import edu.pwr.zpi.data.entities.Unit;


@Service
public class SensorDataDummyService implements SensorDataService {
	
	@Autowired
	private SensorDao sensorDao;
	
	public SensorDataDummyService() {
	}
	
	@Override
	public List<Sensor> getAllSensors() {
		return sensorDao.getAllSensors();
	}

	@Override
	public List<SensorData> getLatestPublicSensorData() {
		List<SensorData> result = new ArrayList<SensorData>();
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		SensorData data = getSensorData(21.3, Unit.DEGREE_CELCIUS, ts, "Polnocna sciana");
		result.add(data);
		
		data = getSensorData(1001.0, Unit.PRESSURE_HPA, ts, "Cisnienie zewnatrz");
		result.add(data);
		
		data = getSensorData(61, Unit.HUMIDITY, ts, "Wilgotnosc dwor");
		result.add(data);
		
		return result;
	}

	private SensorData getSensorData(double value, Unit unit, Timestamp ts, String desc) {
		SensorData data = new SensorData();
		
		data.setId(0);
		data.setSensorId(1);
		data.setSymbol(unit.getSymbol());
		data.setPhysical(unit.getPhysical());
		data.setTime(ts);
		data.setValue(value);
		data.setDescription(desc);
		return data;
	}

	@Override
	public List<SensorData> getLatestSensorData() {
		List<SensorData> result = new ArrayList<SensorData>();
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		SensorData data = getSensorData(21.3, Unit.DEGREE_CELCIUS, ts, "Polnocna sciana");
		result.add(data);
		
		data = getSensorData(1001.0, Unit.PRESSURE_HPA, ts, "Cisnienie zewnatrz");
		result.add(data);
		
		data = getSensorData(61, Unit.HUMIDITY, ts, "Wilgotnosc dwor");
		result.add(data);
		
		data = getSensorData(18.0, Unit.DEGREE_CELCIUS, ts, "Parter/Sypialnia");
		result.add(data);
		return result;
	}
	

}
