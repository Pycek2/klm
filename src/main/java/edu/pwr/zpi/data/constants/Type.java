package edu.pwr.zpi.data.constants;

public enum Type {
	T("Temperature","C"),
	P("Pressure","Pa"),
	H("Humidity","%");
	
	private String typeName;
	private String unit;
	
	private Type(String typeName, String unit) {
		this.typeName = typeName;
		this.unit     = unit;
	}
	
	public String toString() {
		return name() + "(" + typeName + "," + unit + ")";
	}

	public String getUnit() {
		return this.unit;
	}
	
	public static Type parse(int val) {

		if (val < Type.values().length) {
			return Type.values()[val];
		}
		return null;
	}
	
}