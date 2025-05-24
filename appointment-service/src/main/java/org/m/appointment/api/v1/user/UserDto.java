package org.m.appointment.api.v1.user;

import org.m.appointment.model.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

  private Long id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private Role role;

}
