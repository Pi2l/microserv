package org.m.clinic.controller.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.m.clinic.controller.shared.AbstractDto;
import org.m.clinic.model.Role;
import org.m.clinic.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto extends AbstractDto<User> {

  private Long id;

  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  private String firstName;

  private String lastName;

  private Role role;

  @Override
  public User getNewEntity() {
    return new User();
  }
}
