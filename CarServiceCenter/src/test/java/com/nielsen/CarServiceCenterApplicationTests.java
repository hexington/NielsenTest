package com.nielsen;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nielsen.models.Appointment;
import com.nielsen.scheduler.SchedulerHelper;
import com.nielsen.services.AppointmentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceCenterApplicationTests {
	
	@Autowired
	AppointmentService appointmentService;
	
	private static Appointment appointment;
	private static int appointmentId;
	
	@Test
	public void test1CreateAppointmentTest() {
		assertThat(SchedulerHelper.createAppointment(appointmentService)).isEqualTo(true);
		appointmentId = appointmentService.getAppointmentId();
	}
	
	@Test
	public void test2RetrieveAppointmentTest() {
		appointment = appointmentService.retrieveAppointment(appointmentId);
		assertThat(appointment).isNotNull();
	}
	
	@Test
	public void test3UpdateAppointmentTest() {
		appointment.setCar("Audi");
		assertThat(appointmentService.updateAppointment(appointment)).isEqualTo(true);
	}
	
	@Test
	public void test4DeleteAppointmentTest() {
		assertThat(appointmentService.deleteAppointment(appointmentId)).isEqualTo(true);
	}

}
