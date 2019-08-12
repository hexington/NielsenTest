package com.nielsen.carservicecenter.controllers;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nielsen.carservicecenter.entities.Appointment;
import com.nielsen.carservicecenter.exceptions.BadRequestException;
import com.nielsen.carservicecenter.exceptions.UserNotFoundException;
import com.nielsen.carservicecenter.repositories.AppointmentsRepository;

@RestController
public class MainRestController {
	
	@Autowired
	private AppointmentsRepository appointmentsRepository;
	
	@PostMapping("/createappointment")
	public ResponseEntity<Appointment> createAppointment(@Valid @RequestBody Appointment appointment) {
		Appointment apt = appointmentsRepository.save(appointment);
		
		// Send AppointmentId in the response header
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(apt.getAppointmentId()).toUri();
		
		// Returns Status 201 - Created
		// return ResponseEntity.created(location).build();
		
		// If you want to send back the Appointment Object
		return new ResponseEntity<Appointment>(apt, HttpStatus.CREATED);
	}

	@GetMapping("/getappointment/{id}")
	public Appointment getAppointment(@PathVariable("id") int id) {		
		Optional<Appointment> appointment = appointmentsRepository.findById(id);
		
		if(!appointment.isPresent())
			throw new UserNotFoundException("Appointment Id: " + id);
		
		return appointment.get();
	}
	
	@DeleteMapping("/deleteappointment/{id}")
	public void deleteAppointment(@PathVariable int id) {
		if(!appointmentsRepository.existsById(id)) {
			throw new UserNotFoundException("Appointment Id: " + id);
		}
		appointmentsRepository.deleteById(id);
	}
	
	@PostMapping("/updateappointment/{id}")
	public ResponseEntity<Appointment> updateAppointment(@Valid @RequestBody Appointment appointment, @PathVariable int id) {
		if(!appointmentsRepository.existsById(id)) {
			throw new UserNotFoundException("Appointment Id: " + id);
		}
		
		appointment.setAppointmentId(id);
		Appointment apt = appointmentsRepository.save(appointment);
		
		return new ResponseEntity<Appointment>(apt, HttpStatus.OK);
	}
	
	@GetMapping("/getrange/from/{firstDate}/to/{secondDate}")
	public List<Appointment> getRange(@PathVariable String firstDate, @PathVariable String secondDate){
		Date fromDate = null;
		Date toDate = null;
		try {fromDate = new SimpleDateFormat("MM-dd-yyyy").parse(firstDate);
			 toDate = new SimpleDateFormat("MM-dd-yyyy").parse(secondDate);} 
		catch (ParseException e) {
			e.printStackTrace();
			throw new BadRequestException("Date must be in MM-dd-yyyy format.");
		}
		
		List<Appointment> appointments = appointmentsRepository.findByDateRange(fromDate, toDate);
		// Sort results by price - High to low
		appointments = appointments.stream().sorted(Comparator.comparingInt(Appointment::getPrice).reversed()).collect(Collectors.toList());
		
		return appointments;
	}
}
