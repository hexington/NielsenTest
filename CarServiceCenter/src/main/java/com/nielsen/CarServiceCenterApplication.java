package com.nielsen;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.nielsen.config.WebAppInitializer;
import com.nielsen.config.WebConfig;
import com.nielsen.scheduler.RunnableScheduler;
import com.nielsen.services.AppointmentService;

@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)
@Import({ WebConfig.class, WebAppInitializer.class })
public class CarServiceCenterApplication {

	public static ApplicationContext context;
	
	private final static Logger LOGGER = Logger.getLogger(CarServiceCenterApplication.class.getName());
	
	public static void main(String[] args) {
		context = SpringApplication.run(CarServiceCenterApplication.class, args);
		AppointmentService appointmentService = context.getBean(com.nielsen.services.AppointmentService.class);
		
		// Create thread for appointment scheduler function to create new 
		// appointments at random intervals
		RunnableScheduler rs = new RunnableScheduler(appointmentService);
		Thread thread = new Thread(rs);
		thread.start();
		
		LOGGER.info("Application has started successfully.");
	}

}
