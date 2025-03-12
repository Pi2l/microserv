package org.m.clinic.controller.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.m.clinic.controller.shared.AbstractDto;
import org.m.clinic.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDto extends AbstractDto<Appointment> {

  private Long id;

  @NotBlank
  private Long patientId;

  @NotBlank
  private Long doctorId;

  @NotNull
  private Status status;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime appointmentTime;

  @Override
  public Appointment getNewEntity() {
    return new Appointment();
  }
}