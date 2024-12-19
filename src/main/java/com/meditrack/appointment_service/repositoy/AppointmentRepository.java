package com.meditrack.appointment_service.repositoy;

import com.meditrack.appointment_service.entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for managing Appointment entities in MongoDB.
 */
@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByDoctorIdAndAppointmentDateBetween(String doctorId, LocalDateTime start, LocalDateTime end);
    List<Appointment> findByPatientId(String patientId);
}
