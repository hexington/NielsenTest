package com.nielsen.carservicecenter.scheduler;

import java.util.Random;
import java.util.logging.Logger;

import com.nielsen.carservicecenter.entities.Appointment;
import com.nielsen.carservicecenter.repositories.AppointmentsRepository;

// A Runnable class used to create new appointments at random intervals
public class RunnableScheduler implements Runnable {
	
	private AppointmentsRepository appointmentsRepository;
	private Appointment appt;
	
	private final static Logger LOGGER = Logger.getLogger(RunnableScheduler.class.getName());
	
	public RunnableScheduler(AppointmentsRepository appointmentsRepository){
		this.appointmentsRepository = appointmentsRepository;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
                Thread.sleep(getRandomInterval());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			
			appt = SchedulerHelper.createAppointment(appointmentsRepository);
			LOGGER.info("SchedulerHelper.createAppointment() thread successful - AppointmentId: " + appt.getAppointmentId());
		}
	}
	
	// return anywhere from 1 to 10 minutes
	private long getRandomInterval() {
		Random r = new Random();
		long interval = r.longs(1, 11).limit(1).findFirst().getAsLong();
		return interval * 60000;
	}
}
