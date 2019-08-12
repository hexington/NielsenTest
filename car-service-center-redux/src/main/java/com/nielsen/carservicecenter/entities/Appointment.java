package com.nielsen.carservicecenter.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AppointmentId")
	private int appointmentId;
	
	@NotEmpty(message="First Name should not be empty")
	@NotBlank
	@Column(name="FirstName")
	private String firstName;
	
	@NotEmpty(message="Last Name should not be empty")
	@NotBlank
	@Column(name="LastName")
	private String lastName;
	
	@NotEmpty(message="Car Name should not be empty")
	@NotBlank
	@Column(name="Car")
	private String car;
	
	//@Future(message="Date must not be in the past")
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="DateOfAppt")
	private Date dateOfAppt;
	
	@Positive
	@Column(name="Price")
	private int price;
	
	@Pattern(regexp="ONTIME|DELAYED", message="Must be 'ONTIME' or 'DELAYED'")
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
