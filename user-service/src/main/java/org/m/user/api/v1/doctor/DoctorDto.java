package org.m.user.api.v1.doctor;

import org.m.user.api.v1.shared.AbstractDto;
import org.m.user.api.v1.user.UserDto;
import org.m.user.model.Doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorDto extends AbstractDto<Doctor> {

  private Long id;
  private UserDto userDto;
  private String specialization;
  private int experienceYears;

  @Override
  public Doctor getNewEntity() {
    return new Doctor();
  }
}