package org.m.user.api.v1.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.m.user.api.v1.shared.AbstractDto;
import org.m.user.api.v1.user.UserDto;
import org.m.user.model.Patient;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientDto extends AbstractDto<Patient> {

  private Long id;
  private UserDto userDto;

  @NotBlank
  private String phoneNumber;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date dateOfBirth;

  @Override
  public Patient getNewEntity() {
    return new Patient();
  }
}