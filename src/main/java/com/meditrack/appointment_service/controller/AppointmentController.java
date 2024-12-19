package com.meditrack.appointment_service.controller;

import com.meditrack.appointment_service.entity.Appointment;
import com.meditrack.appointment_service.exception.AppointmentNotFoundException;
import com.meditrack.appointment_service.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST controller for managing appointments.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    /**
     * Create a new appointment.
     *
     * @param appointment Appointment entity.
     * @return ResponseEntity with the created appointment.
     */
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        log.info("API call to create a new appointment");
        return ResponseEntity.status(201).body(appointmentService.createAppointment(appointment));
    }

    /**
     * Update an existing appointment by ID.
     *
     * @param id          ID of the appointment to update.
     * @param appointment Updated appointment data.
     * @return ResponseEntity with the updated appointment.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable String id, @RequestBody Appointment appointment) {
        log.info("API call to update appointment with ID: {}", id);
        return ResponseEntity.ok(appointmentService.updateAppointment(id, appointment));
    }

    /**
     * Fetch an appointment by ID.
     *
     * @param id ID of the appointment.
     * @return ResponseEntity with the appointment.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable String id) {
        log.info("API call to fetch appointment with ID: {}", id);
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    /**
     * Fetch all appointments.
     *
     * @return ResponseEntity with a list of all appointments.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAll() {
        log.info("API call to fetch all appointments");
        return ResponseEntity.ok(appointmentService.getAll());
    }

    /**
     * Fetch appointments for a specific doctor within a time range.
     *
     * @param doctorId Doctor ID.
     * @param start    Start time.
     * @param end      End time.
     * @return ResponseEntity with a list of appointments for the doctor.
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(
            @PathVariable String doctorId,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        log.info("API call to fetch appointments for doctor ID: {} from {} to {}", doctorId, start, end);
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctor(doctorId, start, end));
    }

    /**
     * Fetch appointments for a specific patient.
     *
     * @param patientId Patient ID.
     * @return ResponseEntity with a list of appointments for the patient.
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable String patientId) {
        log.info("API call to fetch appointments for patient ID: {}", patientId);
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(patientId));
    }

    /**
     * Delete an appointment by ID.
     *
     * @param id ID of the appointment to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id) {
        log.info("API call to delete appointment with ID: {}", id);
        if (!appointmentService.deleteAppointment(id)) {
            throw new AppointmentNotFoundException("Appointment not found with ID: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
