package com.meditrack.appointment_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Entity class representing appointment details stored in the database.
 */
@Data
@Document(collection = "appointments")
public class Appointment {
    @Id
    private String id;

    @NotBlank
    private String patientId;

    @NotBlank
    private String doctorId;

    private String doctorName;

    @Future
    private LocalDateTime appointmentDate;

    @NotBlank
    private String status;

}
