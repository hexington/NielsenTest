package com.nielsen.scheduler;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import com.nielsen.models.Appointment;
import com.nielsen.services.AppointmentService;

// Helper class used to create automated appointments
public class SchedulerHelper {
	
	private static String[] firstNames = {"Bobby", "Ray", "Carlos", "Dan", "David", "Aaron", "Mike", "Alex", "Cindy", "Anna"};
	private static String[] lastNames = {"O'Brian", "Smith", "McPhee", "Montalto", "Grant", "DeLeon", "Azad", "Deng", "Garcia", "Jenkins"};
	private static String[] cars = {"Honda", "Nissan", "Lamborghini", "Toyota", "Ferrari", "Audi", "Ford", "Infiniti"};
	private static String[] status = {"ONTIME", "DELAYED"};
	
	public static boolean createAppointment(AppointmentService appointmentService) {
		Random r = new Random();
		int fnIndex = r.nextInt(firstNames.length);
		int lnIndex = r.nextInt(lastNames.length);
		int carIndex = r.nextInt(cars.length);
		int dateRange = r.nextInt(121);
		int statusIndex = r.nextInt(status.length);
		
		// Create date and add a random number of days - from 1 - 120
		Date date = new Date();
		LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		localDateTime = localDateTime.plusDays(dateRange);
		date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = format.format(date);
		
		// Set price from 100 - 1000
		int price = r.ints(100, 1001).limit(1).findFirst().getAsInt();
		
		Appointment appointment = new Appointment();
		appointment.setFirstName(firstNames[fnIndex]);
		appointment.setLastName(lastNames[lnIndex]);
		appointment.setCar(cars[carIndex]);
		appointment.setDateOfAppt(dateString);
		appointment.setPrice(price);
		appointment.setStatusOfAppt(status[statusIndex]);
		
		return appointmentService.saveAppointment(appointment);
	}
}
