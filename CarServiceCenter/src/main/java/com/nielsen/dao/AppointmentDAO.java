package com.nielsen.dao;

import java.util.Date;
import java.util.List;

import com.nielsen.models.Appointment;

public interface AppointmentDAO {

	boolean saveAppointment(Appointment appointment);
	
	public int getAppointmentId();
	
	public boolean deleteAppointment(Integer apptNo);
	
	public Appointment retrieveAppointment(Integer apptNo);
	
	public boolean updateAppointment(Appointment appointment);
	
	public List<Appointment> getRangeOfAppointments(Date firstDate, Date secondDate);
}