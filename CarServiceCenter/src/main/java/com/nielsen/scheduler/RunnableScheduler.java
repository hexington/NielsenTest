package com.nielsen.scheduler;

import java.util.Random;
import java.util.logging.Logger;

import com.nielsen.services.AppointmentService;

// A Runnable class used to create new appointments at random intervals
public class RunnableScheduler implements Runnable {
	
	private AppointmentService appointmentService;
	
	private final static Logger LOGGER = Logger.getLogger(RunnableScheduler.class.getName());
	
	public RunnableScheduler(AppointmentService appointmentService){
		this.appointmentService = appointmentService;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
                Thread.sleep(getRandomInterval());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			
			if(SchedulerHelper.createAppointment(appointmentService)) {
				LOGGER.info("SchedulerHelper.createAppointment() thread successful.");
			}
		}
	}
	
	// return anywhere from 1 to 10 minutes
	private long getRandomInterval() {
		Random r = new Random();
		long interval = r.longs(1, 11).limit(1).findFirst().getAsLong();
		return interval * 60000;
	}
}
