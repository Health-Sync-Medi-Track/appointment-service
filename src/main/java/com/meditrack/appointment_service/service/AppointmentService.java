package com.meditrack.appointment_service.service;

import com.meditrack.appointment_service.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service interface defining methods for managing appointments.
 */
public interface AppointmentService {

    /**
     * Creates a new appointment.
     *
     * @param appointment The appointment entity to be created.
     * @return The created appointment entity.
     */
    Appointment createAppointment(Appointment appointment);

    /**
     * Updates an existing appointment by its ID.
     *
     * @param id          The unique ID of the appointment to update.
     * @param appointment The updated appointment entity.
     * @return The updated appointment entity.
     */
    Appointment updateAppointment(String id, Appointment appointment);

    /**
     * Retrieves an appointment by its ID.
     *
     * @param id The unique ID of the appointment to retrieve.
     * @return The appointment entity with the specified ID.
     */
    Appointment getAppointmentById(String id);

    /**
     * Retrieves a list of appointments for a specific doctor within a specified time range.
     *
     * @param doctorId The unique ID of the doctor.
     * @param start    The start time for the time range.
     * @param end      The end time for the time range.
     * @return A list of appointments for the specified doctor and time range.
     */
    List<Appointment> getAppointmentsByDoctor(String doctorId, LocalDateTime start, LocalDateTime end);

    /**
     * Retrieves a list of appointments for a specific patient.
     *
     * @param patientId The unique ID of the patient.
     * @return A list of appointments for the specified patient.
     */
    List<Appointment> getAppointmentsByPatient(String patientId);

    /**
     * Deletes an appointment by its ID.
     *
     * @param id The unique ID of the appointment to delete.
     * @return True if the appointment was successfully deleted, false otherwise.
     */
    boolean deleteAppointment(String id);

    /**
     * Retrieves all appointments.
     *
     * @return A list of all appointments.
     */
    List<Appointment> getAll();
}
