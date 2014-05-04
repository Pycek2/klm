package edu.pwr.zpi.data.dao.impl;

public class VerificationResult {
	public boolean result;
	public String role = "NONE";
	public String firstName;
	public String lastName;
	
	public VerificationResult(boolean r) {
		this.result = r;
	}
	
	public VerificationResult(boolean r, String role) {
		this.result = r;
		this.role = role;
	}
}
