package edu.pwr.zpi.data.entities;

import java.sql.Timestamp;

public class SensorData {
	/** Database id of data entry. */
	private long id;
	
	/** Value from sensor. */
	private double value;
	
	/** Unit symbol. */
	private String symbol;
	
	/** Physical quantity symbol. */
	private String physical;
	
	/** Time of measurement. */
	private Timestamp time;
	
	/** Id of sensor. */
	private int sensorId;
	
	/** Short description, e.g. location. */
	private String description;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPhysical() {
		return physical;
	}

	public void setPhysical(String physical) {
		this.physical = physical;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
