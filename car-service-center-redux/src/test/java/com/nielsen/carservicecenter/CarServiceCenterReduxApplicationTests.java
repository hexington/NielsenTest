package com.nielsen.carservicecenter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nielsen.carservicecenter.entities.Appointment;
import com.nielsen.carservicecenter.repositories.AppointmentsRepository;
import com.nielsen.carservicecenter.scheduler.SchedulerHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceCenterReduxApplicationTests {
	 
	@Autowired
    private AppointmentsRepository appointmentsRepositoryMock;
	
	private Appointment appointment;
    
    @Before
    public void setUp() {
    	appointment = mock(Appointment.class);
    }

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void searchById() {
		appointmentsRepositoryMock.findById(100);
	}
	
	@Test
	public void testEndpoints() {
		// Create appointment
		Appointment apt = SchedulerHelper.createAppointment(appointmentsRepositoryMock);
		assertThat(apt).isNotNull();
		
		// Get appointment
		Optional<Appointment> appointment = appointmentsRepositoryMock.findById(apt.getAppointmentId());
		assertThat(appointment.isPresent()).isTrue();
		
		// Update appointment
		apt.setStatusOfAppt("DELAYED");
		assertThat(appointmentsRepositoryMock.save(apt)).isNotNull();
		
		// Delete appointment
		appointmentsRepositoryMock.deleteById(apt.getAppointmentId());
		appointment = appointmentsRepositoryMock.findById(apt.getAppointmentId());
		assertThat(appointment.isPresent()).isFalse();
	}

}
