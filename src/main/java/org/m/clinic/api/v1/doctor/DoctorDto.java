package org.m.clinic.api.v1.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.m.clinic.api.v1.shared.AbstractDto;
import org.m.clinic.model.Doctor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorDto extends AbstractDto<Doctor> {

  private Long id;
  private Long userId;
  private String specialization;
  private int experienceYears;

  @Override
  public Doctor getNewEntity() {
    return new Doctor();
  }
}