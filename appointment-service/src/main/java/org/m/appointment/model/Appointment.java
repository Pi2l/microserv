package org.m.appointment.model;

import java.time.LocalDateTime;

import org.m.lib.model.Auditable;
import org.m.lib.model.HasIdentifier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @Column(name = "patient_id", nullable = false)
  private Long patientId;

  @Column(name = "doctor_id", nullable = false)
  private Long doctorId;

  @Builder.Default
  @Column(name = "procedure_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private ProcedureType type = ProcedureType.GENERAL_CHECKUP;

  @Builder.Default
  @Column(name = "completion_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status = Status.SCHEDULED;

  @Column(name = "appointment_time", nullable = false)
  private LocalDateTime appointmentTime;

}
