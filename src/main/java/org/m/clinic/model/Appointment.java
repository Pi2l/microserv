package org.m.clinic.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment extends Auditable implements HasIdentifier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "patient_id", nullable = false)
  private Patient patient;

  @ManyToOne
  @JoinColumn(name = "doctor_id", nullable = false)
  private Doctor doctor;

  @Column(name = "completion_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status = Status.SCHEDULED;

  @Column(name = "appointment_time", nullable = false)
  private LocalDateTime appointmentTime;

}
