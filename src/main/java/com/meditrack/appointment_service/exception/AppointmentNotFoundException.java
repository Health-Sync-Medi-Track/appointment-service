package com.meditrack.appointment_service.exception;

/**
 * Custom exception for appointment not found scenarios.
 */
public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
