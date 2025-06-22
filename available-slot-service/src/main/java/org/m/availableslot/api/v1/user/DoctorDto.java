package org.m.availableslot.api.v1.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorDto {

  private Long id;
  private UserDto userDto;
  private String specialization;
  private int experienceYears;

}