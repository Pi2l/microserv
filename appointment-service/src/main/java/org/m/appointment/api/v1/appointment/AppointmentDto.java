package org.m.appointment.api.v1.appointment;

import java.time.LocalDateTime;

import org.m.appointment.api.v1.shared.AbstractDto;
import org.m.appointment.model.Appointment;
import org.m.appointment.model.ProcedureType;
import org.m.appointment.model.Status;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDto extends AbstractDto<Appointment> {

  private Long id;

  @NotNull
  private Long patientId;

  @NotNull
  private Long doctorId;

  @NotNull
  private ProcedureType type;

  @NotNull
  private Status status;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime appointmentTime;

  @Override
  public Appointment getNewEntity() {
    return new Appointment();
  }
}