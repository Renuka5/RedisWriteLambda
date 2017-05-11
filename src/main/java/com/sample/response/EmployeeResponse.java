package com.sample.response;

public class EmployeeResponse {
	private String Name;
    private String Company;
    private String Location;
	public EmployeeResponse(String name, String company, String location) {
		super();
		Name = name;
		Company = company;
		Location = location;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
    

}
