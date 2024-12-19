package com.meditrack.appointment_service.service;

import com.meditrack.appointment_service.entity.Appointment;
import com.meditrack.appointment_service.exception.AppointmentNotFoundException;
import com.meditrack.appointment_service.repositoy.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service implementation for managing appointments.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    /**
     * Create a new appointment.
     *
     * @param appointment The appointment entity to be created.
     * @return The created appointment entity.
     */
    @Override
    public Appointment createAppointment(Appointment appointment) {
        log.info("Creating new appointment for patient ID: {}", appointment.getPatientId());
        return appointmentRepository.save(appointment);
    }

    /**
     * Update an existing appointment by its ID.
     *
     * @param id          The unique ID of the appointment to update.
     * @param appointment The updated appointment data.
     * @return The updated appointment entity.
     */
    @Override
    public Appointment updateAppointment(String id, Appointment appointment) {
        log.info("Updating appointment with ID: {}", id);
        return appointmentRepository.findById(id)
                .map(existingAppointment -> {
                    appointment.setId(id); // Ensure the same record is updated.
                    return appointmentRepository.save(appointment);
                })
                .orElseThrow(() -> {
                    log.warn("Appointment not found with ID: {}", id);
                    return new AppointmentNotFoundException("Appointment not found with ID: " + id);
                });
    }

    /**
     * Fetch an appointment by its ID.
     *
     * @param id The unique ID of the appointment to fetch.
     * @return The appointment entity.
     */
    @Override
    public Appointment getAppointmentById(String id) {
        log.info("Fetching appointment with ID: {}", id);
        return appointmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Appointment not found with ID: {}", id);
                    return new AppointmentNotFoundException("Appointment not found with ID: " + id);
                });
    }

    /**
     * Fetch appointments for a specific doctor within a time range.
     *
     * @param doctorId The unique ID of the doctor.
     * @param start    The start time for the time range.
     * @param end      The end time for the time range.
     * @return A list of appointments for the doctor within the time range.
     */
    @Override
    public List<Appointment> getAppointmentsByDoctor(String doctorId, LocalDateTime start, LocalDateTime end) {
        log.info("Fetching appointments for doctor ID: {} between {} and {}", doctorId, start, end);
        return appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, start, end);
    }

    /**
     * Fetch appointments for a specific patient.
     *
     * @param patientId The unique ID of the patient.
     * @return A list of appointments for the specified patient.
     */
    @Override
    public List<Appointment> getAppointmentsByPatient(String patientId) {
        log.info("Fetching appointments for patient ID: {}", patientId);
        return appointmentRepository.findByPatientId(patientId);
    }

    /**
     * Delete an appointment by its ID.
     *
     * @param id The unique ID of the appointment to delete.
     * @return True if the appointment was successfully deleted, otherwise throws an exception.
     */
    @Override
    public boolean deleteAppointment(String id) {
        log.info("Deleting appointment with ID: {}", id);
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        } else {
            log.warn("Appointment not found with ID: {}", id);
            throw new AppointmentNotFoundException("Appointment not found with ID: " + id);
        }
    }

    /**
     * Fetch all appointments.
     *
     * @return A list of all appointments.
     */
    @Override
    public List<Appointment> getAll() {
        log.info("Fetching all appointments");
        return appointmentRepository.findAll();
    }
}
