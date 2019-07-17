package com.nielsen.models;

public class Appointment extends BaseModel {
	
	private int appointmentId;
	private String firstName;
	private String lastName;
	private String car;
	private String dateOfAppt;
	private int price;
	private String statusOfAppt;
	
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getDateOfAppt() {
		return dateOfAppt;
	}
	public void setDateOfAppt(String dateOfAppt) {
		this.dateOfAppt = dateOfAppt;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStatusOfAppt() {
		return statusOfAppt;
	}
	public void setStatusOfAppt(String statusOfAppt) {
		this.statusOfAppt = statusOfAppt;
	}
	
	
}
