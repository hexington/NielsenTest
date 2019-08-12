package com.nielsen.carservicecenter.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nielsen.carservicecenter.entities.Appointment;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment,Integer>{
	
	@Query("SELECT p FROM Appointment p WHERE p.dateOfAppt between :firstDate and :secondDate")
	List<Appointment> findByDateRange(@Param("firstDate") Date from, @Param("secondDate") Date to);
}
