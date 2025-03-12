package org.m.clinic.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment extends Auditable implements HasIdentifier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @ManyToOne
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  @Column(name = "completion_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status = Status.SCHEDULED;

  @Column(name = "appointment_time", nullable = false)
  private LocalDateTime appointmentTime;

}
