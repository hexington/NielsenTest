package com.nielsen.carservicecenter;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nielsen.carservicecenter.repositories.AppointmentsRepository;
import com.nielsen.carservicecenter.scheduler.RunnableScheduler;

@SpringBootApplication
public class CarServiceCenterReduxApplication {
	
	public static ApplicationContext context;
	
	private final static Logger LOGGER = Logger.getLogger(CarServiceCenterReduxApplication.class.getName());

	public static void main(String[] args) {
		context = SpringApplication.run(CarServiceCenterReduxApplication.class, args);
		
		AppointmentsRepository appointmentService = context.getBean(com.nielsen.carservicecenter.repositories.AppointmentsRepository.class);
		
		// Create thread for appointment scheduler function to create new 
		// appointments at random intervals
		RunnableScheduler rs = new RunnableScheduler(appointmentService);
		Thread thread = new Thread(rs);
		thread.start();
		
		LOGGER.info("Application has started successfully.");
	}

}
