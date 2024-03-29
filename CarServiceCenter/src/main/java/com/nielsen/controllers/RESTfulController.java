package com.nielsen.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nielsen.models.Appointment;
import com.nielsen.models.BaseModel;
import com.nielsen.services.AppointmentService;

@RestController
@PropertySource("classpath:app.properties")
public class RESTfulController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
    Environment env;
	
	private final static Logger LOGGER = Logger.getLogger(RESTfulController.class.getName());

	@PostMapping("/createAppointment")
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
		if(appointmentService.saveAppointment(appointment)) { // Successful database entry
			// Get the appointment Id
			int id = appointmentService.getAppointmentId();	
			appointment.setAppointmentId(id);
			appointment.setSuccessful(true);
			appointment.setResultMsg(env.getProperty("app.rest.appointmentid") + " " + id);
			LOGGER.info("Appointment saved successfully - createAppointment() method.");
		} else {
			appointment.setSuccessful(false);
			appointment.setResultMsg(env.getProperty("app.rest.error"));
			LOGGER.info("Appointment not saved - createAppointment() method.");
			return new ResponseEntity<Appointment>(appointment, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Appointment>(appointment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteAppointment/{id}")
	public ResponseEntity<BaseModel> deleteAppointment(@PathVariable("id") String apptNo) {
		BaseModel baseModel = new BaseModel();
		String msg = null;
		
		// Will return true/false on success/failure
		if(appointmentService.deleteAppointment(Integer.parseInt(apptNo))){
			msg = env.getProperty("app.rest.deleted") + " " + apptNo;
			LOGGER.info("Appointment delected successfully - deleteAppointment() method.");
		} else {
			msg = env.getProperty("app.rest.error");
			LOGGER.info("Appointment not deleted - deleteAppointment() method.");
			return new ResponseEntity<BaseModel>(baseModel, HttpStatus.NOT_FOUND);
		}
		
		baseModel.setResultMsg(msg);
		return new ResponseEntity<BaseModel>(baseModel, HttpStatus.OK);
	}
	
	@GetMapping("/retrieveAppointment/{id}")
	public ResponseEntity<Appointment> retrieveAppointment(@PathVariable("id") String apptNo){
		Appointment appointment = appointmentService.retrieveAppointment(Integer.parseInt(apptNo));
		
		// appointment will be null if the record does not exist in the database
		if(appointment == null) { 
			appointment = new Appointment();
			appointment.setSuccessful(false);
			appointment.setResultMsg(env.getProperty("app.rest.notexist") + " " + apptNo);
			LOGGER.info("Appointment does not exist - retrieveAppointment() rest method.");
			return new ResponseEntity<Appointment>(appointment, HttpStatus.NOT_FOUND);
		} else {
			appointment.setSuccessful(true);
			LOGGER.info("Appointment successfully retrieved - retrieveAppointment() method.");
		}
		
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}
	
	@PutMapping("/updateAppointment")
	public ResponseEntity<BaseModel> updateAppointment(@RequestBody Appointment appointment){
		BaseModel baseModel = new BaseModel();
		if(appointmentService.updateAppointment(appointment)) { // Successful database update
			baseModel.setSuccessful(true);
			baseModel.setResultMsg(env.getProperty("app.rest.updated") + " " + appointment.getAppointmentId());
			LOGGER.info("Appointment successfully retrieved - updateAppointment() rest method.");
		} else {
			baseModel.setSuccessful(false);
			baseModel.setResultMsg(env.getProperty("app.rest.error"));
			LOGGER.info("Appointment was not updated - updateAppointment() method.");
			return new ResponseEntity<BaseModel>(baseModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<BaseModel>(baseModel, HttpStatus.OK);
	}
	
	@GetMapping("/rangeAppointment")
	public List<Appointment> rangeAppointment(@RequestParam String firstDate, @RequestParam String secondDate){
		List<Appointment> appointments = new ArrayList<Appointment>();
		Date fromDate = null;
		Date toDate = null;
		try {fromDate = new SimpleDateFormat("MM/dd/yyyy").parse(firstDate);
			 toDate = new SimpleDateFormat("MM/dd/yyyy").parse(secondDate);} 
		catch (ParseException e) {
			e.printStackTrace();
			LOGGER.info("ParseException occured - rangeAppointment() method.");
			return appointments;
		}
		
		// returns empty list if nothing is found
		appointments = appointmentService.getRangeOfAppointments(fromDate, toDate);
		
		// Sort results by price - High to low
		appointments = appointments.stream().sorted(Comparator.comparingInt(Appointment::getPrice).reversed()).collect(Collectors.toList());
		
		LOGGER.info("rangeAppointment() method successful.");
		return appointments;
	}
}
