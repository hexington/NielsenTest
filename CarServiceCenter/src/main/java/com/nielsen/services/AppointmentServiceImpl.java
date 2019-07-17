package com.nielsen.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nielsen.dao.AppointmentDAO;
import com.nielsen.models.Appointment;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentDAO appointmentDAO;
	
	@Override
	@Transactional
	public boolean saveAppointment(Appointment appointment) {
		return appointmentDAO.saveAppointment(appointment);
	}
	
	@Override
	@Transactional
	public int getAppointmentId() {
		return appointmentDAO.getAppointmentId();
	}
	
	@Override
	@Transactional
	public boolean deleteAppointment(Integer apptNo) {
		return appointmentDAO.deleteAppointment(apptNo);
	}
	
	@Override
	@Transactional
	public Appointment retrieveAppointment(Integer apptNo) {
		return appointmentDAO.retrieveAppointment(apptNo);
	}
	
	@Override
	@Transactional
	public boolean updateAppointment(Appointment appointment) {
		return appointmentDAO.updateAppointment(appointment);
	}
	
	@Override
	@Transactional
	public List<Appointment> getRangeOfAppointments(Date firstDate, Date secondDate){
		return appointmentDAO.getRangeOfAppointments(firstDate, secondDate);
	}
}
