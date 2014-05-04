package edu.pwr.zpi.data.entities;

public enum Unit {
	DEGREE_CELCIUS("T", "˚C"),
	PRESSURE_HPA("P", "hPa"),
	HUMIDITY("H", "%"),
	POWER_USAGE("E", "kWh");
	
	private String symbol;
	private String physical;
	
	private Unit(String physical, String symbol) {
		this.symbol = symbol;
		this.physical = physical;
	}
	
	public String getSymbol() {
		return this.symbol;
	}

	public String getPhysical() {
		return this.physical;
	}
}
