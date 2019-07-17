package com.nielsen.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nielsen.entities.AppointmentEntity;
import com.nielsen.models.Appointment;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private final static Logger LOGGER = Logger.getLogger(AppointmentDAOImpl.class.getName());
	
	@Override
	public boolean saveAppointment(Appointment appointment) {
		boolean saveFlag = true;
		try {
			AppointmentEntity appointmentEntity = new AppointmentEntity();
			appointmentEntity.setFirstName(appointment.getFirstName());
			appointmentEntity.setLastName(appointment.getLastName());
			appointmentEntity.setCar(appointment.getCar());
			appointmentEntity.setDateOfAppt(new SimpleDateFormat("MM/dd/yyyy").parse(appointment.getDateOfAppt()));
			appointmentEntity.setPrice(appointment.getPrice());
			appointmentEntity.setStatusOfAppt(appointment.getStatusOfAppt());
		
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.save(appointmentEntity); // Add new record
		} catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.info("Excpetion occurred - saveAppointment() method.");
			saveFlag = false;
		}
		
		return saveFlag;
	}
	
	@Override
	public int getAppointmentId() {
		Session currentSession = sessionFactory.getCurrentSession();
		NativeQuery<?> query = currentSession.createSQLQuery("SELECT last_insert_id()");
		int id = Integer.parseInt(query.getResultList().get(0).toString());
		LOGGER.info("getAppointmentId() method successful.");
		return id;
	}
	
	@Override
	public boolean deleteAppointment(Integer apptNo) {
		boolean deleteFlag = true;
		try {
			Session session = sessionFactory.getCurrentSession();
			AppointmentEntity accountEntity = (AppointmentEntity) session.load(AppointmentEntity.class, apptNo);
			session.delete(accountEntity);
		} catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.info("Excpetion occurred - deleteAppointment() method.");
			deleteFlag = false;
		}
		
		return deleteFlag;
	}
	
	@Override
	public Appointment retrieveAppointment(Integer apptNo) {
		Appointment appointment = new Appointment();
		
		Session session = sessionFactory.getCurrentSession();
		AppointmentEntity appointmentEntity = (AppointmentEntity) session.get(AppointmentEntity.class, apptNo);
		
		// Return null if appointment does not exist
		if(appointmentEntity == null) {
			return null;
		}
		
		appointment.setAppointmentId(appointmentEntity.getAppointmentId());
		appointment.setFirstName(appointmentEntity.getFirstName());
		appointment.setLastName(appointmentEntity.getLastName());
		appointment.setCar(appointmentEntity.getCar());
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = format.format(appointmentEntity.getDateOfAppt());
		appointment.setDateOfAppt(dateString.toString());
		appointment.setPrice(appointmentEntity.getPrice());
		appointment.setStatusOfAppt(appointmentEntity.getStatusOfAppt());
		
		return appointment;
	}
	
	@Override
	public boolean updateAppointment(Appointment appointment) {
		boolean saveFlag = true;
		try {
			AppointmentEntity appointmentEntity = new AppointmentEntity();
			appointmentEntity.setAppointmentId(appointment.getAppointmentId());
			appointmentEntity.setFirstName(appointment.getFirstName());
			appointmentEntity.setLastName(appointment.getLastName());
			appointmentEntity.setCar(appointment.getCar());
			appointmentEntity.setDateOfAppt(new SimpleDateFormat("MM/dd/yyyy").parse(appointment.getDateOfAppt()));
			appointmentEntity.setPrice(appointment.getPrice());
			appointmentEntity.setStatusOfAppt(appointment.getStatusOfAppt());
		
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.update(appointmentEntity); // Update record
		} catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.info("Excpetion occurred - updateAppointment() method.");
			saveFlag = false;
		}
		
		return saveFlag;
	}
	
	public List<Appointment> getRangeOfAppointments(Date firstDate, Date secondDate){
		Session currentSession = sessionFactory.getCurrentSession();
		String hql = "from AppointmentEntity s where s.dateOfAppt between :firstDate and :secondDate";
		List<?> result = currentSession.createQuery(hql).setParameter("firstDate", firstDate).setParameter("secondDate", secondDate).list();
		
		List<Appointment> appointments = new ArrayList<Appointment>();
		
		// Create appointment objects from AppointmentEntity result list
		if(!result.isEmpty()) {
			result.stream().forEach(ae -> {
				Appointment appointment = new Appointment();
				appointment.setAppointmentId(((AppointmentEntity) ae).getAppointmentId());
				appointment.setFirstName(((AppointmentEntity) ae).getFirstName());
				appointment.setLastName(((AppointmentEntity) ae).getLastName());
				appointment.setCar(((AppointmentEntity) ae).getCar());
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				String dateString = format.format(((AppointmentEntity) ae).getDateOfAppt());
				appointment.setDateOfAppt(dateString.toString());
				appointment.setPrice(((AppointmentEntity) ae).getPrice());
				appointment.setStatusOfAppt(((AppointmentEntity) ae).getStatusOfAppt());
				appointments.add(appointment);
			});
		}
		
		LOGGER.info("getRangeOfAppointments() method successful.");
		return appointments;
	}
}
