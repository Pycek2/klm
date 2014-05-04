package edu.pwr.zpi.data.entities;

import edu.pwr.zpi.data.constants.Type;


public class Sensor {
	private long id;
	
	private String name;
	private Type type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sensor [id=").append(id).append(", ");
		if (name != null)
			builder.append("name=").append(name).append(", ");
		if (type != null)
			builder.append("type=").append(type);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
