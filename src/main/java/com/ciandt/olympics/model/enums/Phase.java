package com.ciandt.olympics.model.enums;

public enum Phase {
	
	ELIMINATIONS("Eliminations"),
	OCTAVESFINALS("Octavesfinals"),
	QUARTERFINALS("Quarterfinals"),
	SEMIFINALS("Semifinals"),
	FINAL("Final");
	
	private String value;
	
	Phase(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}

}
