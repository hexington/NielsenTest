package com.nielsen.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Appointments")
public class AppointmentEntity {
	
	@Id
	@Column(name="AppointmentId")
	private int appointmentId;
	
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="Car")
	private String car;
	
	@Column(name="DateOfAppt")
	private Date dateOfAppt;
	
	@Column(name="Price")
	private int price;
	
	@Column(name="StatusOfAppt")
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

	public Date getDateOfAppt() {
		return dateOfAppt;
	}

	public void setDateOfAppt(Date dateOfAppt) {
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
