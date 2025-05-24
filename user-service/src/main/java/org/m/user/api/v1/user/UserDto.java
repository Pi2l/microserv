package org.m.user.api.v1.user;

import org.m.user.api.v1.shared.AbstractDto;
import org.m.user.model.Role;
import org.m.user.model.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
